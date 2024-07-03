package com.emodiario.data.repository

import com.emodiario.data.remote.EmodiarioAPI
import com.emodiario.data.remote.dto.ActivityRequest
import com.emodiario.data.remote.dto.ActivityResponse
import com.emodiario.data.remote.dto.RatingRequest
import com.emodiario.data.remote.dto.RatingResponse
import com.emodiario.data.remote.dto.UserRequest
import com.emodiario.data.remote.dto.UserResponse
import javax.inject.Inject

class EmodiarioRepository @Inject constructor(private val api: EmodiarioAPI) {

    suspend fun login(email: String, password: String): Result<UserResponse> {
        return try {
            Result.success(
                api.login(
                    UserRequest(
                        email = email,
                        password = password
                    )
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(
        userRequest: UserRequest
    ): Result<UserResponse> {
        return try {
            Result.success(api.register(userRequest))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getActivities(
        userId: Int
    ): Result<List<ActivityResponse>> {
        return try {
            Result.success(api.getActivities(userId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createActivity(
        userId: Int,
        activity: ActivityRequest
    ): Result<ActivityResponse> {
        return try {
            Result.success(api.createActivity(userId, activity))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun newActivityRating(
        activityId: Int,
        rating: RatingRequest
    ): Result<Any> {
        return try {
            Result.success(api.newActivityRating(activityId, rating))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getActivityRatings(
        activityId: Int,
    ): Result<List<RatingResponse>> {
        return try {
            Result.success(api.getActivityRatings(activityId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUser(
        userId: Int
    ): Result<UserResponse> {
        return try {
            Result.success(api.getUser(userId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}