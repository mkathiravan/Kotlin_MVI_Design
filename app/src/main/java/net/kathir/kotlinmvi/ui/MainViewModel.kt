package net.kathir.kotlinmvi.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import net.kathir.kotlinmvi.model.Users
import net.kathir.kotlinmvi.repository.MainRepository
import net.kathir.kotlinmvi.util.DataState

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle): ViewModel()
{

    private val _dataState : MutableLiveData<DataState<List<Users>>> = MutableLiveData()

    val dataState : LiveData<DataState<List<Users>>> get () = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent)
    {
        viewModelScope.launch {
            when(mainStateEvent)
            {
                is MainStateEvent.GetUserEvents -> {
                    mainRepository.getUsers()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)
                }

                is MainStateEvent.None -> {

                }
            }
        }
    }

}

sealed class MainStateEvent {

    object GetUserEvents : MainStateEvent()

    object None: MainStateEvent()
}