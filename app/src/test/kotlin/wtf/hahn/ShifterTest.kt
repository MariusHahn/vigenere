package wtf.hahn

import kotlin.test.Test
import kotlin.test.assertEquals

class ShifterTest {
    @Test fun shiftTest() {
        val shifter = Shifter()
        assertEquals('B', shifter.shift('A', 'B'))
        assertEquals('C', shifter.shift('B', 'B'))
        assertEquals('A', shifter.shift('W', 'E'))
        assertEquals('B', shifter.shift('W', 'F'))
        assertEquals('R', shifter.shift('W', 'V'))
        assertEquals('D', shifter.shift('M', 'R'))
        assertEquals('H', shifter.shift('M', 'V'))
        assertEquals('A', shifter.shiftDec('B', 'B'))
        assertEquals('W', shifter.shiftDec('R', 'V'))


    }

    @Test fun encryptTest() {
        val text = "VIGENERE VIGENERE VIGENERE V"
        val key = "WIKIPEDI AFINDENW IRSEHRGU T"
        val chiper = "RQQMCIUM VNORQIEA DZYIUVXY O"
        assertEquals( expected =  chiper, actual =  Shifter().encrypt(text, key))



        assertEquals(expected = text, actual = Shifter().decrypt(chiper, key))
    }
}