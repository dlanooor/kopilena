package com.application.kopilena.data.pemesanan

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

@Database(entities = [Pemesanan::class], version = 1, exportSchema = false)
abstract class PemesananDatabase : RoomDatabase() {
    abstract fun pemesananDao(): PemesananDao

    companion object {
        @Volatile
        private var INSTANCE: PemesananDatabase? = null

        fun getInstance(context: Context): PemesananDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, PemesananDatabase::class.java, "pemesanan.db").addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        INSTANCE?.let { pemesananDatabase ->
                            val pemesananDao = pemesananDatabase.pemesananDao()
                            CoroutineScope(Dispatchers.IO).launch {
                                fillWithStartingData(context, pemesananDao)
                            }
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }

        private fun fillWithStartingData(context: Context, dao: PemesananDao) {
            val pemesanan = loadJsonArray(context)
            try {
                if (pemesanan != null) {
                    for (i in 0 until pemesanan.length()) {
                        val item = pemesanan.getJSONObject(i)
                        dao.insertPemesanan(
                            Pemesanan(
                                item.getInt("kode"),
                                item.getString("nama"),
                                item.getString("jenis"),
                                item.getInt("jumlah"),
                                item.getInt("total")
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
            val `in` = context.resources.openRawResource(R.raw.pemesanan)
            val reader = BufferedReader(InputStreamReader(`in`))
            var line: String?
            try {
                while(reader.readLine().also { line = it } != null) {
                    builder.append(line)
                }
                val json = JSONObject(builder.toString())
                return json.getJSONArray("pemesanan")
            } catch (exception: IOException) {
                exception.printStackTrace()
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
            return null
        }
    }
}