package com.example.secureappetp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var signIn: Button
    lateinit var uname:EditText
    lateinit var pass:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signIn=findViewById(R.id.SignInButton)
        uname=findViewById(R.id.loginuname)
        pass=findViewById(R.id.loginpassword)


        val userName:String
        signIn.setOnClickListener{
            if(uname.text.toString().equals("Taruna") && pass.text.toString().equals("123456")){
           var intent=Intent(this,landingpage::class.java)
            startActivity(intent)}
            else{
               Toast.makeText(this,pass.text.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}