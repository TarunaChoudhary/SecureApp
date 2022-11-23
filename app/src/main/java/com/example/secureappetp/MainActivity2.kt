package com.example.secureappetp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity2 : AppCompatActivity(){
    lateinit var mAuth:FirebaseAuth
    private lateinit var usrName:String
    private lateinit var pass:String
    private lateinit var registerButton:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        mAuth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        usrName= findViewById<EditText>(R.id.registeruname).text.toString()
        pass=findViewById<EditText>(R.id.registerpassword).text.toString()
        registerButton=findViewById(R.id.RegisterButton)

        registerButton.setOnClickListener{
            registeredUserData()
        }


    }



    private fun registeredUserData(){
        //input validation
        if(usrName.isEmpty()){
            findViewById<EditText>(R.id.registeruname).error = "Username required"
        }
        else{
            findViewById<EditText>(R.id.registeruname).error = null
        }
        if(Patterns.EMAIL_ADDRESS.matcher(usrName).matches()){
            findViewById<EditText>(R.id.registeruname).error = "Invalid email"
        }
        else{
            findViewById<EditText>(R.id.registeruname).error = null
        }
        if(pass.isEmpty()){
            findViewById<EditText>(R.id.registerpassword).error = "Password required"
        }
        else{
            findViewById<EditText>(R.id.registerpassword).error = null
        }
        if(pass.length<6){
            findViewById<EditText>(R.id.registerpassword).error ="Password should be >= 6 characters"
        }

        mAuth.signInWithEmailAndPassword(usrName,pass)
            .addOnCompleteListener{
            @Override
                fun onComplete(task:Task<AuthResult>){
                    if(task.isSuccessful){
                        val user= userData(usrName,pass)

                        FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().currentUser?.uid!!)
                            .setValue(user).addOnCompleteListener {
                                fun onComplete(task:Task<AuthResult>){
                                    if(task.isSuccessful){
                                        Toast.makeText(this,"Registered Successfully", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                    else{
                                        Toast.makeText(this,"Registered unsuccessfully", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            }
                    }
                }
            }
    }
}


