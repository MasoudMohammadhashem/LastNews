package com.mohammadhashem.lastnews.presentation.ui.fragment.mainfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadhashem.lastnews.common.constants.API_KEY
import com.mohammadhashem.lastnews.common.utils.observable.NewsSingleObservable
import com.mohammadhashem.lastnews.data.model.SourcesResponse
import com.mohammadhashem.lastnews.domain.usecases.sources.GetAllSourceUseCases
import com.mohammadhashem.lastnews.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getAllSourceUseCases: GetAllSourceUseCases) :
    BaseViewModel() {
    private val _sourceList = MutableLiveData<SourcesResponse>()
    var sourceList: LiveData<SourcesResponse> = _sourceList


    init {
        fetchData()
    }

    private fun fetchData() {
        try {
            getAllSourceUseCases.getAllRemoteUseCase(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.setValue(true) }
                .subscribe(object : NewsSingleObservable<SourcesResponse>(compositeDisposable) {
                    override fun onSuccess(succeed: SourcesResponse) {
                        _sourceList.value = succeed
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.message
                        loading.value = false
                    }
                })
        } catch (e: Exception) {
            error.value = e.message
            loading.value = false
        }
    }

}