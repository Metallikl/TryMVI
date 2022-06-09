package com.luche.trymvi.feature_savename.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luche.trymvi.feature_savename.domain.usecases.IsValidNameUseCase
import com.luche.trymvi.feature_savename.domain.usecases.SaveNameUseCase
import com.luche.trymvi.util.ResultStatus
import com.luche.trymvi.feature_savename.presentation.viewAction.SaveNameViewAction
import com.luche.trymvi.feature_savename.presentation.viewState.SaveNameViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SaveNameViewModel(
    private val saveNameUseCase: SaveNameUseCase,
    private val isValidNameUseCase: IsValidNameUseCase
) : ViewModel() {
    val viewState = SaveNameViewState()

    fun dispacherViewAction(action: SaveNameViewAction) {
        when (action) {
            SaveNameViewAction.initScreen -> initializeScreen()
            is SaveNameViewAction.SendName -> sendNameToApi(action.name)
            is SaveNameViewAction.UpdateName -> updateName(action.name)
            is SaveNameViewAction.ValidateName -> validateName(action.name)
        }
    }

    private fun initializeScreen() {
        viewState.state.value = SaveNameViewState.STATE.INITIAL
        viewModelScope.launch {
            delay(5000)
            viewState.let {
                it.name.value = ""
                it.interaction.value = SaveNameViewState.ViewInteraction.HideSaveBtn
            }
            viewState.state.postValue(SaveNameViewState.STATE.SUCCESS)
        }
    }

    private fun validateName(name: String) {
        viewModelScope.launch {
            viewState.state.value = SaveNameViewState.STATE.LOADING
            delay(2000)
            viewState.state.value = isValidNameUseCase(name)
            when (viewState.state.value) {
                SaveNameViewState.STATE.SUCCESS -> {
                    viewState.interaction.value =
                        SaveNameViewState.ViewInteraction.ShowSaveBtn
                }
                else -> viewState.interaction.value =
                    SaveNameViewState.ViewInteraction.InvalidateNameError(
                        "Nome invalido !!!"
                    )
            }
        }
    }

    private fun sendNameToApi(name: String) {
        viewState.state.value = SaveNameViewState.STATE.LOADING
        viewModelScope.launch {
            when (val result = saveNameUseCase(name)) {
                is ResultStatus.Success -> {
                    viewState.state.postValue(SaveNameViewState.STATE.SUCCESS)
                    viewState.interaction.value = SaveNameViewState.ViewInteraction.NameSuccessfullySaved(result.data)
                }
                is ResultStatus.Error -> {
                    viewState.state.postValue(SaveNameViewState.STATE.ERROR)
                    viewState.interaction.value = SaveNameViewState.ViewInteraction.ShowErrorSnack(result.throwable)
                }
            }
        }
    }

    private fun updateName(name: String) {
        viewState.name.value = name
        viewState.isValidadeButtonEnabled.value = name.length >= 3
    }
}