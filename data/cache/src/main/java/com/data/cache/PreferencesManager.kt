package com.data.cache

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.domain.core.di.annotations.qualifiers.AppContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "PreferencesManager"

val Context.dataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class PreferencesManager @Inject constructor(@AppContext context: Context) {

    private val dataStore = context.dataStore

    val tokenFlow: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.LOGIN_USER_TOKEN] ?: ""
        }

    suspend fun saveUserToken(token: String?) = dataStore.edit { preferences ->
        preferences[PreferencesKeys.LOGIN_USER_TOKEN] = token ?: ""
    }

    val userPreferencesFlow: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.LOGIN_USER] ?: ""
        }

    suspend fun saveUser(userPreferences: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LOGIN_USER] = userPreferences
        }
    }

    val isLoginFlow: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.IS_LOGIN] ?: false
        }

    suspend fun saveLogin(isLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LOGIN] = isLogin
        }
    }

    fun stringFlow(key: String): Flow<String> {
        val preferencesKey = stringPreferencesKey(key)
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e(TAG, "Error reading preferences", exception)
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[preferencesKey] ?: ""
            }
    }

    suspend fun putString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    private object PreferencesKeys {
        val LOGIN_USER = stringPreferencesKey("login_user")
        val LOGIN_USER_TOKEN = stringPreferencesKey("login_user_token")
        val IS_LOGIN = booleanPreferencesKey("is_login")
    }
}