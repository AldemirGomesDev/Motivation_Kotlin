package com.example.motivation.mock

import com.example.motivation.util.MotivationConstants
import java.util.*

class  Phrase (val description: String, val category: Int)

fun Int.random(): Int = Random().nextInt(this)

class Mock {

    private val ALL = MotivationConstants.PHRASE.ALL
    private val SUN = MotivationConstants.PHRASE.SUN
    private val HAPPY = MotivationConstants.PHRASE.HAPPY

    private val mListPhrase: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez", HAPPY),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste", HAPPY),
        Phrase("A melhor maneira de prever o futuro é inventá-lo", SUN),
        Phrase("Você perde todas as chances que você não aproveita", SUN),
        Phrase("Quando está mais escuro, vemos mais estrelas", HAPPY),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso", SUN)
    )

    fun getPhrase(value: Int): String {
        val filtered = mListPhrase.filter { it -> (it.category == value || value == ALL) }

        val rand = filtered.size.random()

        return filtered[rand].description
    }
}