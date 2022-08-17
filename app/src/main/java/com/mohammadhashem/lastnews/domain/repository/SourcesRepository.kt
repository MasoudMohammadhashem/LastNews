package com.mohammadhashem.lastnews.domain.repository

import com.mohammadhashem.lastnews.data.model.SourcesResponse
import io.reactivex.rxjava3.core.Single

interface SourcesRepository {
    fun getAllRemoteSources(apiKey: String): Single<SourcesResponse>
}
