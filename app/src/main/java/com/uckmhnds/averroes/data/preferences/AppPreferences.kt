package com.uckmhnds.averroes.data.preferences

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
){
    //

    private fun putString(key: String, value: String) =
        sharedPreferences.edit().putString(key, value).apply()

    private fun clear(key: String) = sharedPreferences.edit().remove(key).apply()

    private fun getString(key: String) =
        sharedPreferences.getString(key, "").toString()

    private fun getLiveString(key: String) =
        sharedPreferences.stringLiveData(key, "")

    private fun putInt(key: String, value: Int) =
        sharedPreferences.edit().putInt(key, value)

    private fun getLiveInt(key: String) =
        sharedPreferences.intLiveData(key, 0)

    //

    fun listView(){
        putString(AppPreferenceKeys.LIST_or_GRID, AppPreferenceKeys.LIST)
    }

    fun gridView(){
        putString(AppPreferenceKeys.LIST_or_GRID, AppPreferenceKeys.GRID)
    }

    fun getView(): String{
        return getString(AppPreferenceKeys.LIST_or_GRID)
    }

    fun getLiveView(): SharedPreferenceLiveData<String>{
        return getLiveString(AppPreferenceKeys.LIST_or_GRID)
    }

    //

    fun setNoteNumber(value: Int){
        putInt(AppPreferenceKeys.NOTE_NUMBER, value).apply()
    }

    fun getLiveNoteNumber(): LiveData<Int>{
        return getLiveInt(AppPreferenceKeys.NOTE_NUMBER)
    }

    //

    fun setTodoNumber(value: Int){
        putInt(AppPreferenceKeys.TODO_NUMBER, value).apply()
    }

    fun getLiveTodoNumber(): LiveData<Int>{
        return getLiveInt(AppPreferenceKeys.TODO_NUMBER)
    }

}