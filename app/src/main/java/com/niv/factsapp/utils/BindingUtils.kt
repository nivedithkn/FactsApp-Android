package com.niv.factsapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.niv.factsapp.R

/**
 * BindingUtils - A util class for binding
 *
 * @author Nivedith
 * @since 2020-03-28.
 */
object BindingUtils {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_globe)
            .into(imageView)
    }
}