package com.banidevv.parkeetest.presenter.page.main

import android.content.Intent
import com.banidevv.parkeetest.databinding.ItemPosterBinding
import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import com.banidevv.parkeetest.presenter.core.BaseRecyclerAdapter
import com.banidevv.parkeetest.presenter.page.detail.DetailMovieActivity
import com.bumptech.glide.Glide

class ItemPosterAdapter : BaseRecyclerAdapter<MovieUiModel, ItemPosterBinding>() {

    override fun ItemPosterBinding.bind(item: MovieUiModel, position: Int) {
        Glide.with(root.context)
            .load(item.backdropUrl)
            .into(root)

        root.setOnClickListener {
            DetailMovieActivity.start(root.context, item)
        }
    }
}