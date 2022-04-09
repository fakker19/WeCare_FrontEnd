package com.example.wecareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wecareapp.model.Specialist
import com.example.wecareapp.model.SpecialistResponse
import com.example.wecareapp.viewmodel.CreateSpecialistVM

class RegisterActivity2 : AppCompatActivity() {
    lateinit var viewModel: CreateSpecialistVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        val registro=findViewById<Button>(R.id.bt_crearC2)
        val Firstname=findViewById<EditText>(R.id.tv_nombre1)
        val Lastname=findViewById<EditText>(R.id.tv_apellido1)
        val Email=findViewById<EditText>(R.id.tv_correo1)
        val Password=findViewById<EditText>(R.id.tv_contraseña1)
        val ConfirmPassword=findViewById<EditText>(R.id.tv_confirm_password)
        val Esp=findViewById<EditText>(R.id.tv_especialidad)
        val Nrocol=findViewById<EditText>(R.id.tv_nro_colegiatura)

        //  RegisterService.enviarWs(nombres,apellidos,correo,contrasena,esp,nrocol);
        initViewModel()
        registro.setOnClickListener(){
            if(TextUtils.isEmpty(Firstname.text.toString()) &&
                TextUtils.isEmpty(Lastname.text.toString()) &&
                TextUtils.isEmpty(Email.text.toString()) &&
                TextUtils.isEmpty(Password.text.toString())  &&
                TextUtils.isEmpty(ConfirmPassword.text.toString()) &&
                TextUtils.isEmpty(Esp.text.toString()) &&
                TextUtils.isEmpty(Nrocol.text.toString())
                )
                {
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            }
            else if (TextUtils.isEmpty(Password.text.toString()) != TextUtils.isEmpty(ConfirmPassword.text.toString())){
                Toast.makeText(this,"Contraseña debe ser igual", Toast.LENGTH_SHORT).show()
            }
            else{
                createSpecialist()
                val intent = Intent(this, SelectorActivity::class.java).apply {
                    //putExtra("Username",user.name)
                }
                startActivity(intent)
            }
        }
    }
    private fun createSpecialist(){

        val Firstname=findViewById<EditText>(R.id.tv_nombre1).text.toString()
        val Lastname=findViewById<EditText>(R.id.tv_apellido1).text.toString()
        val Email=findViewById<EditText>(R.id.tv_correo1).text.toString()
        val Password=findViewById<EditText>(R.id.tv_contraseña1).text.toString()
        val ConfirmPassword=findViewById<EditText>(R.id.tv_confirm_password).text.toString()
        val Esp=findViewById<EditText>(R.id.tv_especialidad).text.toString()
        val Nrocol=findViewById<EditText>(R.id.tv_nro_colegiatura).text.toString()
        val  specialist = Specialist(Firstname, Lastname, Email, Esp,Nrocol,Password,ConfirmPassword)

        viewModel.createNewSpecialist(specialist)

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CreateSpecialistVM::class.java)
        viewModel.getCreateNewSpecialistObserver().observe(this, Observer <SpecialistResponse?>{

            if(it  == null) {
                Toast.makeText(this, "Failed to create User", Toast.LENGTH_LONG).show()
            } else {
                //{"code":201,"meta":null,"data":{"id":2877,"name":"xxxxxaaaaabbbbb","email":"xxxxxaaaaabbbbb@gmail.com","gender":"male","status":"active"}}
                Toast.makeText(this, "Successfully created User", Toast.LENGTH_LONG).show()
            }
        })
    }
}