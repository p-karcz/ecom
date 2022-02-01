package com.karcz.piotr.ecom.data.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = "token")

@Singleton
class UserTokenDataStore @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun saveToken(token: String) {
        context.datastore.edit {
            it[TOKEN_KEY] = "Bearer $token"
        }
    }

    suspend fun getToken(): String? {
        return context.datastore.data.first()[TOKEN_KEY]
    }

    companion object {
        val TOKEN_KEY = stringPreferencesKey("token")
    }
}
