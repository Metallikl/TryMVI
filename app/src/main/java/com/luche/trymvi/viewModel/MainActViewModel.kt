package com.luche.trymvi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luche.trymvi.viewAction.MainViewAction
import com.luche.trymvi.viewState.MainActViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActViewModel : ViewModel() {
    val viewState = MainActViewState()

    fun dispacherViewAction(action: MainViewAction) {
        when (action) {
            is MainViewAction.SendName -> sendNameToApi(action.name)
            is MainViewAction.UpdateName -> updateName(action.name)
            is MainViewAction.ValidateName -> validateName(action.name)
            MainViewAction.initScreen -> initializeScreen()
        }
    }

    private fun initializeScreen() {
        viewModelScope.launch {
            viewState.state.value = MainActViewState.STATE.LOADING
            viewState.let {
                it.name.value = ""
                it.interaction.value = MainActViewState.ViewInteraction.HideSaveBtn
            }
            viewState.state.value = MainActViewState.STATE.SUCCESS
        }

    }

    private fun validateName(name: String) {
        viewModelScope.launch {
            viewState.state.value = MainActViewState.STATE.LOADING
            delay(2000)
            viewState.state.value = callUseCase(name)
            when (viewState.state.value) {
                MainActViewState.STATE.SUCCESS -> {
                    viewState.interaction.value =
                        MainActViewState.ViewInteraction.ShowSaveBtn
                }
                else -> viewState.interaction.value =
                    MainActViewState.ViewInteraction.InvalidateNameError(
                        "Nome invalido !!!"
                    )
            }
        }
    }

    private fun callUseCase(name: String): MainActViewState.STATE {
        return if (name.isNotEmpty() && name.length > 4) {
            MainActViewState.STATE.SUCCESS
        } else MainActViewState.STATE.ERROR
    }


    private fun sendNameToApi(name: String) {
        TODO("Not yet implemented")
    }

    private fun updateName(name: String) {
        viewState.name.value = name
        viewState.isValidadeButtonEnabled.value = name.length >= 3
    }
}