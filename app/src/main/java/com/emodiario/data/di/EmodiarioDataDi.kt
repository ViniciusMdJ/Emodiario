package com.emodiario.data.di

import android.util.Log
import com.emodiario.Constraints
import com.emodiario.data.remote.EmodiarioAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EmodiarioDataDi {
    @Provides
    @Singleton
    fun providesEmodiarioAPI(): EmodiarioAPI =
        Retrofit.Builder()
            .baseUrl(Constraints.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(RequestInterceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmodiarioAPI::class.java)
}

object RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.i("Interceptor", "Outgoing request to ${request.url} ${request.method}")
        Log.i("Interceptor", "Outgoing request body: ${request.getBodyAsString()}")
        return chain.proceed(request)
    }

    private fun Request.getBodyAsString(): String {
        val requestCopy = this.newBuilder().build()
        val buffer = Buffer()
        requestCopy.body?.writeTo(buffer)
        return buffer.readUtf8()
    }
}