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
    
    /**
     * Method to load the image in the image view using glide
     *
     * @param imageView image view to which the image has to be laoded
     * @param url url of the image
     */
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_globe)
            .error(R.drawable.ic_placeholder)
            .into(imageView)
    }
}