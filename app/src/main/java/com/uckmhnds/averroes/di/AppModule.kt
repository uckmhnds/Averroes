package com.uckmhnds.averroes.di

import android.content.Context
import android.content.SharedPreferences
import com.uckmhnds.averroes.data.preferences.AppPreferenceKeys
import com.uckmhnds.averroes.data.preferences.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @AverroesAppPreferences
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            AppPreferenceKeys.LIST_or_GRID,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun providesAppPreferences(
        @AverroesAppPreferences sharedPreferences: SharedPreferences
    ): AppPreferences {
        return AppPreferences(sharedPreferences)

    }

}



@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class AverroesAppPreferences