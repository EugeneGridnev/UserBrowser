package ru.eugeneprojects.userbrowser.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Id(
    val name: String,
    val value: String?
) : Parcelable