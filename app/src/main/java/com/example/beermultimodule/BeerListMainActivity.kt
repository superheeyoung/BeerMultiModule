package com.example.beermultimodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beermultimodule.databinding.ActivityBeerListMainBinding
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.Resource
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeerListMainActivity : AppCompatActivity() {
    private val binding: ActivityBeerListMainBinding by lazy {
        ActivityBeerListMainBinding.inflate(layoutInflater)
    }

    private val beerAdapter: BeerAdapter by lazy {
        BeerAdapter {
            launchActivity<BeerDetailActivity>(
                BeerDetailActivity.EXTRA_BEER_NAME to it.name,
                BeerDetailActivity.EXTRA_BEER_TAGLINE to it.tagline,
                BeerDetailActivity.EXTRA_DESCRIPTION to it.description,
                BeerDetailActivity.EXTRA_IMAGE_URL to it.imgUrl,
            )
        }
    }

    private val beerListMainViewModel: BeerListMainViewModel by viewModels()

    private var isLoading = false
    private var isLastPage = false

    private val paginationScrollListener: PaginationScrollListener =
        object : PaginationScrollListener() {
            override fun loadMoreItems(page: Int) {
            }

            override fun getTotalPageCount() = 20

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beerListMainViewModel.getBeerItem()

        with(binding.rvBeer) {
            setHasFixedSize(true)
            addOnScrollListener(paginationScrollListener)
            layoutManager = LinearLayoutManager(this@BeerListMainActivity)
            adapter = beerAdapter
        }

        lifecycleScope.launchWhenStarted {
            launch {
                beerListMainViewModel.stateBeerList.collect { resource ->
                    when(resource) {
                        is Resource.Loading -> {

                            binding.prBar.isVisible = true
                        }
                        is Resource.Success -> {
                            binding.prBar.isVisible = false
                            isLastPage = resource.value.size < 20
                            isLoading = false

                            if (resource.value.isNotEmpty()) {
                                binding.tvEmptySearchResult.isVisible = false
                                binding.rvBeer.isVisible = true
                                beerAdapter.beerList = resource.value
                                beerAdapter.notifyDataSetChanged()
                            } else {
                                binding.tvEmptySearchResult.isVisible = true
                                binding.rvBeer.isVisible = false
                            }
                        }
                        else -> {
                            binding.prBar.isVisible = false
                        }
                    }
                }
            }
        }
    }
}