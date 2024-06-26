package ru.eugeneprojects.userbrowser.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Timezone(
    val description: String,
    val offset: String
) : Parcelable