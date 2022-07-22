package com.luche.trymvi.feature_listguest.presentation.viewstate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import com.luche.trymvi.feature_savename.data.entity.Guest

class GuestListViewState {
    val state = MutableLiveData<State>().apply {
        this.value = State.INITAL_LOADING
    }
    val guestSearch = MutableLiveData<String>().apply {
        value = ""
    }

    val guestList = MutableLiveData<List<Guest>>()

    val isLoading : LiveData<Boolean> = map(state){
        it == State.INITAL_LOADING
                || it == State.SEARCH_LOADING
                || it == State.CLEAR_LOADING
    }

    enum class State{
        INITAL_LOADING,
        SEARCH_LOADING,
        CLEAR_LOADING,
        SUCCESS,
        ERROR
    }
}