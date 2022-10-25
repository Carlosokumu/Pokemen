package com.carlos.database.mapper

import com.carlos.database.entity.PokemonInfoEntity
import com.carlos.model.PokemonInfo


object PokemonInfoEntityMapper :EntityMapper<PokemonInfo,PokemonInfoEntity>{



    override fun asEntity(domain: PokemonInfo): PokemonInfoEntity {

    }

    override fun asDomain(entity: PokemonInfoEntity): PokemonInfo {

    }
}