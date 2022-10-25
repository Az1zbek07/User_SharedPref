package com.example.homeworkuser.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharedPrefManager(context: Context) {
    private val pref = context.getSharedPreferences("My Pref", Context.MODE_PRIVATE)
    private val edit = pref.edit()
    val gson = Gson()

    fun saveUser(user: User){
        val text = gson.toJson(user)
        edit.putString("user", text)
        edit.apply()
    }

    fun getUser(): User?{
        val json = pref.getString("user", null)
        val type: Type = object : TypeToken<User>() {}.type
        return gson.fromJson(json, type)
    }
}