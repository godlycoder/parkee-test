package com.banidevv.parkeetest.presenter.page.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.banidevv.parkeetest.R
import com.banidevv.parkeetest.databinding.ActivityMainBinding
import com.banidevv.parkeetest.presenter.core.BaseActivity
import com.banidevv.parkeetest.presenter.core.UiState
import com.banidevv.parkeetest.presenter.page.favorite.FavoriteActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    private val popularAdapter = ItemPosterAdapter()
    private val topRatedAdapter = ItemMovieAdapter()
    private val nowPlayingAdapter = ItemMovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialView()
        initialObserver()

        viewModel.getPopularMovies()
        viewModel.getTopRatedMovies()
    }

    private fun initialView() {
        binding.rvPopular.adapter = popularAdapter
        binding.rvTopRated.adapter = topRatedAdapter
        binding.rvNowPlaying.adapter = nowPlayingAdapter
    }

    private fun initialObserver() {
        viewModel.popularUiState.observe(this) { state ->
            when(state) {
                is UiState.Error -> {
                    showMessage(state.message)
                }
                is UiState.Loading -> {

                }
                is UiState.Success -> {
                    popularAdapter.setItems(state.data)
                }
            }
        }

        viewModel.topRatedUiState.observe(this) { state ->
            when(state) {
                is UiState.Error -> {
                    showMessage(state.message)
                }
                is UiState.Loading -> {

                }
                is UiState.Success -> {
                    topRatedAdapter.setItems(state.data)
                }
            }
        }

        viewModel.topRatedUiState.observe(this) { state ->
            when(state) {
                is UiState.Error -> {
                    showMessage(state.message)
                }
                is UiState.Loading -> {

                }
                is UiState.Success -> {
                    nowPlayingAdapter.setItems(state.data)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_favorite) {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
        return true
    }

}