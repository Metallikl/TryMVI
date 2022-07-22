package com.luche.trymvi.common.presentation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.luche.trymvi.util.SingleLiveEvent

abstract class BaseViewModel<VI,VS,VA>: ViewModel() {

    abstract val viewState: VS
    abstract val viewAction: SingleLiveEvent<VA>

    abstract fun dispatchIntentToVM(intent: VI)

}