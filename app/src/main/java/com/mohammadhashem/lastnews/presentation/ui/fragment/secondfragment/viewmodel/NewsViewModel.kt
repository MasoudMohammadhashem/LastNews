package com.mohammadhashem.lastnews.presentation.ui.fragment.secondfragment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mohammadhashem.lastnews.common.constants.API_KEY
import com.mohammadhashem.lastnews.common.utils.observable.NewsSingleObservable
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.ArticlesResponse
import com.mohammadhashem.lastnews.data.model.SourceX
import com.mohammadhashem.lastnews.domain.usecases.articles.AllUseCaseArticles
import com.mohammadhashem.lastnews.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val allUseCaseArticles: AllUseCaseArticles,@ApplicationContext context: Context) :
    BaseViewModel() {
    private val _articleList = MutableLiveData<List<Article>>()
    var articleList: LiveData<List<Article>> = _articleList


    fun fetchData(sourceX: SourceX) {
        callRemote(sourceX)
    }

    private fun callRemote(sourceX: SourceX){
        allUseCaseArticles.getAllRemoteArticleUseCase.invoke(sourceX.id, 1, 20, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NewsSingleObservable<ArticlesResponse>(compositeDisposable) {
                override fun onSuccess(succeed: ArticlesResponse) {
                        if (succeed.status == "ok") {
                            _articleList.value = succeed.articles
                            loading.value = false
                        }
//                    else -> RETRY REQUEST -> callRemote(sourceX)
                }
                override fun onError(e: Throwable) {
                    error.value = e.message
                    loading.value = false
                }
            })
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
                        _articleList.value = succeed
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