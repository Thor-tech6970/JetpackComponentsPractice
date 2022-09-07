package com.example.jetpackcomponentspractice.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.jetpackcomponentspractice.repositories.MainActivityRepository
import com.example.jetpackcomponentspractice.viewModels.MainActivityViewModel

class MainViewModelFactory(val repository: MainActivityRepository , val page : Int) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return MainActivityViewModel(repository , page) as T
    }
}