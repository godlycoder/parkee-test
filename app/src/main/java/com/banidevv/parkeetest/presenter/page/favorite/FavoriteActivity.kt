package com.banidevv.parkeetest.presenter.page.favorite

import android.os.Bundle
import androidx.activity.viewModels
import com.banidevv.parkeetest.databinding.ActivityFavoriteBinding
import com.banidevv.parkeetest.presenter.core.BaseActivity
import com.banidevv.parkeetest.presenter.core.UiState
import com.banidevv.parkeetest.presenter.page.main.ItemMovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>() {

    private val viewModel by viewModels<FavoriteViewModel>()

    private val moviesAdapter = ItemMovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialView()
        initialObserver()
    }

    private fun initialView() {
        binding.root.adapter = moviesAdapter
    }

    private fun initialObserver() {
        viewModel.moviesUiState.observe(this) { state ->
            when(state) {
                is UiState.Error -> {
                    showMessage(state.message)
                }
                is UiState.Loading -> {}
                is UiState.Success -> {
                    moviesAdapter.setItems(state.data)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getFavoriteMovies()
    }

}