package com.carlos.database.mapper

import com.carlos.database.entity.PokemonEntity
import com.carlos.model.Pokemon

object PokemonEntityMapper : EntityMapper<List<Pokemon>, List<PokemonEntity>> {

    override fun asEntity(domain: List<Pokemon>): List<PokemonEntity> {
        return domain.map {
            PokemonEntity(page = it.page, name = it.name, url = it.url,color = it.color)
        }
    }

    override fun asDomain(entity: List<PokemonEntity>): List<Pokemon> {
        return entity.map {
            Pokemon(page = it.page, url = it.url, name = it.name, color = it.color)
        }
    }


}
fun List<Pokemon>.asEntity(): List<PokemonEntity> {
    return PokemonEntityMapper.asEntity(this)
}

fun PokemonEntity.asDomain(): Pokemon {
    return Pokemon(page = this.page,url = this.url,name = this.name,color = this.color)
}