package smp.clone.doodle.presentation.viewmodel

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import smp.clone.doodle.model.PhoneAuthUser
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: MutableStateFlow<AuthState> = _authState

    private val userRef = firebaseDatabase.reference.child("users")

    fun sendVerificationCode(phoneNumber: String, activity: Activity) {
        _authState.value = AuthState.Loading
        val options = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                            signWithCredential(credential,context = activity)
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                            super.onCodeSent(id, token)
                Log.d("PhoneAuthViewModel", "onCodeSent: $id")
                _authState.value = AuthState.CodeSent(id)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.e("Phone Auth","verification failed : ${exception.message}")
                _authState.value = AuthState.Error(exception.message ?: "Verification failed")

            }
        }

    }
    private fun signWithCredential(credential: PhoneAuthCredential,context: Context){
            _authState.value = AuthState.Loading
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener {task->
                if(task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val phoneAuthUser = PhoneAuthUser(
                        userId = user?.uid ?: "",
                        phoneNumber = user?.phoneNumber ?: "",
                        name = "",
                        status = ""
                    )
                    markUserAsSignedIn(context)
                    _authState.value = AuthState.Success(phoneAuthUser)

                    fetchUserProfile(user?.uid ?: "")
                }
                    else {
                        _authState.value = AuthState.Error(task.exception?.message ?: "Sign-in failed")
                    }

                }

        }
    }

}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: PhoneAuthUser) : AuthState()
    data class CodeSent(val verificationID: String) : AuthState()
    data class Error(val message: String) : AuthState()
}