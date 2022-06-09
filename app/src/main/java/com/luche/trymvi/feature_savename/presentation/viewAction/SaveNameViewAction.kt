package com.luche.trymvi.feature_savename.presentation.viewAction

sealed class SaveNameViewAction{
    object initScreen : SaveNameViewAction()
    data class UpdateName(val name: String) : SaveNameViewAction()
    data class ValidateName(val name: String): SaveNameViewAction()
    data class SendName(val name: String): SaveNameViewAction()
}
