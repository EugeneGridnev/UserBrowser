package ru.eugeneprojects.userbrowser.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Registered(
    val age: Int,
    val date: String
) : Parcelable