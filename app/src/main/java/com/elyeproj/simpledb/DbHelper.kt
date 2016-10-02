package com.elyeproj.simpledb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DbHelper(context: Context) : SQLiteOpenHelper(context, DbHelper.DATABASE_NAME, null, DbHelper.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.w(TAG, "Upgrade from version $oldVersion to $newVersion")
        Log.w(TAG, "This is version 1, no DB to update")
    }

    fun clearDb() {
        writableDatabase.execSQL("DROP TABLE IF EXISTS $DATABASE_TABLE")
        onCreate(writableDatabase)
    }

    companion object {
        val KEY_ID = "_ID"
        val KEY_NAME = "NAME"
        val DATABASE_TABLE = "simpletable"
        var RESULT_COLUMNS = arrayOf(KEY_ID, KEY_NAME)

        private val TAG = DbHelper::class.java.simpleName

        private val DATABASE_NAME = "simpledatabase.sqlite"
        private val DATABASE_VERSION = 1

        private val DATABASE_CREATE =
                "CREATE TABLE $DATABASE_TABLE ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$KEY_NAME TEXT NOT NULL);"
    }
}
