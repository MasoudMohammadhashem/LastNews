package com.mohammadhashem.lastnews.presentation.ui.fragment.secondfragment.viewmodel

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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val allUseCaseArticles: AllUseCaseArticles) :
    BaseViewModel() {
    private val _articleList = MutableLiveData<List<Article>>()
    var articleList: LiveData<List<Article>> = _articleList

    fun fetchData(sourceId: SourceX) {
        checkDataBaseData(sourceId)
    }

    private fun checkDataBaseData(sourceId: SourceX) {
        allUseCaseArticles.getAllCacheArticleUseCase.invoke(sourceId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NewsSingleObservable<List<Article>>(compositeDisposable) {
                override fun onSuccess(succeed: List<Article>) {
                    if (succeed.isNotEmpty()) {
                        loading.value = false
                        _articleList.value = succeed
                    }else{
                        callRemote(sourceId)
                    }
                }
                override fun onError(e: Throwable) {
                    callRemote(sourceId)
                }
            })
    }
    private fun callRemote(sourceId: SourceX){
        allUseCaseArticles.getAllRemoteArticleUseCase.invoke(sourceId.id, 1, 20, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NewsSingleObservable<ArticlesResponse>(compositeDisposable) {
                override fun onSuccess(succeed: ArticlesResponse?) {
                    if (succeed != null) {
                        for (item in succeed.articles) {
                            allUseCaseArticles.addArticleToCacheUseCase.invoke(item)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object :
                                    NewsSingleObservable<Long>(compositeDisposable) {
                                    override fun onSuccess(succeed: Long) {
                                        val a = succeed
                                    }
                                    override fun onError(e: Throwable) {
                                        e.message
                                    }
                                })
                        }
                        checkDataBaseData(sourceId)
                    }
                }
                override fun onError(e: Throwable) {
                    error.value = e.message
                    loading.value = false
                }
            })
    }
}