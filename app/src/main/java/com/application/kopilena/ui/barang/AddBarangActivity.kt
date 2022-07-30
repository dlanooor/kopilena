package com.application.kopilena.ui.barang

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.application.kopilena.data.barang.Barang
import com.application.kopilena.databinding.ActivityAddBarangBinding
import com.application.kopilena.ui.MainActivity
import java.io.ByteArrayOutputStream

class AddBarangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBarangBinding
    private lateinit var viewModel: AddBarangViewModel
    private lateinit var stringImage: String

    private val GALLERY_REQUEST_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Add Barang"
        val factory = BarangViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(AddBarangViewModel::class.java)

        binding.loadButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                val mimeTypes = arrayOf("image/jpeg", "image/png", "image/jpg")
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                onresult.launch(intent)
                binding.loadButton.text = "Image Selected"
            } else {
                requestPermission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }

        binding.addButton.setOnClickListener {
            val kodeBarang = binding.kodeBarangEditText.text.toString().toInt()
            val namaBarang = binding.namaBarangEditText.text.toString()
            val jenisBarang = binding.jenisBarangEditText.text.toString()
            val jumlah = binding.jumlahEditText.text.toString().toInt()
            val harga = binding.hargaEditText.text.toString().toInt()

            viewModel.insertBarang(
                Barang(
                    kodeBarang = kodeBarang,
                    namaBarang = namaBarang,
                    jenisBarang = jenisBarang,
                    jumlah = jumlah,
                    harga = harga,
                    image = stringImage
                )
            )
            startActivity(Intent(this@AddBarangActivity, MainActivity::class.java))
            finish()
        }
    }

    private val onresult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        Log.i("TAG", "This is the result: ${result.data} ${result.resultCode}")
        onResultReceived(GALLERY_REQUEST_CODE, result)
    }

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){ granted->
        if (!granted) Toast.makeText(this, "Permission Denied !! Try again", Toast.LENGTH_SHORT).show()
    }

    private fun onResultReceived(requestCode: Int, result: ActivityResult?){
        when(requestCode){
            GALLERY_REQUEST_CODE ->{
                if (result?.resultCode == Activity.RESULT_OK){
                    result.data?.data?.let{uri ->
                        Log.i("TAG", "onResultReceived: $uri")
                        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
                        val byteArrayOutputStream = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                        stringImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
                    }
                }else {
                    Log.e("TAG", "onActivityResult: error in selecting image")
                }
            }
        }
    }
}