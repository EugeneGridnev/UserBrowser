package ru.eugeneprojects.userbrowser.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.eugeneprojects.userbrowser.api.UserAPI
import ru.eugeneprojects.userbrowser.util.Constants
import java.lang.reflect.Type
import java.time.OffsetDateTime

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    fun provideUserApi(gson: Gson): UserAPI = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(UserAPI::class.java)

    @Provides
    fun provideGson() = GsonBuilder().apply {
        registerTypeAdapter(OffsetDateTime::class.java, object:JsonDeserializer<OffsetDateTime> {
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): OffsetDateTime? =
                json?.let{
                    OffsetDateTime.parse(it.asJsonPrimitive.asString)
                }

        })
    }.create()
}