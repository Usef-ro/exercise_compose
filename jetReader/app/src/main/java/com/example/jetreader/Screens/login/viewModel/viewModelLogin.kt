package com.example.jetreader.Screens.login.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetreader.Repository.repository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class viewModelLogin
@Inject constructor(
    val repository: repository
) : ViewModel() {

//    val loadingState = MutableStateFlow(LoadingState.Idle)


    private val _loading = MutableLiveData(false)

    val loading: LiveData<Boolean> = _loading


    var connected =
        mutableStateOf(false)

    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {


        if (_loading.value == false) {

           viewModelScope.launch {
               repository.signUpWithEmailAndPassword(email, password){
                   _loading.value = it
                   home()
               }
           }
            _loading.value = true


        }
    }

//    private fun createUser(displayName: String?) {
//
//        // create data in fireStore Database
//
//        viewModelScope.launch {
//            repository.addUserToDatabase(displayName!!)
//        }
//
//
//    }

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            repository.signInWithEandP(email, password) {
                home()
            }
        }

    fun signInWithGoogle(home: () -> Unit) {
        viewModelScope.launch {
            repository.signInWithGoogle()
        }
    }

    fun checkConnection() {
        viewModelScope.launch {
            connected.value = repository.connection()
            Log.e("PP", "checkConnection: ${connected.value}")

        }
    }
}