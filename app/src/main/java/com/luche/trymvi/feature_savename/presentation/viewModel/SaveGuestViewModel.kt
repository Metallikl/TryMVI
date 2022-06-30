package com.luche.trymvi.feature_savename.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luche.trymvi.feature_savename.domain.usecases.IsValidNameUseCase
import com.luche.trymvi.feature_savename.domain.usecases.SaveGuestUseCase
import com.luche.trymvi.util.ResultStatus
import com.luche.trymvi.feature_savename.presentation.viewAction.SaveGuestAction
import com.luche.trymvi.feature_savename.presentation.viewState.SaveGuestState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SaveGuestViewModel(
    private val saveGuestUseCase: SaveGuestUseCase,
    private val isValidNameUseCase: IsValidNameUseCase
) : ViewModel() {
    val viewState = SaveGuestState()

    fun dispacherViewAction(action: SaveGuestAction) {
        when (action) {
            SaveGuestAction.initScreen -> initializeScreen()
            is SaveGuestAction.SendGuest -> sendNameToApi(action.name)
            is SaveGuestAction.UpdateGuest -> updateName(action.name)
            is SaveGuestAction.ValidateGuest -> validateName(action.name)
        }
    }

    private fun initializeScreen() {
        viewState.state.value = SaveGuestState.STATE.INITIAL
        viewModelScope.launch {
            delay(5000)
            viewState.let {
                it.name.value = ""
                it.interaction.value = SaveGuestState.ViewInteraction.HideSaveBtn
            }
            viewState.state.postValue(SaveGuestState.STATE.SUCCESS)
        }
    }

    private fun validateName(name: String) {
        viewModelScope.launch {
            viewState.state.value = SaveGuestState.STATE.LOADING
            delay(2000)
            viewState.state.value = isValidNameUseCase(name)
            when (viewState.state.value) {
                SaveGuestState.STATE.SUCCESS -> {
                    viewState.interaction.value =
                        SaveGuestState.ViewInteraction.ShowSaveBtn
                }
                else -> viewState.interaction.value =
                    SaveGuestState.ViewInteraction.InvalidateNameError(
                        "Nome invalido !!!"
                    )
            }
        }
    }

    private fun sendNameToApi(name: String) {
        viewState.state.value = SaveGuestState.STATE.LOADING
        viewModelScope.launch {
            when (val result = saveGuestUseCase(name)) {
                is ResultStatus.Success -> {
                    viewState.state.postValue(SaveGuestState.STATE.SUCCESS)
                    viewState.interaction.value = SaveGuestState.ViewInteraction.NameSuccessfullySaved(result.data)
                }
                is ResultStatus.Error -> {
                    viewState.state.postValue(SaveGuestState.STATE.ERROR)
                    viewState.interaction.value = SaveGuestState.ViewInteraction.ShowErrorSnack(result.throwable)
                }
            }
        }
    }

    private fun updateName(name: String) {
        viewState.name.value = name
        viewState.isValidadeButtonEnabled.value = name.length >= 3
    }
}