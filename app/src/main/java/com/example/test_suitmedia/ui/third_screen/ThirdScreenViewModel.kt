package com.example.test_suitmedia.ui.third_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.test_suitmedia.data.repository.UserRepository
import com.example.test_suitmedia.data.response.DataItem

class ThirdScreenViewModel(
    private val repository: UserRepository
) : ViewModel() {
    fun getUser(): LiveData<PagingData<DataItem>> = repository.getUser().cachedIn(viewModelScope)

}