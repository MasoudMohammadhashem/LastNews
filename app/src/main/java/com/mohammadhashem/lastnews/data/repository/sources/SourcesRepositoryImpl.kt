package com.mohammadhashem.lastnews.data.repository.sources

import com.mohammadhashem.lastnews.data.model.SourcesResponse
import com.mohammadhashem.lastnews.data.repository.sources.datasource.DataSourceSources
import com.mohammadhashem.lastnews.domain.repository.SourcesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(private val remote: DataSourceSources) :
    SourcesRepository {
    override fun getAllRemoteSources(apiKey: String): Single<SourcesResponse> =
        remote.getAllRemoteSources(apiKey)
}

