package com.example.edgeofumpire

import android.app.Application
import com.example.edgeofumpire.data.database.AppDatabase

class EdgeOfUmpireApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.getDataBaseFromContext(applicationContext)
    }
}