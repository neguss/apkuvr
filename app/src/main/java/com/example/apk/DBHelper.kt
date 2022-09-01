package com.example.apk

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper




class DBHelper(context: Context, factory:SQLiteDatabase.CursorFactory?):SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        //val query = ("CREATE TABLE " + TABLE_NAME + "(" + ID_COL + " INTEGER PRIMARY KEY, " + COL1 + " TEXT)")
        //db.execSQL(query)
        db.execSQL("CREATE TABLE OBJECT ( OBJ_ID INTEGER PRIMARY KEY, OBJ_NAME  TEXT)")
        db.execSQL("CREATE TABLE REQUIREMENTS ( REQ_ID INTEGER PRIMARY KEY, OBJ_ID INTEGER, REQ_NAME  TEXT, DOCUMENT TEXT, FOREIGN KEY(OBJ_ID) REFERENCES OBJECT(OBJ_ID))")
        db.execSQL("CREATE TABLE PERSON ( PERS_ID INTEGER PRIMARY KEY, PERS_NAME  TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists

        db.execSQL("DROP TABLE IF EXISTS OBJECT")
        db.execSQL("DROP TABLE IF EXISTS REQUIREMENTS")
        db.execSQL("DROP TABLE IF EXISTS PERSON")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS OBJECT")
        db.execSQL("DROP TABLE IF EXISTS REQUIREMENTS")
        db.execSQL("DROP TABLE IF EXISTS PERSON")
        onCreate(db)
    }


    fun addRowObj(obj : String, tab:String){
        val values = ContentValues()
        values.put("OBJ_NAME", obj)
        val db = this.writableDatabase
        db.insert(tab, null, values)
        db.close()
    }

    fun addRowReq(obj_id:Int, req : String, doc:String, tab:String){
        val values = ContentValues()
        values.put("OBJ_ID",obj_id)
        values.put("REQ_NAME", req)
        values.put("DOCUMENT", doc)
        val db = this.writableDatabase
        db.insert(tab, null, values)
        db.close()
    }

    fun addRowPers(pers : String, tab:String){
        val values = ContentValues()
        values.put("PERS_NAME", pers)
        val db = this.writableDatabase
        db.insert(tab, null, values)
        db.close()
    }

    fun getObject(): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM OBJECT", null)

    }

    fun getRequirement(obj_id:String): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT REQ_NAME FROM REQUIREMENTS WHERE OBJ_ID="+obj_id, null)

    }
    fun getPers(): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM PERSON", null)

    }



    companion object{
        private const val DATABASE_NAME = "DB1"
        private const val DATABASE_VERSION= 4
        val TABLE_NAME = "CATEGORY"
        val ID_COL = "ID"
        val COL1   = "OBJ_NAME"

    }
}
