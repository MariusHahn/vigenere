package wtf.hahn

import kotlin.math.abs


class Shifter {

    fun shift(input: Char, key: Char): Char {
        if (input < 'A' || input > 'Z') return input
        val shift = key.toInt() - 'A'.toInt()
        if (input.toInt() + shift <= 'Z'.toInt()) {
            return (input.toInt() + shift).toChar()
        }
        val overflow = input.toInt() + shift - 'Z'.toInt()
        return ('A'.toInt() + overflow - 1).toChar()
    }

    fun shiftDec(input: Char, key: Char): Char {
        if (input < 'A' || input > 'Z') return input
        val shift = key.toInt() - 'A'.toInt()
        if (input.toInt() - shift >= 'A'.toInt()) {
            return (input.toInt() - shift).toChar()
        }
        val overflow = abs(input.toInt() - key.toInt())
        return ('Z'.toInt() - overflow - 1).toChar()
    }

    fun encrypt(text: String, key: String): String {
        return text
                .mapIndexed { index, c -> shift(c, key[index]) }
                .joinToString("")
    }

    fun decrypt(chipper: String, key: String): String {
        return key
                .mapIndexed { index, c ->
                    shift(c, chipper[index])}
                .joinToString("")
    }
}