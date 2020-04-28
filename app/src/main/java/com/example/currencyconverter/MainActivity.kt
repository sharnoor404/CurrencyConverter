package com.example.currencyconverter

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //first parameter corresponds to input which will be of the format string,here i dont wish to show any progress ti the user
    //therefore progress bar has been set void and the last parameter is output which is also a string
//async task performs a task on a seperate thread such that the working of this task doesnt disturb the overall working of the app
    inner class Download:AsyncTask<String,Void,String>(){
//downloading data would be done in the below function
        override fun doInBackground(vararg params: String?): String {
            TODO("Not yet implemented")
        }
//execution of downloaded data in done in the below function
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }


    }
}
