package com.luche.trymvi.feature_listguest.presentation.viewintent

sealed class GuestListIntent {
    object InitScreen : GuestListIntent()
    data class SearchGuest(val guestName: String) : GuestListIntent()
    object ClearSearch : GuestListIntent()
    data class EditGuest(val guestId: String): GuestListIntent()
    object AddGuest: GuestListIntent()
}
