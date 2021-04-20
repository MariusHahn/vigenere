package wtf.hahn

import kotlin.random.Random

class Crypt {

    fun keyGen(length: Int): String {
        return IntRange(1, length)
                .map { Random.nextInt('A'.toInt(),'Z'.toInt()+1).toChar() }
                .joinToString("" )
    }

    fun encrypt(text: String, key: String): String {
        val text = text.toUpperCase()
        val key = enlargeKey(text, key)
        return text.mapIndexed { index, c -> encrypt(c, key[index]) }.joinToString("")
    }

    fun decrypt(chipper: String, key: String): String {
        val chipper = chipper.toUpperCase()
        val key = enlargeKey(chipper, key)
        return chipper.toUpperCase().mapIndexed { index, c -> decrypt(c, key[index]) }.joinToString("")
    }

    fun encrypt(input: Char, key: Char): Char {
        return crypt(input, key, Math::addExact)
    }

    fun decrypt(input: Char, key: Char): Char {
        return crypt(input, key, Math::subtractExact)
    }

    private fun crypt(input: Char, key: Char, addOrSubtract: (i: Int, k: Int) -> Int): Char {
        if (input < 'A' || input > 'Z') return input
        val padding = 65
        val alphabetSize = 26
        val i: Int = (input - padding).toInt()
        val k: Int = (key - padding).toInt()
        val o = (alphabetSize + addOrSubtract(i, k)) % alphabetSize
        return (o + padding).toChar()
    }

    private fun enlargeKey(text: String, key: String): String {
        val longKey = key
                .toUpperCase()
                .replace(" ", "")
                .repeat((text.length / key.length).inc())
                .toMutableList()
        text.toList()
                .mapIndexed { index: Int, c: Char -> Pair(index, c) }
                .filter { x -> x.second < 'A' || x.second > 'Z' }
                .forEach { pair: Pair<Int, Char> -> longKey.add(pair.first, pair.second) }
        return longKey.joinToString("")
    }
}
