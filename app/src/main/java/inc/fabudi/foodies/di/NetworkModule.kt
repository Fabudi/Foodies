package inc.fabudi.foodies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import inc.fabudi.foodies.EnvVars
import inc.fabudi.foodies.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService =
        getRetrofit(okHttpClient).create(ApiService::class.java)

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(EnvVars.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @JvmStatic
    @get:Singleton
    @get:Provides
    val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder()
            .connectTimeout(3000, TimeUnit.MILLISECONDS)
            .readTimeout(200000, TimeUnit.MILLISECONDS)
            .writeTimeout(250000, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor {
                it.proceed(
                    it.request().newBuilder()
                        .header("User-Agent", EnvVars.userAgent)
                        .build()
                )
            }.addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            ).build()
}