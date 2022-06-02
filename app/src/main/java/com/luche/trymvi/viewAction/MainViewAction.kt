package com.luche.trymvi.viewAction

sealed class MainViewAction{
    object initScreen : MainViewAction()
    data class UpdateName(val name: String) : MainViewAction()
    data class ValidateName(val name: String): MainViewAction()
    data class SendName(val name: String): MainViewAction()
}
