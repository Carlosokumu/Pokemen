package com.carlos.database.mapper

import com.carlos.database.entity.PokemonInfoEntity
import com.carlos.model.PokemonInfo


object PokemonInfoEntityMapper : EntityMapper<PokemonInfo, PokemonInfoEntity> {


    override fun asEntity(domain: PokemonInfo): PokemonInfoEntity {

        return PokemonInfoEntity(
            name = domain.name,
            id = domain.id,
            hp = domain.HP,
            attack = domain.ATK,
            defence = domain.DEF,
            speed = domain.SPD,
            exp = domain.exp,
            height = domain.height,
            species = domain.species,
            weight = domain.weight,
            stats = domain.stats,
            types = domain.types
        )
    }

    override fun asDomain(entity: PokemonInfoEntity): PokemonInfo {
       return  PokemonInfo(
           name = entity.name,
           id= entity.id,
           HP = entity.hp,
           ATK = entity.attack,
           DEF = entity.defence,
           SPD = entity.speed,
           exp = entity.exp,
           height = entity.height,
           species = entity.species,
           weight = entity.weight,
           stats = entity.stats,
           types = entity.types
       )
    }

}
fun PokemonInfo.asEntity(): PokemonInfoEntity {
    return PokemonInfoEntityMapper.asEntity(this)
}

fun PokemonInfoEntity.asDomain(): PokemonInfo {
    return PokemonInfoEntityMapper.asDomain(this)
}