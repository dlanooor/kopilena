package com.application.kopilena.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.application.kopilena.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        title = "Login"
        setContentView(binding.root)

        binding.tvAccount.setOnClickListener {
            binding.tvUserpass.isVisible = true
        }

        binding.loginButton.setOnClickListener {
            if (binding.userEditText.text.toString().equals("admin") && binding.passwordEditText.text.toString().equals("admin"))
                startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
}