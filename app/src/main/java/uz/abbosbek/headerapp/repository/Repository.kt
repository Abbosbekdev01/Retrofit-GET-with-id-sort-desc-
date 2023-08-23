package uz.abbosbek.headerapp.repository

import retrofit2.Response
import uz.abbosbek.headerapp.api.RetrofitInstance
import uz.abbosbek.headerapp.models.Post

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number: Int): Response<Post> {
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPosts(userId: Int, sort: String, order: String): Response<List<Post>> {
        return RetrofitInstance.api.getCustomPosts(userId, sort, order)
    }
}