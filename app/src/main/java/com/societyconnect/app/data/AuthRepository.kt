package com.societyconnect.app.data

import android.content.Context
import java.security.MessageDigest

/**
 * Local-only account storage for this assessment project. In a production app,
 * credentials must be handled by a secure backend rather than on-device storage.
 */
class AuthRepository(context: Context) {
    private val preferences = context.getSharedPreferences("society_connect_auth", Context.MODE_PRIVATE)

    fun register(name: String, email: String, password: String): Boolean {
        if (name.isBlank() || email.isBlank() || password.isBlank()) return false
        preferences.edit()
            .putString(KEY_NAME, name.trim())
            .putString(KEY_EMAIL, email.trim().lowercase())
            .putString(KEY_PASSWORD_HASH, password.sha256())
            .apply()
        return true
    }

    fun login(email: String, password: String): Boolean =
        email.trim().lowercase() == preferences.getString(KEY_EMAIL, null) &&
            password.sha256() == preferences.getString(KEY_PASSWORD_HASH, null)

    private fun String.sha256(): String = MessageDigest.getInstance("SHA-256")
        .digest(toByteArray())
        .joinToString("") { byte -> "%02x".format(byte) }

    private companion object {
        const val KEY_NAME = "name"
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD_HASH = "password_hash"
    }
}
