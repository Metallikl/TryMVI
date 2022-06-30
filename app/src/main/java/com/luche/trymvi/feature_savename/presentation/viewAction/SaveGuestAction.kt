package com.luche.trymvi.feature_savename.presentation.viewAction

sealed class SaveGuestAction{
    object initScreen : SaveGuestAction()
    data class UpdateGuest(val name: String) : SaveGuestAction()
    data class ValidateGuest(val name: String): SaveGuestAction()
    data class SendGuest(val name: String): SaveGuestAction()
}
