package smp.clone.doodle.presentation.viewmodel

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import smp.clone.doodle.model.PhoneAuthUser
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import androidx.core.content.edit
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import java.io.ByteArrayOutputStream

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
                signWithCredential(credential, context = activity)
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                Log.d("PhoneAuthViewModel", "onCodeSent: $id")
                _authState.value = AuthState.CodeSent(id)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.e("Phone Auth", "verification failed : ${exception.message}")
                _authState.value = AuthState.Error(exception.message ?: "Verification failed")

            }
        }
        val option = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(options)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(option)

    }

    private fun signWithCredential(credential: PhoneAuthCredential, context: Context) {
        _authState.value = AuthState.Loading
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
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
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Sign-in failed")
                }

            }

    }

    //used ktx here
    private fun markUserAsSignedIn(context: Context) {

        val sharedPreference = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreference.edit { putBoolean("isSignedIn", true) }
    }

    private fun fetchUserProfile(userId: String) {

        val userRef = userRef.child(userId)

        userRef.get().addOnSuccessListener { snapshot ->

            if (snapshot.exists()) {

                val userProfile = snapshot.getValue(PhoneAuthUser::class.java)
                if (userProfile != null) {

                    _authState.value = AuthState.Success(userProfile)
                }
            }

        }.addOnFailureListener {

            _authState.value = AuthState.Error("Failed to fetch user profile")

        }

        fun verifyCode(otp: String, context: Context) {

            val currentAuthState = _authState.value

            if (currentAuthState !is AuthState.CodeSent || currentAuthState.verificationID.isEmpty()) {

                Log.e("PhoneAuth", "Attempting to verify OTP without a valid verification ID")

                _authState.value = AuthState.Error("Verification not started or invalid ID")

                return
            }

            val credential = PhoneAuthProvider.getCredential(currentAuthState.verificationID, otp)
            signWithCredential(credential, context)
        }

        fun convertBitmapToBase64(bitmap: Bitmap): String {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
        }

        fun saveUserProfile(userId: String, name: String, status: String, profileImage: Bitmap?) {

            val database = FirebaseDatabase.getInstance().reference

            val encodedImage = profileImage?.let { convertBitmapToBase64(it) }
            val userProfile = PhoneAuthUser(
                userId = userId,
                name = name,
                status = status,
                phoneNumber = Firebase.auth.currentUser?.phoneNumber ?: "",
                profileImage = encodedImage
            )

            database.child("users").child(userId).setValue(userProfile)
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