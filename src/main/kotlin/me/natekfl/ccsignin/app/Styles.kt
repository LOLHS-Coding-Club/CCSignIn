package me.natekfl.ccsignin.app

import javafx.scene.control.ContentDisplay
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
        button {
            fontWeight = FontWeight.findByWeight(400)
            baseColor = Color.web("#212529")
            borderRadius = MultiValue(arrayOf(CssBox(0.px, 0.px, 0.px, 0.px)))

        }
    }
}