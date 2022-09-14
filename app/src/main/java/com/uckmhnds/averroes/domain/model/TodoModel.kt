package com.uckmhnds.averroes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoModel (

    val id: Int,

    val text: String,

    val date: String,

    var done: Boolean

): Parcelable