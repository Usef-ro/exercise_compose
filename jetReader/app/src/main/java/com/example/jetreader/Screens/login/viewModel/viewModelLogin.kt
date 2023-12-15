package com.example.jetreader.Screens.login.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetreader.Model.modelUser
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@ViewModelScoped
class viewModelLogin
@Inject constructor(

) : ViewModel() {

    val loadingState = MutableStateFlow(LoadingState.Idle)

    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)

    val loading: LiveData<Boolean> = _loading

    lateinit var mGoogleSignInClient: GoogleSignInClient


    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {

        if (_loading.value == false) {
            _loading.value = true

            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {

                        // me  @gmail.com
                        val displayName = it.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        home()
                        _loading.value = false
                    }
                    .addOnFailureListener {
                        Log.e(
                            "createUserWithEmailAndPassword",
                            "addOnFailureListener: ${it.localizedMessage}",
                        )
                        _loading.value = false

                    }
            } catch (e: Exception) {
                Log.e(
                    "createUserWithEmailAndPassword",
                    "Error ${e.localizedMessage}",
                )
            }


        }
    }

    private fun createUser(displayName: String?) {

        // create data in fireStore Database

        val userId = auth.currentUser?.uid
        val user = modelUser(
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "", quote = "", profession = "", id = ""
        ).toMap()

//        user["user_id"] = userId.toString()
//        user["display_name"]=displayName.toString()
        try {
            FirebaseFirestore.getInstance().collection("users")
                .add(user)
        } catch (e: Exception) {
            Log.e("createUser", "error ${e.localizedMessage}")
        }


    }

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        Log.e(
                            "signInWithEmailAndPassword",
                            "addOnSuccessListener: ${it.user}",
                        )
                    }
                    .addOnFailureListener {
                        Log.e(
                            "signInWithEmailAndPassword",
                            "addOnFailureListener: ${it.localizedMessage}",
                        )
                    }
                    .addOnCanceledListener {
                        Log.e(
                            "signInWithEmailAndPassword",
                            "addOnCanceledListener",
                        )
                    }

                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            home()
                        } else {
                            Log.e(
                                "signInWithEmailAndPassword",
                                "addOnCompleteListener: ${task.result}",
                            )
                        }
                    }
            } catch (e: Exception) {
                Log.e("signInWithEmailAndPassword: ", " $e ")
            }
        }

    fun signInWithGoogle(home: () -> Unit) {

        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
//            .requestIdToken()
            .requestEmail()
            .build()

//        mGoogleSignInClient=GoogleSignIn.getClient(context,gso)

//        mGoogleSignInClient.apiOptions
    }
}