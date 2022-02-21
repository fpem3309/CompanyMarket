package com.example.companymarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private var firebaseAuth : FirebaseAuth? = null

    private var tv_email : EditText? = null
    private var tv_password : EditText? = null
    private var btn_join : Button? = null
    private var btn_login : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        tv_email = findViewById(R.id.tv_email)
        tv_password = findViewById(R.id.tv_password)
        btn_join = findViewById(R.id.btn_join)
        btn_login = findViewById(R.id.btn_login)

        btn_join?.setOnClickListener{
            joinEmail()
        }

        btn_login?.setOnClickListener{
            loginEmail()
        }
    }

    private fun loginEmail(){
        firebaseAuth!!.signInWithEmailAndPassword(tv_email?.text.toString(),tv_password?.text.toString())
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    var new_user = firebaseAuth?.currentUser
                    Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // 이 창을 닫음(LoginActivity)
                }else{
                    Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun joinEmail(){
        firebaseAuth!!.createUserWithEmailAndPassword(tv_email?.text.toString(),tv_password?.text.toString())
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"회원가입 성공",Toast.LENGTH_SHORT).show()
                    var new_user = firebaseAuth?.currentUser
                }else{
                    Toast.makeText(this,"회원가입 실패",Toast.LENGTH_SHORT).show()

                }
            }
    }

}