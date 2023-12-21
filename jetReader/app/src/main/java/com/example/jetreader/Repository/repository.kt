package com.example.jetreader.Repository

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import com.example.jetreader.Model.modelUser
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.scopes.ViewModelScoped
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject


@ViewModelScoped
class repository @Inject constructor(
    val auth: FirebaseAuth, val firebaseFirestore: FirebaseFirestore
) {
    var TAG = "repository"


    fun signInWithEandP(email: String, password: String, onResult: (String) -> Unit = {}) {

        try{

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
//                        home()
                        onResult(task.result.user.toString())
                    } else {
                        Log.e(
                            "${TAG} => signInWithEmailAndPassword",
                            "addOnCompleteListener: ${task.result}",
                        )
                    }
                }
        } catch (e: Exception) {
            Log.e("${TAG} => signInWithEmailAndPassword: ", " $e ")
        }

    }


    fun signUpWithEmailAndPassword(email: String, password: String,onResult: (Boolean) -> Unit) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                    // me  @gmail.com
                    val displayName = it.result.user?.email?.split("@")?.get(0)
                    addUserToDatabase(displayName!!)
                    onResult(false)
//                    home()
//                    _loading.value = false
                }
        } catch (e: Exception) {
            Log.e(
                "${TAG} => createUserWithEmailAndPassword",
                "Error ${e.localizedMessage}",
            )
        }
    }

    fun addUserToDatabase(displayName: String) {
        try {
            val userId = auth.currentUser?.uid
            val user = modelUser(
                userId = userId.toString(),
                displayName = displayName,
                avatarUrl = "", quote = "", profession = "", id = ""
            ).toMap()

            user["user_id"] = userId.toString()
            user["display_name"] = displayName
            FirebaseFirestore.getInstance().collection("users")
                .add(user)

        } catch (e: Exception) {
            Log.e(TAG, "addUserToDatabase ${e.localizedMessage}")
        }
    }

    fun signInWithGoogle() {
        lateinit var mGoogleSignInClient: GoogleSignInClient

        val gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
//            .requestIdToken()
            .requestEmail()
            .build()

//        mGoogleSignInClient=GoogleSignIn.getClient(context,gso)

//        mGoogleSignInClient.apiOptions
    }


    suspend fun connection(): Boolean {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val url = URL("https://firebase.google.com/")
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        httpURLConnection.connectTimeout = 2000
        httpURLConnection.connect()
        if (httpURLConnection.responseCode == 200) {
            return true

        } else {
            Log.e(TAG, "connection: " + httpURLConnection.responseCode)
            return false
        }
    }

}