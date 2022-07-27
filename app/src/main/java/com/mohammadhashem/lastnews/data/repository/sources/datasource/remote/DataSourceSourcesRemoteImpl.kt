package com.mohammadhashem.lastnews.data.repository.sources.datasource.remote

import com.mohammadhashem.lastnews.data.api.ApiNews
import com.mohammadhashem.lastnews.data.model.SourcesResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DataSourceSourcesRemoteImpl @Inject constructor(private val api: ApiNews) : DataSourceSourcesRemote {

    override fun getAllRemoteSources(apiKey: String): Single<SourcesResponse> {
        return api.getSources(apiKey)
    }

}
