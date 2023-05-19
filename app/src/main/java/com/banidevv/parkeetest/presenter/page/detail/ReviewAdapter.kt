package com.banidevv.parkeetest.presenter.page.detail

import com.banidevv.parkeetest.databinding.ItemReviewBinding
import com.banidevv.parkeetest.domain.uimodel.ReviewUiModel
import com.banidevv.parkeetest.presenter.core.BaseRecyclerAdapter
import com.bumptech.glide.Glide

class ReviewAdapter : BaseRecyclerAdapter<ReviewUiModel, ItemReviewBinding>() {

    override fun ItemReviewBinding.bind(item: ReviewUiModel, position: Int) {
        tvContent.text = item.content
        tvName.text = item.author

        Glide.with(root.context)
            .load(item.avatarUrl)
            .into(ivAvatar)
    }

}