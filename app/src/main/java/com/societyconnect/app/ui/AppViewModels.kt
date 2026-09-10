package com.societyconnect.app.ui

import androidx.lifecycle.ViewModel
import com.societyconnect.app.data.AuthRepository
import com.societyconnect.app.data.ServiceRequest
import com.societyconnect.app.data.ServiceRequestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AuthUiState(
    val isLoading: Boolean = false,
    val loginError: String? = null,
    val registrationError: String? = null,
    val registrationComplete: Boolean = false
)

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String): Boolean {
        _uiState.value = _uiState.value.copy(isLoading = true, loginError = null)
        val successful = repository.login(email, password)
        _uiState.value = AuthUiState(loginError = if (successful) null else "Invalid email or password.")
        return successful
    }

    fun register(name: String, email: String, password: String) {
        val successful = repository.register(name, email, password)
        _uiState.value = AuthUiState(
            registrationError = if (successful) null else "Please complete all fields.",
            registrationComplete = successful
        )
    }

    fun clearRegistrationState() {
        _uiState.value = AuthUiState()
    }
}

data class ServiceRequestsUiState(
    val requests: List<ServiceRequest> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)

class ServiceRequestsViewModel(private val repository: ServiceRequestRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ServiceRequestsUiState())
    val uiState: StateFlow<ServiceRequestsUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = ServiceRequestsUiState(requests = repository.load(), isLoading = false)
    }

    fun submit(title: String, category: String, description: String): Boolean {
        if (title.isBlank() || description.isBlank()) {
            _uiState.value = _uiState.value.copy(error = "Please enter a title and description.")
            return false
        }
        val updated = _uiState.value.requests + ServiceRequest(title.trim(), category, description.trim())
        repository.save(updated)
        _uiState.value = ServiceRequestsUiState(requests = updated, isLoading = false)
        return true
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
