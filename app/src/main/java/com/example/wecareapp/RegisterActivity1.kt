package com.example.wecareapp

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        initViewModel()

        registro.setOnClickListener(){

            createPatient()
            val intent = Intent(this, SelectorActivity::class.java).apply {
                //putExtra("Username",user.name)
            }
            startActivity(intent)
        }
    }

    private fun createPatient() {
        val Firstname=findViewById<AutoCompleteTextView>(R.id.tv_nombre).text.toString()
        val Lastname=findViewById<AutoCompleteTextView>(R.id.tv_apellido).text.toString()
        val Email=findViewById<AutoCompleteTextView>(R.id.tv_correo).text.toString().replace(" ","")
        val Password=findViewById<AutoCompleteTextView>(R.id.tv_contraseña).text.toString()

        val patient  = Patient(Firstname, Lastname, Email, Password)
        viewModel.createNewPatient(patient)

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CreatePatientVM::class.java)
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


