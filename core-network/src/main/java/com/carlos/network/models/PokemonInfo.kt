package com.carlos.network.models

import com.google.gson.annotations.SerializedName
import kotlin.random.Random

class PokemonInfo(
    val name: String,
    @SerializedName("hp")
    val HP: Int = Random.nextInt(maxHp),
    @SerializedName("attack")
    val ATK: Int = Random.nextInt(maxAttack),
    @SerializedName("defence")
    val DEF: Int = Random.nextInt(maxDefense),
    @SerializedName("speed")
    val SPD: Int = Random.nextInt(maxSpeed),
    val exp: Int = Random.nextInt(maxExp),
    val height: Int,
    val weight: Int,
    val stats: List<Stats>
) {


    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpFloat(base_stat: Int): Float =  (base_stat/ maxHp).toFloat()
    fun getAttackString(): String = "$ATK/$maxAttack"
    fun getDefenseString(): String = "$DEF/$maxDefense"
    fun getSpeedString(): String = "$SPD/$maxSpeed"
    fun getExpString(): String = "$exp/$maxExp"

    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000
    }
}

data class Stats(val base_stat: Int,val stat: Stat)

data class Stat(val name: String)