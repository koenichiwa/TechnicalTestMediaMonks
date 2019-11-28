package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.CommentModel
import com.kvw.technicaltestmediamonks.business.models.PostModel
import com.kvw.technicaltestmediamonks.data.retrofit.services.CommentService

class CommentRepositoryImpl(private val commentService: CommentService) : CommentRepository {
    override suspend fun getCommentsByPost(postModel: PostModel): List<CommentModel> {
        return commentService.getByPost(postModel.id)
            .map { CommentModel(it.id, it.name, it.email, it.body) }
    }
}