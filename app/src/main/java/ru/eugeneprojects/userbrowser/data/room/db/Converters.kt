package ru.eugeneprojects.userbrowser.data.room.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.eugeneprojects.userbrowser.data.models.Dob
import ru.eugeneprojects.userbrowser.data.models.Id
import ru.eugeneprojects.userbrowser.data.models.Location
import ru.eugeneprojects.userbrowser.data.models.Login
import ru.eugeneprojects.userbrowser.data.models.Name
import ru.eugeneprojects.userbrowser.data.models.Picture
import ru.eugeneprojects.userbrowser.data.models.Registered

class Converters {

    @TypeConverter
    fun fromDob(dob: Dob): String = Gson().toJson(dob)

    @TypeConverter
    fun toDob(dob: String): Dob = Gson().fromJson(dob, Dob::class.java)

    @TypeConverter
    fun fromId(id: Id): String = Gson().toJson(id)

    @TypeConverter
    fun toId(id: String): Id = Gson().fromJson(id, Id::class.java)

    @TypeConverter
    fun fromLocation(location: Location): String = Gson().toJson(location)

    @TypeConverter
    fun toLocation(location: String): Location = Gson().fromJson(location, Location::class.java)

    @TypeConverter
    fun fromLogin(login: Login): String = Gson().toJson(login)

    @TypeConverter
    fun toLogin(login: String): Login = Gson().fromJson(login, Login::class.java)

    @TypeConverter
    fun fromName(name: Name): String = Gson().toJson(name)

    @TypeConverter
    fun toName(name: String): Name = Gson().fromJson(name, Name::class.java)

    @TypeConverter
    fun fromPicture(picture: Picture): String = Gson().toJson(picture)

    @TypeConverter
    fun toPicture(picture: String): Picture = Gson().fromJson(picture, Picture::class.java)

    @TypeConverter
    fun fromRegistered(registered: Registered): String = Gson().toJson(registered)

    @TypeConverter
    fun Registered(registered: String): Registered = Gson().fromJson(registered, Registered::class.java)

}