package com.example.chattingapplication.shareapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapplication.databinding.ActivityShareBinding

class ShareActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnWhatsApp.setOnClickListener {
            shareTextToApp("com.whatsapp")
        }

        binding.btnFacebook.setOnClickListener {
            shareTextToApp("com.facebook.katana")
        }

        binding.btnInstagram.setOnClickListener {
            shareTextToApp("com.instagram.android")
        }

        binding.btnSMS.setOnClickListener {
            sendSMS()
        }
    }

    private fun shareTextToApp(packageName: String) {
        val text = binding.etText.text.toString()
        if (text.isEmpty()) {
            showToast("Please enter text to share")
            return
        }
        try {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
                setPackage(packageName)
            }
            startActivity(intent)
        } catch (e: Exception) {
            showToast("App not installed")
        }
    }

    private fun sendSMS() {
        val phone = binding.etPhone.text.toString()
        val message = binding.etText.text.toString()
        if (phone.isEmpty() || message.isEmpty()) {
            showToast("Enter phone number and message")
            return
        }
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("sms:$phone")
            putExtra("sms_body", message)
        }
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
