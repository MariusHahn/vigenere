package wtf.hahn

import kotlin.test.Test
import kotlin.test.assertEquals

class CryptTest {
    @Test fun cryptTest() {
        val crypt = Crypt()
        assertEquals('B', crypt.encrypt('A', 'B'))
        assertEquals('C', crypt.encrypt('B', 'B'))
        assertEquals('A', crypt.encrypt('W', 'E'))
        assertEquals('B', crypt.encrypt('W', 'F'))
        assertEquals('R', crypt.encrypt('W', 'V'))
        assertEquals('D', crypt.encrypt('M', 'R'))
        assertEquals('H', crypt.encrypt('M', 'V'))
        assertEquals('A', crypt.decrypt('B', 'B'))
        assertEquals('W', crypt.decrypt('R', 'V'))
    }

    @Test fun encryptTest() {
        val text = "VIGENERE VIGENERE VIGENERE V"
        val key = "WIKIPEDI AFINDENW IRSEHRGU T"
        val cipher = "RQQMCIUM VNORQIEA DZYIUVXY O"
        assertEquals(cipher, Crypt().encrypt(text, key))
        assertEquals(text, Crypt().decrypt(cipher, key))
    }

    @Test fun encrypt2Test() {
        val text = "VIGENERE VIGENERE VIGENERE V"
        val key = "Wikipedia finden wir sehr gut"
        val cipher = "RQQMCIUM VNORQIEA DZYIUVXY O"
        assertEquals(cipher, Crypt().encrypt(text, key))
        assertEquals(text, Crypt().decrypt(cipher, key))
    }

    @Test fun encryptRealistic() {
        val text = """
            | Die Methode geht zurück auf die Tabula recta (lat. für „Quadratische Tafel“), in der die Buchstaben des
            | Alphabets in Zeilen geschrieben und bei jeder Zeile jeweils um einen Platz weiter nach links verschoben
            | werden. Diese wurde durch den deutschen Benediktinerabt Johannes Trithemius (1462–1516) im Jahr 1508 im 
            | fünften Band seines in lateinischer Sprache geschriebenen sechsbändigen Werkes Polygraphiae libri sex 
            | (Sechs Bücher zur Polygraphie) angegeben. In dem 1518 nach seinem Tode veröffentlichten Buch schlug er 
            | vor, nach jedem einzelnen Klartextbuchstaben zum nächsten Alphabet in seiner Tabula überzugehen und 
            | so alle verfügbaren Alphabete auszunutzen.
        """.trimMargin().toUpperCase()
        val key = "alphabetische Reihenfolge der Buchstaben"

        val chipper = Crypt().encrypt(text, key)

        val decrypt = Crypt().decrypt(chipper, key)
        assertEquals(text, decrypt)
    }

    @Test fun encrypt2Realistic() {
        val text = """
            | Die Methode geht zurück auf die Tabula recta (lat. für „Quadratische Tafel“), in der die Buchstaben des
            | Alphabets in Zeilen geschrieben und bei jeder Zeile jeweils um einen Platz weiter nach links verschoben
            | werden. Diese wurde durch den deutschen Benediktinerabt Johannes Trithemius (1462–1516) im Jahr 1508 im 
            | fünften Band seines in lateinischer Sprache geschriebenen sechsbändigen Werkes Polygraphiae libri sex 
            | (Sechs Bücher zur Polygraphie) angegeben. In dem 1518 nach seinem Tode veröffentlichten Buch schlug er 
            | vor, nach jedem einzelnen Klartextbuchstaben zum nächsten Alphabet in seiner Tabula überzugehen und 
            | so alle verfügbaren Alphabete auszunutzen.
        """.trimMargin()
        val key = "alphabetische Reihenfolge der Buchstaben"

        val chipper = Crypt().encrypt(text, key)

        val decrypt = Crypt().decrypt(chipper, key)
        assertEquals(text.toUpperCase(), decrypt)
    }

    @Test fun keyGenTest() {
        val keyGen = Crypt().keyGen(10)
        assertEquals(10, keyGen.length)
        assertEquals(26, Crypt().keyGen(10000).toList().toSet().size)
    }
}