package com.bignerdranch.android.a7minuteworkoutkotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1 // This DATABASE Version
        private const val DATABASE_NAME = "SevenMinutesWorkout.db" // Name of the DATABASE
        private const val TABLE_HISTORY = "history" // Table Name
        private const val COLUMN_ID = "_id" // Column Id
        private const val COLUMN_COMPLETED_DATE = "completed_date" // Column for Completed Date
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_HISTORY_TABLE = ("CREATE TABLE " +
                TABLE_HISTORY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_COMPLETED_DATE
                + " TEXT)") // Create History Table Query.
        db?.execSQL(CREATE_HISTORY_TABLE) // Executing the create table query.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY") // It drops the existing history table
        onCreate(db) // Calls the onCreate function so all the updated tables will be created.
    }
}