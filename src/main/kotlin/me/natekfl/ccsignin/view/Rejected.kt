package me.natekfl.ccsignin.view

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import tornadofx.*


class Rejected(val reason: String) : VBox() {
    val restartText = Label()
    var secondsLeft = 5

    init {
        paddingBottom = 50
        spacing = 10.0
        alignment = Pos.CENTER
        label {
            text = reason
            font = Font.font(75.0)
            isWrapText = true
            textAlignment = TextAlignment.CENTER
            paddingLeft = 50
            paddingRight = 50
        }
        label {
            text = "Please ask an officer for assistance"
            font = Font.font(50.0)
        }
        button {
            add(restartText.apply {
                text = "RESTART"
                font = Font.font(75.0)
                action {
                    TypeName.restart()
                }
            })
        }
    }
}