package com.mohammadhashem.lastnews.data.repository.sources.datasource.remote

import com.mohammadhashem.lastnews.data.model.SourcesResponse
import com.mohammadhashem.lastnews.data.repository.sources.datasource.DataSourceSources
import io.reactivex.rxjava3.core.Single

interface DataSourceSourcesRemote : DataSourceSources {
    override fun getAllRemoteSources(apiKey: String): Single<SourcesResponse>
}
