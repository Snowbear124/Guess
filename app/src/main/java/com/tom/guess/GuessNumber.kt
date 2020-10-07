package com.tom.guess

import java.util.*

class GuessNumber() {
    val SecretNumber = Random().nextInt(10) + 1
    var count = 0

    fun validate(Number : Int) : Int {
        return Number - SecretNumber
    }
}

fun main() {
    val guessNumber = GuessNumber()
    println(guessNumber.SecretNumber)
    println(guessNumber.validate(3))
}