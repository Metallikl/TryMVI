package com.luche.trymvi.feature_listguest.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.luche.trymvi.common.presentation.BaseViewModel
import com.luche.trymvi.feature_listguest.domain.usecase.GetGuestListUseCase
import com.luche.trymvi.feature_listguest.presentation.viewaction.GuestListAction
import com.luche.trymvi.feature_listguest.presentation.viewintent.GuestListIntent
import com.luche.trymvi.feature_listguest.presentation.viewstate.GuestListViewState
import com.luche.trymvi.feature_savename.data.entity.Guest
import com.luche.trymvi.util.ResultStatus
import com.luche.trymvi.util.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GuestListViewModel(
    private val getGuestListUseCase: GetGuestListUseCase
): BaseViewModel<GuestListIntent,GuestListViewState,GuestListAction>() {
    override val viewState = GuestListViewState()
    override val viewAction = SingleLiveEvent<GuestListAction>()

    override fun dispatchIntentToVM(intent: GuestListIntent) {
        when(intent){
            GuestListIntent.AddGuest -> addGuest()
            GuestListIntent.ClearSearch -> TODO()
            is GuestListIntent.EditGuest -> editGuest(intent.guestId)
            GuestListIntent.InitScreen -> initScreen()
            is GuestListIntent.SearchGuest -> searchGuest(intent.guestName)
        }
    }

    private fun addGuest() {
        setViewAction(GuestListAction.NavegateToAddGuestScreen)
    }

    private fun setViewState(state: GuestListViewState.State ) {
        viewState.state.value = state
    }

    private fun setViewAction(action: GuestListAction) {
        viewAction.value = action
    }


    private fun initScreen() {
        setViewState(GuestListViewState.State.INITAL_LOADING)
        viewModelScope.launch {
            delay(2000)
            when( val guestListUseCase = getGuestListUseCase()){
                is ResultStatus.Success ->{
                    updateViewGuestList(guestListUseCase.data)
                }
                is ResultStatus.Error ->{
                    errorOnUpdateViewGuesyList(guestListUseCase.throwable)
                }
            }
        }
    }

    private fun searchGuest(guestName: String) {
        setViewState(GuestListViewState.State.SEARCH_LOADING)
        viewModelScope.launch {
            when( val guestListUseCase = getGuestListUseCase(guestName)){
                is ResultStatus.Success ->{
                    updateViewGuestList(guestListUseCase.data)
                }
                is ResultStatus.Error ->{
                    errorOnUpdateViewGuesyList(guestListUseCase.throwable)
                }
            }
        }
    }

    private fun editGuest(guestId: String) {
        setViewAction(GuestListAction.NavegateToEditScreen(guestId = guestId))
    }

    private fun updateViewGuestList(guestList: List<Guest>) {
        setViewState(GuestListViewState.State.SUCCESS)
        viewState.guestList.value = guestList
    }


    private fun errorOnUpdateViewGuesyList(throwable: String) {
        setViewState(GuestListViewState.State.ERROR)
        setViewAction(GuestListAction.ShowError(throwable))
    }
}