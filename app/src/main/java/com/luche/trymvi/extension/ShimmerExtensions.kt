package com.luche.trymvi.extension

import androidx.core.view.isVisible
import com.facebook.shimmer.ShimmerFrameLayout

fun ShimmerFrameLayout.updateShimmerState(show: Boolean = true) {
    this.apply {
        if (show) {
            this.startShimmer()
            this.showShimmer(show)
        } else{
            this.stopShimmer()
            this.hideShimmer()
        }
        this.isVisible = show
    }
}