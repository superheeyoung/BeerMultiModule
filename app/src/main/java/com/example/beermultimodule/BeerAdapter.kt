package com.example.beermultimodule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beermultimodule.databinding.ItemBeerOptionBinding
import com.example.domain.Beer

class BeerAdapter(
    private val onClick: (Beer) -> Unit
) : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    var beerList = listOf<Beer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val bindingView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_beer_option, parent, false)
        return BeerViewHolder(ItemBeerOptionBinding.bind(bindingView), onClick)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(beerList[position])
    }

    class BeerViewHolder(
        var viewBinding: ItemBeerOptionBinding,
        val onClick: (Beer) -> Unit
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        private var currentBeer: Beer? = null

        init {
            viewBinding.clBeerOption.setOnClickListener {
                currentBeer?.let {
                    onClick(it)
                }
            }
        }

        fun bind(beerItem: Beer) {
            currentBeer = beerItem
            Glide.with(viewBinding.imgBeer)
                .load(beerItem.imgUrl)
                .into(viewBinding.imgBeer)
            viewBinding.tvName.text = beerItem.name
            viewBinding.tvTag.text = beerItem.tagline
        }
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

}