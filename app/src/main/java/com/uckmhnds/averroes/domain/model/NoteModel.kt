package com.uckmhnds.averroes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel(

    val id: Int,

    var title:String?,

    var text:String?,

    var date: String?,

    var category: String?,

): Parcelable
