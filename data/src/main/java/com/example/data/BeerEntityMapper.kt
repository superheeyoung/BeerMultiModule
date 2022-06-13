package com.example.data

import com.example.domain.Beer
import javax.inject.Inject


class BeerEntityMapper @Inject constructor(){
    fun transform(target : List<BeerEntity>) : List<Beer> = with(target) {
        return map { beer->
            Beer(beer.name, beer.tagline, beer.description, beer.imgUrl ?: "https://images.punkapi.com/v2/80.png")
        }
    }
}