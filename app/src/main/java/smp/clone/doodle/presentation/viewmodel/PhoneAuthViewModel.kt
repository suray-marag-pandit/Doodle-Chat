package smp.clone.doodle.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import smp.clone.doodle.model.PhoneAuthUser
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(): ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: MutableStateFlow<AuthState> = _authState
}

sealed class AuthState{
    object Idle: AuthState()
    object Loading: AuthState()
    data class Success(val user: PhoneAuthUser): AuthState()
    data class CodeSent(val verificationID: String): AuthState()
    data class Error(val message: String): AuthState()
}