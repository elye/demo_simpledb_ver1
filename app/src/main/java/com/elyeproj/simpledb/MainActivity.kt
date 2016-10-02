package com.elyeproj.simpledb

import android.content.ContentValues
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DbHelper(this)
        loadData()
    }

    private fun loadData() {
        var result: String = ""
        val cursor = dbHelper.writableDatabase.query(DbHelper.DATABASE_TABLE, DbHelper.RESULT_COLUMNS,
                null, null, null, null, DbHelper.KEY_ID)
        val INDEX_COLUMN_NAME = cursor.getColumnIndexOrThrow(DbHelper.KEY_NAME)
        while (cursor != null && cursor.moveToNext()) { result += "${cursor.getString(INDEX_COLUMN_NAME)}-" }
        txt_all_data.text = result
    }

    fun insertData(view: View) {
        if (!TextUtils.isEmpty(edit_entry.text)) {
            val newValue = ContentValues()
            newValue.put(DbHelper.KEY_NAME, edit_entry.text.toString())
            dbHelper.writableDatabase.insert(DbHelper.DATABASE_TABLE, null, newValue)
            edit_entry.text.clear()
            loadData()
        }
    }

    fun clearData(view: View) {
        dbHelper.clearDb()
        loadData()
    }
}
