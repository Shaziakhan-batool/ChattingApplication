package com.example.chattingapplication.sharedpreferences

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapplication.databinding.ActivitySharedPreBinding


class SharedPreActivity : AppCompatActivity() {

        private lateinit var binding: ActivitySharedPreBinding
        private val PREFS_NAME = "myPrefs"
        private val KEY_TEXT = "savedText"

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySharedPreBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

            // Load text on app start
            val savedText = sharedPreferences.getString(KEY_TEXT, "")
            binding.tvOutput.text = savedText

            // Save Button Click
            binding.btnSave.setOnClickListener {
                val text = binding.etInput.text.toString()

                if (text.isNotEmpty()) {
                    sharedPreferences.edit().putString(KEY_TEXT, text).apply()
                    binding.tvOutput.text = text
                    binding.etInput.text.clear()

                    showToast("Data saved successfully!")
                } else {
                    showToast("Please enter some text")
                }
            }

            // Load Button Click
            binding.btnLoad.setOnClickListener {
                val loadedText = sharedPreferences.getString(KEY_TEXT, "")

                if (!loadedText.isNullOrEmpty()) {
                    binding.tvOutput.text = loadedText
                    showToast("Data loaded successfully!")
                } else {
                    showToast("No data found to load")
                }
            }
        }

        // Common Toast function
        private fun showToast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }



