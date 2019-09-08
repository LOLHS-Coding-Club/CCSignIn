package me.natekfl.ccsignin.view

import com.mongodb.client.model.Filters
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import me.natekfl.ccsignin.app.CCSignIn
import org.bson.types.ObjectId
import tornadofx.*
import java.util.regex.Pattern

object TypeName: VBox() {
    fun restart() {
        textBox.text = ""
        MainView.frame.bottom = this
    }

    val textBox: TextField = TextField()
    init {
        paddingBottom = 50

        hbox {
            alignment = Pos.CENTER
            paddingBottom = 20
            label {
                text = "Start typing your FIRST name"
                font = Font.font(30.0)
            }
        }
        hbox {
            alignment = Pos.CENTER
            paddingBottom = 20
            children.add(textBox.apply {
                font = Font.font(50.0)
                textProperty().addListener { _, _, new ->
                    text = new.toUpperCase()

                    val pattern = Pattern.compile("^"+ Pattern.quote(textBox.text), Pattern.CASE_INSENSITIVE);
                    val results = CCSignIn.namesCollection.find(Filters.regex("firstname", pattern))
                    if (results.count() == 1) {
                        MainView.frame.bottom = ConfirmName(results.first()!!.getObjectId("_id"))
                    } else if (results.count() == 0 && textBox.text.isNotEmpty()) {
                        MainView.frame.bottom = Rejected("It looks like you aren't signed up. (We have no name that starts with ${textBox.text})")
                    } else if (results.count() > 0 && results.first()?.getString("firstname")?.length == textBox.text.length) {
                        val names = mutableListOf<ObjectId>()
                        for (result in results) {
                            names.add(result.getObjectId("_id"))
                        }
                        MainView.frame.bottom = SelectName(names.toTypedArray())
                    }
                }
            })
        }

        flowpane {
            alignment = Pos.CENTER
            paddingBottom = 50
            paddingLeft = 25
            paddingRight = 25

            for (letter in 'A'..'Z') {
                button {
                    setPrefSize(100.0, 100.0)
                    label {
                        text = letter.toString()
                        font = Font.font(75.0)
                        action {
                            textBox.text = "${textBox.text}$letter"
                        }
                    }
                }
            }
            button {
                label {
                    text = "⌫"
                    font = Font.font(75.0)
                    action {
                        textBox.text = textBox.text.dropLast(1)
                    }
                }
            }
        }
    }
}