package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.PostModel
import com.kvw.technicaltestmediamonks.business.models.UserModel

interface PostRepository {
    suspend fun getPostsByUser(userModel: UserModel): List<PostModel>
}