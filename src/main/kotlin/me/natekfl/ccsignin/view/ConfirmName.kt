package me.natekfl.ccsignin.view

import com.mongodb.client.model.Filters
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import me.natekfl.ccsignin.app.CCSignIn
import org.bson.Document
import org.bson.types.ObjectId
import tornadofx.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ConfirmName(val nameId: ObjectId) : VBox() {
    init {
        val name = CCSignIn.namesCollection.find(Document("_id", nameId)).first()!!

        alignment = Pos.CENTER
        paddingBottom = 50

        label {
            text = "${name.getString("firstname")} ${name.getString("lastname")}"
            font = Font.font(75.0)
        }
        label {
            text = "Is this you?"
            font = Font.font(50.0)
        }
        hbox {
            alignment = Pos.CENTER
            spacing = 15.0
            button {
                label {
                    text = "NO"
                    font = Font.font(75.0)
                    action {
                        MainView.frame.bottom = Rejected("It looks like you aren't signed up")
                    }
                }
            }
            button {
                label {
                    text = "YES"
                    font = Font.font(75.0)
                    action {
                        val records = CCSignIn.recordsCollection.find(Filters.and(Filters.gt("_id",
                                ObjectId(Date(System.currentTimeMillis() - 12 * 60 * 60 * 1000))),
                                Filters.eq("person", nameId)))
                        if (records.count() > 0) {
                            MainView.frame.bottom = Rejected("You've already signed in today")
                        } else {
                            CCSignIn.recordsCollection.insertOne(Document("person", nameId)
                                    .append("nameReadable", "${name.getString("firstname")} ${name.getString("lastname")}")
                                    .append("dateReadable", LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"))))
                            MainView.frame.bottom = Accepted()
                        }

                    }
                }
            }
        }
    }
}