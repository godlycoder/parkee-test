package com.banidevv.parkeetest.presenter.page.main

import android.content.Intent
import com.banidevv.parkeetest.databinding.ItemMoviesBinding
import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import com.banidevv.parkeetest.presenter.core.BaseRecyclerAdapter
import com.banidevv.parkeetest.presenter.page.detail.DetailMovieActivity
import com.bumptech.glide.Glide

class ItemMovieAdapter : BaseRecyclerAdapter<MovieUiModel, ItemMoviesBinding>() {
    override fun ItemMoviesBinding.bind(item: MovieUiModel, position: Int) {
        Glide.with(root.context)
            .load(item.backdropUrl)
            .into(ivMovies)

        tvTitle.text = item.title
        tvRating.text = item.rating.toString()

        root.setOnClickListener {
            DetailMovieActivity.start(root.context, item)
        }
    }
}