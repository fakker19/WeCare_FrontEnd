package com.example.wecareapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wecareapp.model.Patient
import com.example.wecareapp.model.PatientResponse
import com.example.wecareapp.model.Specialist
import com.example.wecareapp.model.SpecialistResponse
import com.example.wecareapp.viewmodel.CreatePatientVM
import com.example.wecareapp.viewmodel.CreateSpecialistVM

class RegisterActivity2 : AppCompatActivity() {
    lateinit var viewModel: CreateSpecialistVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        val registro=findViewById<Button>(R.id.bt_crearC2)


      //  RegisterService.enviarWs(nombres,apellidos,correo,contrasena,esp,nrocol);
        initViewModel()
        registro.setOnClickListener(){
            createSpecialist()
            val intent = Intent(this, SelectorActivity::class.java).apply {
                //putExtra("Username",user.name)
            }
            startActivity(intent)
        }
    }
    private fun createSpecialist() {

        val Firstname=findViewById<AutoCompleteTextView>(R.id.tv_nombre1).text.toString()
        val Lastname=findViewById<AutoCompleteTextView>(R.id.tv_apellido1).text.toString()
        val Email=findViewById<AutoCompleteTextView>(R.id.tv_correo1).text.toString().replace(" ","")
        val Password=findViewById<AutoCompleteTextView>(R.id.tv_contraseña1).text.toString()
        val Esp=findViewById<AutoCompleteTextView>(R.id.tv_especialidad).text.toString()
        val Nrocol=findViewById<AutoCompleteTextView>(R.id.tv_nro_colegiatura).text.toString()


        val patient  = Specialist(Firstname, Lastname, Email, Password,Esp,Nrocol)
        viewModel.createNewSpecialist(patient)

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