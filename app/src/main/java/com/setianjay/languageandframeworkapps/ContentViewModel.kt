package com.setianjay.languageandframeworkapps

import androidx.lifecycle.*
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity
import kotlinx.coroutines.launch

class ContentViewModel(private val repository: ContentRepository) : ViewModel() {

    fun insert(content: ContentEntity) = viewModelScope.launch {
        repository.insertContent(content)
    }

    fun delete(content: ContentEntity) = viewModelScope.launch {
        repository.deleteContent(content)
    }

    fun getContent(type: String): LiveData<List<ContentEntity>> = repository.getContent(type)
}