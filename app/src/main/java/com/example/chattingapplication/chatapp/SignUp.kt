package com.example.chattingapplication.chatapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.chattingapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val name = edtName.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (!validateInputs(name, email, password)) return@setOnClickListener

            signUp(name, email, password)
        }
    }

    // Validate user inputs
    private fun validateInputs(name: String, email: String, password: String): Boolean {
        if (name.isEmpty()) {
            edtName.error = "Name is required"
            edtName.requestFocus()
            return false
        }
        if (email.isEmpty()) {
            edtEmail.error = "Email is required"
            edtEmail.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.error = "Enter a valid email"
            edtEmail.requestFocus()
            return false
        }
        if (password.isEmpty()) {
            edtPassword.error = "Password is required"
            edtPassword.requestFocus()
            return false
        }
        if (password.length < 6) {
            edtPassword.error = "Password must be at least 6 characters"
            edtPassword.requestFocus()
            return false
        }
        return true
    }

    // Create user with Firebase
    private fun signUp(name: String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    startActivity(Intent(this@SignUp, MainActivity::class.java))
                    finish()
                } else {
                    val errorMessage = task.exception?.message ?: "Some error occurred"
                    Toast.makeText(this@SignUp, errorMessage, Toast.LENGTH_LONG).show()
                    Log.e("SignUpError", errorMessage)
                }
            }
    }

    // Save user to Firebase Database
    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }
}
