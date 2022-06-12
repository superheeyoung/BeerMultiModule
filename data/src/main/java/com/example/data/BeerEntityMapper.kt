package com.example.data

import com.example.domain.Beer
import com.example.domain.Resource

class BeerEntityMapper {
    fun transform(target : List<BeerEntity>) : List<Beer> = with(target) {
        return map { beer->
            Beer(beer.name, beer.tagline, beer.description, beer.imgUrl ?: "https://images.punkapi.com/v2/80.png")
        }
    }
}