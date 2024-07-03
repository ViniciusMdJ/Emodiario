package com.emodiario.data.remote

import com.emodiario.data.remote.dto.ActivityRequest
import com.emodiario.data.remote.dto.ActivityResponse
import com.emodiario.data.remote.dto.RatingRequest
import com.emodiario.data.remote.dto.RatingResponse
import com.emodiario.data.remote.dto.UserRequest
import com.emodiario.data.remote.dto.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EmodiarioAPI {

    @POST("/api/usuarios/login")
    suspend fun login(
        @Body userRequest: UserRequest
    ): UserResponse

    @POST("/api/usuarios")
    suspend fun register(
        @Body userRequest: UserRequest
    ): UserResponse

    @GET("/api/usuarios/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): UserResponse

    @GET("/api/usuarios/{idUsuario}/categorias")
    suspend fun getActivities(
        @Path("idUsuario") userId: Int
    ): List<ActivityResponse>

    @POST("/api/usuarios/{idUsuario}/categorias")
    suspend fun createActivity(
        @Path("idUsuario") userId: Int,
        @Body activity: ActivityRequest
    ): ActivityResponse

    @POST("/api/categorias/{idCategoria}/avaliacoes")
    suspend fun newActivityRating(
        @Path("idCategoria") activityId: Int,
        @Body rating: RatingRequest
    ): Any

    @GET("/api/categorias/{idCategoria}/avaliacoes")
    suspend fun getActivityRatings(
        @Path("idCategoria") activityId: Int,
    ): List<RatingResponse>
}