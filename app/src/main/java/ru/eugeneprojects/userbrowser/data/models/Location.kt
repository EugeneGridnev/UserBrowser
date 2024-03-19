package ru.eugeneprojects.userbrowser.data.models

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val city: String,
    @Embedded(prefix = "coordinates_")
    val coordinates: Coordinates,
    val country: String,
    val postcode: String,
    val state: String,
    @Embedded(prefix = "street_")
    val street: Street,
    @Embedded(prefix = "timezone_")
    val timezone: Timezone
) : Parcelable