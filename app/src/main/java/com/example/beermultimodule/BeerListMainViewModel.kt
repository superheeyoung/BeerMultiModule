package com.example.beermultimodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Beer
import com.example.domain.GetBeerUseCase
import com.example.domain.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class BeerListMainViewModel
@Inject
constructor(private val getBeerUseCase: GetBeerUseCase) : ViewModel() {
    private val _stateBeerList = MutableStateFlow<Resource<List<Beer>>>(Resource.Empty)
    val stateBeerList : StateFlow<Resource<List<Beer>>> = _stateBeerList.asStateFlow()

    fun getBeerItem() {
        getBeerUseCase()
            .onEach { result ->
                _stateBeerList.value = result
            }
            .launchIn(viewModelScope)
    }
}