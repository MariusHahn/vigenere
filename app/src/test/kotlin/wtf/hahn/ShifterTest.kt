package wtf.hahn

import kotlin.test.Test
import kotlin.test.assertEquals

class ShifterTest {
    @Test fun shiftTest() {
        val shifter = Shifter()
        assertEquals(shifter.shift('A', 'B'), 'B')
        assertEquals(shifter.shift('B', 'B'), 'C')
        assertEquals(shifter.shift('B', 'B'), 'C')
        assertEquals(shifter.shift('W', 'E'), 'A')
        assertEquals(shifter.shift('W', 'F'), 'B')
        assertEquals(shifter.shift('M', 'R'), 'D')
        assertEquals(shifter.shift('M', 'V'), 'H')
        //assertEquals(shifter.shiftDec('W', 'R'), 'V')
    }

    @Test fun encryptTest() {
        val text = "VIGENERE VIGENERE VIGENERE V"
        val key = "WIKIPEDI AFINDENW IRSEHRGU T"
        val chiper = "RQQMCIUM VNORQIEA DZYIUVXY O"
        assertEquals( expected =  chiper, actual =  Shifter().encrypt(text, key))



        //assertEquals(expected = text, actual = Shifter().decrypt(chiper, key))
    }
}