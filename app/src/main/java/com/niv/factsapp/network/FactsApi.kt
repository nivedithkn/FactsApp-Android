package com.niv.factsapp.network


import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import com.niv.factsapp.BuildConfig
import com.niv.factsapp.models.ApiResult
import com.niv.factsapp.models.Failure
import com.niv.factsapp.models.SimpleResponse
import com.niv.factsapp.models.Success
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * FactsApi - A class for the implementation of API
 *
 * @author Nivedith
 * @since 2020-03-26.
 */
object FactsApi {

    private const val BASE_URL = BuildConfig.API_BASE_URL

    private val retrofit: RetrofitService by lazy {

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    var errorHandler: ((errorCode: Int) -> Unit)? = null

    var connectionChecker: (() -> Boolean)? = null


    /**
     * This method will execute the `Call<T>` and handle the exceptions
     * that can happen. It returns either `Success<T>` or `Failure<T>`.
     *
     * Consumers of the API calls can check the result using this style:
     *
     *      result = FactsApi.someApiCall()
     *
     *      when (result){
     *          is Success -> doSomething(result.contents)
     *          is Failure -> showError(result.throwable.message)
     *      }
     *
     */
    private fun <T : Any> Call<T>.callWithExceptionHandling(): ApiResult<T> {

        var responseCode = 101
        var responseMsg = "Unknown error"

        return try {

            val response = execute()

            if (response.isSuccessful && response.body() != null) {

                Success(response.body() as T)

            } else {

                responseCode = response.code()
                responseMsg = response.message()

                /*
                Retrofit currently doesn't convert its `errorBody` to JSON. We need to do that
                manually. Ref: https://github.com/square/retrofit/issues/1321
                */
                val errorBody = response.errorBody()?.string()

                val errorResponse = Gson().fromJson(errorBody, SimpleResponse::class.java)

                // Check for mobile error messages.
                val mobileErrorMessage = errorResponse?.error?.mobile_errors?.message
                val normalErrorMessage = errorResponse?.message

                if (!mobileErrorMessage.isNullOrEmpty()) {
                    Failure(
                        responseCode,
                        Throwable(mobileErrorMessage)
                    )    // If there is a "mobile_error"
                } else if (!normalErrorMessage.isNullOrEmpty()) {
                    Failure(
                        responseCode,
                        Throwable(normalErrorMessage)
                    )    // Else if there is an "error"
                } else {
                    Failure(
                        responseCode,
                        Throwable("$responseCode: $responseMsg")
                    ) // Else just the HTTP status.
                }
            }

        } catch (e: Exception) {

            if (e is JsonSyntaxException || e is MalformedJsonException) {
                responseCode = 102
                responseMsg = "Invalid server response."
            }

            Failure(
                responseCode,
                Throwable("$responseCode: $responseMsg \n\n[Error Details]\n${e.localizedMessage}")
            )

        }

    }


}