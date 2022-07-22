package com.luche.trymvi.feature_listguest.presentation.viewaction

sealed class GuestListAction{
    data class ShowError(val error: String) : GuestListAction()
    data class NavegateToEditScreen(val guestId: String) : GuestListAction()
    object NavegateToAddGuestScreen : GuestListAction()
}
