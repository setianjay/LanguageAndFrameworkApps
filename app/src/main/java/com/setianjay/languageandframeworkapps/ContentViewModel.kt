package com.setianjay.languageandframeworkapps

import androidx.lifecycle.*
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity
import kotlinx.coroutines.launch

class ContentViewModel(private val repository: ContentRepository) : ViewModel() {
    private val allLanguage: LiveData<List<ContentEntity>> =
        repository.allContentLanguages.asLiveData()
    private val allFramework: LiveData<List<ContentEntity>> =
        repository.allContentFramework.asLiveData()

    fun insert(content: ContentEntity) = viewModelScope.launch {
        repository.insertContent(content)
    }

    fun getContent(type: String): LiveData<List<ContentEntity>> {
        return if (type == "languages") {
            allLanguage
        } else {
            allFramework
        }
    }
}