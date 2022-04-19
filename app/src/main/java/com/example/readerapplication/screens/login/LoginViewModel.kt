package com.example.readerapplication.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readerapplication.model.MUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPassword(email: String, password: String, onDone: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d("FB", "signInWithEmailAndPassword: Yaya")
                            onDone()
                        } else {
                            Log.d("FB", "signInWithEmailAndPassword: ${it.result}")
                        }
                    }
            } catch (exception: Exception) {
                Log.d("FB", "signInWithEmailAndPassword: ${exception.message}")
            }
        }

    fun createAccountWithEmailAndPassword(email: String, password: String, onDone: () -> Unit) {

        if (_loading.value == true) _loading.value = false

        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("FB", "createAccountWithEmailAndPassword: User creation Successful")
                        val displayName = it.result.user?.email?.split('@')?.get(0)
                        createUser(displayName)
                        onDone()

                    } else {
                        Log.d("FB", "createAccountWithEmailAndPassword: ${it.result}")
                    }
                }
        } catch (exception: Exception) {
            Log.d("FB", "createAccountWithEmailAndPassword: ${exception.message}")
        }

    }

    private fun createUser(displayName: String?) {

        val uid = auth.currentUser?.uid

        val user = MUser(
            userId = uid.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            profession = "Android Developer",
            quotes = "Yes I am enjoying this Job",
            id = ""
        ).toMap()

        try {
            FirebaseFirestore.getInstance()
                .collection("users")
                .add(user)
                .addOnSuccessListener {
                    _loading.value = false
                }.addOnFailureListener {
                    Log.d("FB", "createUser: ${it.message}")
                }
        }catch (exception: Exception){
            Log.d("FB", "createUser: couldn't create user: ${exception.message}")
        }
    }

}