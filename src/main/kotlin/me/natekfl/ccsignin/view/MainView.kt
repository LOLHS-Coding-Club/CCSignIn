package me.natekfl.ccsignin.view

import javafx.scene.layout.BorderPane
import tornadofx.*

class MainView : View("CC Sign in") {
    init {
        shortcut("F11") {
            primaryStage.isFullScreen = !primaryStage.isFullScreen
        }
    }

    companion object {
        val frame = BorderPane().apply {
            bottom = TypeName
        }
    }

    override val root = frame
}