package wtf.hahn


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
        val padding = 65
        val alphabethSize = 26
        val i = input - padding
        val k = key - padding
        val o = (i - k + alphabethSize) % alphabethSize
        return (o + padding).toChar()
    }

    fun encrypt(text: String, key: String): String {
        return text
                .mapIndexed { index, c -> shift(c, key[index]) }
                .joinToString("")
    }

    fun decrypt(chipper: String, key: String): String {
        return chipper
                .mapIndexed { index, c -> shiftDec(c, key[index]) }
                .joinToString("")
    }
}