package me.natekfl.ccsignin.app

import com.mongodb.client.MongoClients
import javafx.stage.Stage
import me.natekfl.ccsignin.view.MainView
import tornadofx.App

class CCSignIn: App(MainView::class, Styles::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        stage.isMaximized = true
        stage.isFullScreen = true
    }

    companion object Mongo {
        val client = MongoClients.create("mongodb+srv://natekfl:TP}25KDWz&qz@ccsignin-bioou.mongodb.net/signin?retryWrites=true&w=majority")
        val db = client.getDatabase("signin")
        val namesCollection = db.getCollection("names")
        val recordsCollection = db.getCollection("records")
    }
}