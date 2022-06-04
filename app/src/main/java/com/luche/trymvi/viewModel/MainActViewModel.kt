package com.luche.trymvi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luche.trymvi.usecases.SaveNameUseCase
import com.luche.trymvi.util.ResultStatus
import com.luche.trymvi.viewAction.MainViewAction
import com.luche.trymvi.viewState.MainActViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActViewModel(
    private val saveNameUseCase: SaveNameUseCase
) : ViewModel() {
    val viewState = MainActViewState()

    fun dispacherViewAction(action: MainViewAction) {
        when (action) {
            MainViewAction.initScreen -> initializeScreen()
            is MainViewAction.SendName -> sendNameToApi(action.name)
            is MainViewAction.UpdateName -> updateName(action.name)
            is MainViewAction.ValidateName -> validateName(action.name)
        }
    }

    private fun initializeScreen() {
        viewState.state.value = MainActViewState.STATE.INITIAL
        viewModelScope.launch {
            delay(5000)
            viewState.let {
                it.name.value = ""
                it.interaction.value = MainActViewState.ViewInteraction.HideSaveBtn
            }
            viewState.state.postValue(MainActViewState.STATE.SUCCESS)
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
        viewState.state.value = MainActViewState.STATE.LOADING
        viewModelScope.launch {
            when (val result = saveNameUseCase(name)) {
                is ResultStatus.Success -> {
                    viewState.state.postValue(MainActViewState.STATE.LOADING)
                    viewState.interaction.value = MainActViewState.ViewInteraction.NameSuccessfullySaved(result.data)
                }
                is ResultStatus.Error -> {
                    viewState.state.postValue(MainActViewState.STATE.ERROR)
                    viewState.interaction.value = MainActViewState.ViewInteraction.ShowErrorSnack(result.throwable)
                }
            }
        }
    }

    private fun updateName(name: String) {
        viewState.name.value = name
        viewState.isValidadeButtonEnabled.value = name.length >= 3
    }
}