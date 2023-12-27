package com.singlepoint.weektvshow.ui.commonutils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MySharedPreferences(context: Context) {
    val FAV_KEY="favorites"
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveList(key: String, list: List<String>) {
        val jsonString = gson.toJson(list)
        sharedPreferences.edit().putString(key, jsonString).apply()
    }

    fun getList(key: String): List<String> {
        val jsonString = sharedPreferences.getString(key, null)
        return if (jsonString != null) {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(jsonString, type)
        } else {
            emptyList()
        }
    }
}
