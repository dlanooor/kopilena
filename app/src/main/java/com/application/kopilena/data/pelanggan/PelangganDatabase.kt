package com.application.kopilena.data.pelanggan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.application.kopilena.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Database(entities = [Pelanggan::class], version = 1, exportSchema = false)
abstract class PelangganDatabase : RoomDatabase() {
    abstract fun pelangganDao(): PelangganDao

    companion object {
        @Volatile
        private var INSTANCE: PelangganDatabase? = null

        fun getInstance(context: Context): PelangganDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, PelangganDatabase::class.java, "pelanggan.db").addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        INSTANCE?.let { pelangganDatabase ->
                            val pelangganDao = pelangganDatabase.pelangganDao()
                            CoroutineScope(Dispatchers.IO).launch {
                                fillWithStartingData(context, pelangganDao)
                            }
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }

        private fun fillWithStartingData(context: Context, dao: PelangganDao) {
            val pelanggan = loadJsonArray(context)
            try {
                if (pelanggan != null) {
                    for (i in 0 until pelanggan.length()) {
                        val item = pelanggan.getJSONObject(i)
                        dao.insertPelanggan(
                            Pelanggan(
                                item.getInt("id"),
                                item.getString("nama"),
                                item.getString("no_hp"),
                                item.getInt("no_antrian")
                            )
                        )
                    }
                }
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
        }

        private fun loadJsonArray(context: Context): JSONArray? {
            val builder = StringBuilder()
            val `in` = context.resources.openRawResource(R.raw.pelanggan)
            val reader = BufferedReader(InputStreamReader(`in`))
            var line: String?
            try {
                while(reader.readLine().also { line = it } != null) {
                    builder.append(line)
                }
                val json = JSONObject(builder.toString())
                return json.getJSONArray("pelanggan")
            } catch (exception: IOException) {
                exception.printStackTrace()
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
            return null
        }
    }
}