package com.emodiario.domain.preferences

import android.content.Context
import com.emodiario.domain.model.User
import org.json.JSONObject

class Prefs(val context: Context) {

    private val PREFS_FILENAME = "br.com.upcities"
    private val KEY_USER = "user"
    private val KEY_USER_LOGGED = "user_logged"

    private val prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    fun login(user: User) {
        usuario = user
        isLogged = true
    }

    fun logout() {
        usuario = null
        isLogged = false
    }

    var usuario: User?
        get() {
            val perfilJson = prefs.getString(KEY_USER, "")
            return if (perfilJson?.isNotEmpty() == true) {
                val jsonObject = JSONObject(perfilJson)
                User(
                    id = jsonObject.getInt("id"),
                    name = jsonObject.getString("name"),
                    email = jsonObject.getString("email"),
                    phoneNumber = jsonObject.getString("phoneNumber"),
                    photoUrl = jsonObject.getString("photoUrl"),
                )
            } else {
                null
            }
        }
        set(value) {
            if (value != null) {
                prefs.edit().putString(
                    KEY_USER,
                    JSONObject()
                        .put("id", value.id)
                        .put("name", value.name)
                        .put("email", value.email)
                        .put("phoneNumber", value.phoneNumber)
                        .put("photoUrl", value.photoUrl)
                        .toString()
                ).apply()
            } else {
                prefs.edit().remove(KEY_USER).apply()
            }
        }

    var isLogged: Boolean
        get() = prefs.getBoolean(KEY_USER_LOGGED, false)
        set(value) = prefs.edit().putBoolean(KEY_USER_LOGGED, value).apply()
}