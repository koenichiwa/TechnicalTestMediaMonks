package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.CommentModel
import com.kvw.technicaltestmediamonks.business.models.PostModel

interface CommentRepository {
    suspend fun getCommentsByPost(postModel: PostModel): List<CommentModel>
}