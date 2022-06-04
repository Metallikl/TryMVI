package com.luche.trymvi.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.facebook.shimmer.ShimmerFrameLayout

@BindingAdapter("app:shouldBeVisible")
fun View.shouldBeVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("app:showShimmerEffect")
fun ShimmerFrameLayout.showShimmerEffect(show: Boolean) {
    if (show) {
        this.startShimmer()
    } else this.stopShimmer()
    //
    this.showShimmer(show)
}