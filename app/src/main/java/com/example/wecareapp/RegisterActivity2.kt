package com.example.wecareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import android.view.View
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
        val Firstname=findViewById<AutoCompleteTextView>(R.id.tv_nombre1)
        val Lastname=findViewById<AutoCompleteTextView>(R.id.tv_apellido1)
        val Email=findViewById<AutoCompleteTextView>(R.id.tv_correo1)
        val Password=findViewById<AutoCompleteTextView>(R.id.tv_contraseña1)
        val ConfirmPassword=findViewById<AutoCompleteTextView>(R.id.tv_confirm_password)
        val Esp=findViewById<AutoCompleteTextView>(R.id.tv_especialidad)
        val Nrocol=findViewById<AutoCompleteTextView>(R.id.tv_nro_colegiatura)

        //  RegisterService.enviarWs(nombres,apellidos,correo,contrasena,esp,nrocol);
        initViewModel()
        registro.setOnClickListener(){
            if(TextUtils.isEmpty(Firstname.text.toString())){
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            }
            else{
                createSpecialist()
            }
            if(TextUtils.isEmpty(Lastname.text.toString())){
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(Password.text.toString()))
            {
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(ConfirmPassword.text.toString())) {
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            }
            else{
                createSpecialist()
            }
            if(TextUtils.isEmpty(Nrocol.text.toString())){
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            }
            else{
                createSpecialist()
            }
            if(Password!=ConfirmPassword){
                Toast.makeText(this,"Contraseña debe ser igual", Toast.LENGTH_SHORT).show()
            }
            else{
                createSpecialist()
            }

            val intent = Intent(this, SelectorActivity::class.java).apply {
                //putExtra("Username",user.name)
            }
            startActivity(intent)
        }
    }
    private fun createSpecialist(){

        val Firstname=findViewById<AutoCompleteTextView>(R.id.tv_nombre1).text.toString().replace(" ","")
        val Lastname=findViewById<AutoCompleteTextView>(R.id.tv_apellido1).text.toString().replace(" ","")
        val Email=findViewById<AutoCompleteTextView>(R.id.tv_correo1).text.toString().replace(" ","")
        val Password=findViewById<AutoCompleteTextView>(R.id.tv_contraseña1).text.toString().replace(" ","")
        val ConfirmPassword=findViewById<AutoCompleteTextView>(R.id.tv_confirm_password).text.toString().replace(" ","")
        val Esp=findViewById<AutoCompleteTextView>(R.id.tv_especialidad).text.toString().replace(" ","")
        val Nrocol=findViewById<AutoCompleteTextView>(R.id.tv_nro_colegiatura).text.toString().replace(" ","")
        val patient  = Specialist(Firstname, Lastname, Email, Esp,Nrocol,Password,ConfirmPassword)

        //var retorno = true

        /*if (Firstname.isEmpty()){
            Toast.makeText(this,
            "Coloque sus nombres en el campo",
            Toast.LENGTH_SHORT).show()
            retorno = false
        }*/
        viewModel.createNewSpecialist(patient)
        //return retorno;

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