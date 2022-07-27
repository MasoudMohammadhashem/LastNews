package com.mohammadhashem.lastnews.data.repository.sources.datasource

import com.mohammadhashem.lastnews.data.model.SourcesResponse
import io.reactivex.rxjava3.core.Single


interface DataSourceSources {
    fun getAllRemoteSources(apiKey: String): Single<SourcesResponse>
}
