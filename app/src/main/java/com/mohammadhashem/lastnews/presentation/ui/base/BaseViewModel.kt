package com.mohammadhashem.lastnews.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel  : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}