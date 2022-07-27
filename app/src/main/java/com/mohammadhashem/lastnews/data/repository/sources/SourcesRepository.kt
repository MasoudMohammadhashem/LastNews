package com.mohammadhashem.lastnews.data.repository.sources

import com.mohammadhashem.lastnews.data.model.SourcesResponse
import io.reactivex.rxjava3.core.Single

interface SourcesRepository {
    fun getAllRemoteSources(apiKey: String): Single<SourcesResponse>
}
