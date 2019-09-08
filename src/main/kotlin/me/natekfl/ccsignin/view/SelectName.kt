package me.natekfl.ccsignin.view

import javafx.geometry.Pos
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import me.natekfl.ccsignin.app.CCSignIn
import org.bson.Document
import org.bson.types.ObjectId
import tornadofx.*

class SelectName(val nameIds: Array<ObjectId>): VBox() {
    init {
        alignment = Pos.CENTER
        paddingBottom = 50

        label {
            text = "Please select your name"
            font = Font.font(75.0)
        }
        flowpane {
            alignment = Pos.CENTER
            hgap = 15.0
            vgap = 5.0
            for (nameId in nameIds) {
                val name = CCSignIn.namesCollection.find(Document("_id", nameId)).first() ?: continue
                button {
                    label {
                        text = "${name.getString("firstname")} ${name.getString("lastname")}"
                        font = Font.font(75.0)
                        action {
                            MainView.frame.bottom = ConfirmName(nameId)
                        }
                    }
                }
            }
            button {
                label {
                    text = "NOT HERE"
                    font = Font.font(75.0)
                    action {
                        MainView.frame.bottom = Rejected("It looks like you aren't signed up")
                    }
                }
            }
        }
    }
}