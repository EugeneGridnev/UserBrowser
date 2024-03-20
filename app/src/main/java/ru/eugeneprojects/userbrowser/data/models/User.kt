package ru.eugeneprojects.userbrowser.data.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "table_user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int? = null,
    val cell: String,
    @Embedded(prefix = "dob_")
    val dob: Dob,
    val email: String,
    val gender: String,
    @Embedded(prefix = "id_")
    val id: Id,
    @Embedded(prefix = "location_")
    val location: Location,
    @Embedded(prefix = "login_")
    val login: Login,
    @Embedded(prefix = "name_")
    val name: Name,
    val nat: String,
    val phone: String,
    @Embedded(prefix = "picture_")
    val picture: Picture,
    @Embedded(prefix = "registered_")
    val registered: Registered
) : Parcelable