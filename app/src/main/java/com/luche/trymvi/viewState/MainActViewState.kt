package com.luche.trymvi.viewState

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.map
import androidx.lifecycle.switchMap

class MainActViewState {
    //reflete o estado atual
    val state = MutableLiveData<STATE>().apply {
        MainActViewState.STATE.SUCCESS
    }
    //reflete uma inteação entre o model do MVI / viewModel
    val interaction = MutableLiveData<ViewInteraction>()

    val isLoadingState : LiveData<Boolean> = map(state){
        it == STATE.LOADING
    }

    val isErrorState : LiveData<Boolean> = map(state){
        it == STATE.ERROR
    }

    val isSuccessState : LiveData<Boolean> = map(state){
        it == STATE.SUCCESS
    }

    val name = MutableLiveData<String>().apply {value = "" }
    val counter = map(name){
        name.value?.length?:0
    }

    val isValidadeButtonEnabled = MutableLiveData<Boolean>().apply { false }

    //Representa as interações que da view e que são chamada pelo model do MVI
    sealed class ViewInteraction {
        object HideSaveBtn : ViewInteraction()
        object ShowSaveBtn : ViewInteraction()
        data class ShowErrorSnack(val error: String) : ViewInteraction()
        data class InvalidateNameError(val error: String) : ViewInteraction()
    }
    //Representa os estados da tela.
    enum class STATE {
        LOADING,
        ERROR,
        SUCCESS
    }
}
