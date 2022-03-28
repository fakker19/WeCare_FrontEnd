package com.example.wecareapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wecareapp.model.Event
import com.example.wecareapp.model.Patient
import com.example.wecareapp.model.PatientResponse
import com.example.wecareapp.viewmodel.CreatePatientVM



//import com.example.wecareapp.viewmodel.MainViewModelFactory
import retrofit2.Response


class RegisterActivity1 : AppCompatActivity() {
    lateinit var viewModel: CreatePatientVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var myResponse: MutableLiveData<Response<Patient>> = MutableLiveData()
        setContentView(R.layout.activity_register1)
        //    val policy = ThreadPolicy.Builder().permitAll().build()
        //      StrictMode.setThreadPolicy(policy)
        val registro=findViewById<Button>(R.id.bt_crearC1)
        var tv_name=findViewById<AutoCompleteTextView>(R.id.tv_nombre)
        var tv_lastname=findViewById<AutoCompleteTextView>(R.id.tv_apellido)
        var tv_mail=findViewById<AutoCompleteTextView>(R.id.tv_correo)
        var tv_password=findViewById<AutoCompleteTextView>(R.id.tv_contraseña)
        var tv_confirm_Password=findViewById<AutoCompleteTextView>(R.id.tv_confirm_password)

        initViewModel()
        registro.setOnClickListener(){
            if(TextUtils.isEmpty(tv_name.text.toString())){
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(tv_lastname.text.toString())){
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(tv_mail.text.toString())){
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(tv_password.text.toString()))
            {
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            } else if(TextUtils.isEmpty(tv_confirm_Password.text.toString()))
            {
                Toast.makeText(this,"Complete los datos", Toast.LENGTH_SHORT).show()
            }
            else if(tv_password!=tv_confirm_Password){
                Toast.makeText(this,"Contraseña debe ser igual", Toast.LENGTH_SHORT).show()
            }
            else{
                createPatient()
            }
            val intent = Intent(this, SelectorActivity::class.java).apply {
                //putExtra("Username",user.name)
            }
            startActivity(intent)
        }
    }

    private fun createPatient() {
        val Firstname=findViewById<AutoCompleteTextView>(R.id.tv_nombre).text.toString().replace(" ","")
        val Lastname=findViewById<AutoCompleteTextView>(R.id.tv_apellido).text.toString().replace(" ","")
        val Email=findViewById<AutoCompleteTextView>(R.id.tv_correo).text.toString().replace(" ","")
        val Password=findViewById<AutoCompleteTextView>(R.id.tv_contraseña).text.toString().replace(" ","")
        val ConfirPassowrd=findViewById<AutoCompleteTextView>(R.id.tv_confirm_password).text.toString().replace(" ","")

        val patient  = Patient(Firstname, Lastname, Email, Password, ConfirPassowrd)
        viewModel.createNewPatient(patient)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CreatePatientVM::class.java]
        viewModel.getCreateNewPatientObserver().observe(this, Observer <PatientResponse?>{

            if(it  == null) {
                Toast.makeText(this, "Failed to create User", Toast.LENGTH_LONG).show()
            } else {
                //{"code":201,"meta":null,"data":{"id":2877,"name":"xxxxxaaaaabbbbb","email":"xxxxxaaaaabbbbb@gmail.com","gender":"male","status":"active"}}
                Toast.makeText(this, "Successfully created User", Toast.LENGTH_LONG).show()
            }
        })
    }
}




/*  fun sendPostRequest(
      Firstname: String,
      Lastname: String,
      Email: String,
      Password: String,
  ) {

      var reqParam = URLEncoder.encode("Firstname", "UTF-8") + "=" + URLEncoder.encode(Firstname, "UTF-8")
      reqParam += "&" + URLEncoder.encode("Lastname", "UTF-8") + "=" + URLEncoder.encode(Lastname, "UTF-8")
      reqParam += "&" + URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8")
      reqParam += "&" + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8")
      val mURL = URL("http://localhost:43654/identity/register_patient")

      with(mURL.openConnection() as HttpURLConnection) {
          // optional default is GET
          requestMethod = "POST"

          val wr = OutputStreamWriter(getOutputStream());
          wr.write(reqParam);
          wr.flush();

          println("URL : $url")
          println("Response Code : $responseCode")

          BufferedReader(InputStreamReader(inputStream)).use {
              val response = StringBuffer()

              var inputLine = it.readLine()
              while (inputLine != null) {
                  response.append(inputLine)
                  inputLine = it.readLine()
              }
              println("Response : $response")
          }
      }
  }*/





