package com.application.kopilena.data.pembayaran

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

@Database(entities = [Pembayaran::class], version = 1, exportSchema = false)
abstract class PembayaranDatabase : RoomDatabase() {
    abstract fun pembayaranDao(): PembayaranDao

    companion object {
        @Volatile
        private var INSTANCE: PembayaranDatabase? = null

        fun getInstance(context: Context): PembayaranDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, PembayaranDatabase::class.java, "pembayaran.db").addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        INSTANCE?.let { pembayaranDatabase ->
                            val pembayaranDao = pembayaranDatabase.pembayaranDao()
                            CoroutineScope(Dispatchers.IO).launch {
                                fillWithStartingData(context, pembayaranDao)
                            }
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }

        private fun fillWithStartingData(context: Context, dao: PembayaranDao) {
            val pembayaran = loadJsonArray(context)
            try {
                if (pembayaran != null) {
                    for (i in 0 until pembayaran.length()) {
                        val item = pembayaran.getJSONObject(i)
                        dao.insertPembayaran(
                            Pembayaran(
                                item.getInt("kode"),
                                item.getInt("total_bayar"),
                                item.getInt("total_uang"),
                                item.getInt("total_kembali")
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
            val `in` = context.resources.openRawResource(R.raw.pembayaran)
            val reader = BufferedReader(InputStreamReader(`in`))
            var line: String?
            try {
                while(reader.readLine().also { line = it } != null) {
                    builder.append(line)
                }
                val json = JSONObject(builder.toString())
                return json.getJSONArray("pembayaran")
            } catch (exception: IOException) {
                exception.printStackTrace()
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
            return null
        }
    }
}