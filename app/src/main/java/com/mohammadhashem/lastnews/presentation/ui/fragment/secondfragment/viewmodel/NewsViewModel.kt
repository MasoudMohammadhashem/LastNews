package com.mohammadhashem.lastnews.presentation.ui.fragment.secondfragment.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.mohammadhashem.lastnews.common.utils.observable.NewsSingleObservable
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.SourceX
import com.mohammadhashem.lastnews.domain.usecases.articles.AllUseCaseArticles
import com.mohammadhashem.lastnews.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val allUseCaseArticles: AllUseCaseArticles,@ApplicationContext context: Context) :
    BaseViewModel() {

//    init {
//        viewModelScope.launch {
//            delay(2000L)
//            loading.value = false
//        }
//    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun callRemote(sourceX: SourceX): Flowable<PagingData<Article>> {
            return allUseCaseArticles.getAllRemoteArticleUseCase.invoke(sourceX.id)
                .cachedIn(viewModelScope)
    }


//JUST Showing the implementation of DataBase
    private fun checkDataBaseData(sourceX: SourceX) {
        allUseCaseArticles.getAllCacheArticleUseCase.invoke(sourceX)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NewsSingleObservable<List<Article>>(compositeDisposable) {
                override fun onSuccess(succeed: List<Article>) {
                    if (succeed.isNotEmpty()) {
                        loading.value = false
//Emit Data               _articleList.value = succeed
                    }else{
                        callRemote(sourceX)
                    }
                }
                override fun onError(e: Throwable) {
                    callRemote(sourceX)
                }
            })
    }
}