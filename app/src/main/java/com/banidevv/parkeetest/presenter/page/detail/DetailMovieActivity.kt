package com.banidevv.parkeetest.presenter.page.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.banidevv.parkeetest.R
import com.banidevv.parkeetest.databinding.ActivityDetailMovieBinding
import com.banidevv.parkeetest.domain.uimodel.MovieUiModel
import com.banidevv.parkeetest.presenter.core.BaseActivity
import com.banidevv.parkeetest.presenter.core.UiState
import com.bumptech.glide.Glide
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding>() {

    private val viewModel by viewModels<DetailMovieViewModel>()

    private var movieUiModel : MovieUiModel? = null
    private var isFavorite = false
    private val reviewAdapter = ReviewAdapter()

    companion object {
        private const val MOVIE = "movie"

        fun start(context : Context, movieUiModel : MovieUiModel) {
            val intent = Intent(context, DetailMovieActivity::class.java)
            val movieString = Gson().toJson(movieUiModel)

            intent.putExtra(MOVIE, movieString)

            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieString = intent.getStringExtra(MOVIE)

        if (movieString != null) {
            movieUiModel = Gson().fromJson(movieString, MovieUiModel::class.java)
        }

        initialView()
        initialObserver()
    }

    private fun initialObserver() {
        viewModel.favoriteUiState.observe(this) { state ->
            when(state) {
                is UiState.Error -> {
                    showMessage(state.message)
                }
                is UiState.Loading -> {}
                is UiState.Success -> {
                    isFavorite = state.data
                    setFavorite()
                }
            }
        }

        viewModel.reviewsUiState.observe(this) { state ->
            when(state) {
                is UiState.Error -> {
                    showMessage(state.message)
                }
                is UiState.Loading -> {}
                is UiState.Success -> {
                    reviewAdapter.setItems(state.data)
                }
            }
        }
    }

    private fun initialView() {
        movieUiModel?.let { model ->
            Glide.with(this)
                .load(model.backdropUrl)
                .into(binding.ivMovies)

            binding.tvTitle.text = model.title
            binding.tvDesc.text = model.overview
            binding.tvRating.text = model.rating.toString()
            binding.rvReview.adapter = reviewAdapter

            binding.ivFavorite.setOnClickListener {
                if (isFavorite) {
                    viewModel.removeFromFavorite(model)
                } else {
                    viewModel.addToFavorite(model)
                }
            }

            viewModel.checkIsFavorite(model.id)
            viewModel.getMovieReviews(model.id)
        }
    }

    private fun setFavorite() {
        binding.ivFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                if (isFavorite) R.drawable.ic_favorite_filled
                else R.drawable.ic_favorite_stroke
            )
        )
    }

}