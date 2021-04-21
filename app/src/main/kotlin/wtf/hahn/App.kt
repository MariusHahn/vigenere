package wtf.hahn

import java.awt.GridLayout
import java.lang.Exception

import javax.swing.*


fun main() {
    val window = JFrame("Crypt")
    window.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    window.setSize(1600, 900)
    val mainPanel = window.contentPane
    mainPanel.layout = GridLayout(0, 1)

    val plainTextPanel = JPanel()
    plainTextPanel.layout = GridLayout()
    plainTextPanel.add(JLabel("Input"))
    val plainTextField = JTextArea("input")
    plainTextField.lineWrap = true
    plainTextPanel.add(plainTextField)
    mainPanel.add(plainTextPanel)

    val keyPanel = JPanel()
    keyPanel.layout = GridLayout()
    keyPanel.add(JLabel("Key"))
    val keyField = JTextArea("key")
    keyField.lineWrap = true
    keyPanel.add(keyField)
    mainPanel.add(keyPanel)

    val chipperPanel = JPanel()
    chipperPanel.layout = GridLayout()
    chipperPanel.add(JLabel("Chipper"))
    val chipperField = JTextArea("chipper")
    chipperField.lineWrap = true
    chipperPanel.add(chipperField)
    mainPanel.add(chipperPanel)

    val buttonPanel = JPanel()
    buttonPanel.layout = GridLayout(0, 3)
    buttonPanel.add(JLabel())
    buttonPanel.add(JLabel("Key Length"))
    val keyLengthField = JTextField()
    buttonPanel.add(keyLengthField)
    val encryptButton = JButton("ENCRYPT")
    val decryptButton = JButton("DECRYPT")
    val keyGenButton = JButton("Generate Key")
    buttonPanel.add(encryptButton)
    buttonPanel.add(decryptButton)
    buttonPanel.add(keyGenButton)
    mainPanel.add(buttonPanel)

    encryptButton.addActionListener {
        chipperField.text = Crypt().encrypt(plainTextField.text.trim(), keyField.text.trim())
    }
    decryptButton.addActionListener {
        plainTextField.text = Crypt().decrypt(chipperField.text.trim(), keyField.text.trim())
    }

    keyGenButton.addActionListener {
        val length = try {
            keyLengthField.text.trim().toInt()
        } catch (e: Exception) {
            10
        }
        keyField.text = Crypt().keyGen(length)
    }

    window.isVisible = true
}
