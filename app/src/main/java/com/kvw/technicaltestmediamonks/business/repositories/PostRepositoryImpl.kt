package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.PostModel
import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.data.retrofit.services.PostService

class PostRepositoryImpl(private val postService: PostService) : PostRepository {
    override suspend fun getPostsByUser(userModel: UserModel): List<PostModel> {
        return postService.getByUser(userModel.id)
            .map { PostModel(it.id, it.title, it.body) }
    }
}