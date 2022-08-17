package com.mohammadhashem.lastnews.domain.usecases.sources

import com.mohammadhashem.lastnews.domain.repository.SourcesRepository

class GetSourceRemoteUseCase(private val repository: SourcesRepository) {
     operator fun invoke(apiKey : String) = repository.getAllRemoteSources(apiKey)
}