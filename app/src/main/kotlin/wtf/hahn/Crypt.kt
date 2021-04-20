package wtf.hahn

import kotlin.random.Random

class Crypt {

    fun keyGen(length: Int): String {
        return IntRange(1, length)
                .map { Random.nextInt('A'.toInt(),'Z'.toInt()+1).toChar() }
                .joinToString("" )
    }

    fun encrypt(text: String, key: String): String {
        return crypt(text, key, ::encrypt)
    }

    fun decrypt(chipper: String, key: String): String {
        return crypt(chipper, key, ::decrypt)
    }

    private fun crypt(text: String, key: String, cryptFunction: (input: Char, key: Char) -> Char ): String {
        val specialChars = getSpecialChars(text)
        val text = removeSpecialChars(specialChars, text)
        val key = cleanUpKey(key)
        val chipper = text.mapIndexed { index, c -> cryptFunction(c, key[index % key.length]) }.joinToString("")
        return insertSpecialChars(chipper, specialChars)
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

    private fun insertSpecialChars(chipper: String, specialChars: List<Pair<Int, Char>>): String {
        val chipperAsList = chipper.toMutableList()
        for (specialChar in specialChars) {
            chipperAsList.add(specialChar.first, specialChar.second)
        }
        return chipperAsList.joinToString("")
    }

    private fun removeSpecialChars(specialChars: List<Pair<Int, Char>>, text: String): String {
        val textAsList = text.toUpperCase().toMutableList()
        for (specialChar in specialChars) {
            textAsList.removeIf { c -> c == specialChar.second }
        }
        return textAsList.joinToString("")
    }

    private fun cleanUpKey(key: String) = key.toUpperCase().replace(" ", "")

    private fun getSpecialChars(text: String) = text.toUpperCase().toList()
            .mapIndexed { index: Int, c: Char -> Pair(index, c) }
            .filter { x -> x.second < 'A' || x.second > 'Z' }
}
