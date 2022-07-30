package com.application.kopilena.data.barang

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

@Database(entities = [Barang::class], version = 1, exportSchema = false)
abstract class BarangDatabase : RoomDatabase() {
    abstract fun barangDao(): BarangDao

    companion object {
        @Volatile
        private var INSTANCE: BarangDatabase? = null

        fun getInstance(context: Context): BarangDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, BarangDatabase::class.java, "barang.db").addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        INSTANCE?.let { barangDatabase ->
                            val barangDao = barangDatabase.barangDao()
                            CoroutineScope(Dispatchers.IO).launch {
                                fillWithStartingData(context, barangDao)
                            }
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }

        private fun fillWithStartingData(context: Context, dao: BarangDao) {
            val pelanggan = loadJsonArray(context)
            try {
                if (pelanggan != null) {
                    for (i in 0 until pelanggan.length()) {
                        val item = pelanggan.getJSONObject(i)
                        dao.insertBarang(
                            Barang(
                                item.getInt("kode_barang"),
                                item.getString("nama_barang"),
                                item.getString("jenis_barang"),
                                item.getInt("jumlah"),
                                item.getInt("harga"),
                                item.getString("image")
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
            val `in` = context.resources.openRawResource(R.raw.barang)
            val reader = BufferedReader(InputStreamReader(`in`))
            var line: String?
            try {
                while(reader.readLine().also { line = it } != null) {
                    builder.append(line)
                }
                val json = JSONObject(builder.toString())
                return json.getJSONArray("barang")
            } catch (exception: IOException) {
                exception.printStackTrace()
            } catch (exception: JSONException) {
                exception.printStackTrace()
            }
            return null
        }
    }
}