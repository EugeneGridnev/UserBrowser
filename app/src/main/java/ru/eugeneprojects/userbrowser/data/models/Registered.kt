package ru.eugeneprojects.userbrowser.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.OffsetDateTime

@Parcelize
data class Registered(
    val age: Int,
    val date: OffsetDateTime
) : Parcelable