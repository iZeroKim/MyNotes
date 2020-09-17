package com.example.room.model

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.Calendar.getInstance
import java.util.concurrent.Executors

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
public abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database")
                    //Prepopulate database
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            //Insert data on a background thread
                            GlobalScope.launch {
                                val noteDao: NoteDao? = null
                                noteDao?.insert(Note(0, "Kim", "Kim is nice", 1))
                                noteDao?.insert(Note(1 , "Sheila", "Sheila is lovely", 5))
                                noteDao?.insert(Note(2, "Dennis", "Dennis is stubborn", 2))
                            }
                        }
                    }).build()


                INSTANCE = instance
                return instance
            }

        }

    }
}