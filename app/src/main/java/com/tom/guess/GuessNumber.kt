package com.tom.guess

import kotlinx.android.synthetic.main.content_materal.*
import java.util.*

class GuessNumber() {
    var SecretNumber = Random().nextInt(10) + 1
    var count = 0

    fun validate(Number : Int) : Int {
        count ++
        return Number - SecretNumber
    }

    fun reset() {
        SecretNumber = Random().nextInt(10) + 1
        count = 0
    }
}

fun main() {
    val guessNumber = GuessNumber()
    println(guessNumber.SecretNumber)
    println(guessNumber.validate(3))
}