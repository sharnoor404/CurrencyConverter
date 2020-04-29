package com.example.currencyconverter

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONObject
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpCookie
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val downloadData=Download()
        try{
            //enter url below
            val url=""
            downloadData.execute(url)

        }catch(e:Exception){
            e.printStackTrace()

        }


    }

    //first parameter corresponds to input which will be of the format string,here i dont wish to show any progress ti the user
    //therefore progress bar has been set void and the last parameter is output which is also a string
//async task performs a task on a seperate thread such that the working of this task doesnt disturb the overall working of the app
    inner class Download:AsyncTask<String,Void,String>(){
//downloading data would be done in the below function
        //vararg stands for array of strings
        override fun doInBackground(vararg params: String?): String {
//the output of this function is a string ,therefore we will store all the downloaded in the string 'result'


            var result=""
            var url:URL
            val httpURLConnection:HttpURLConnection
//grabbing url and setting up connection for application


//the code for this may cause error incase url not found or if website changes its url
    //therefore it is better to embed the code in try and catch block

    try{
        url=URL(params[0])
//grabs url from inout parameter params
        httpURLConnection=url.openConnection() as HttpURLConnection

        val inputStream=httpURLConnection.inputStream

        val inputStreamReader=InputStreamReader(inputStream)
//input stream reader helps to read/interpret the downloaded data

        var data=inputStreamReader.read()
        //data stores the data that inputStream reader has read

        while(data>0){//loop runs till we have something left in data
            val character=data.toChar()
            result+=character
            data=inputStreamReader.read()

        }

        return result
        //return empty string if nothing is read as data

    }catch (e:Exception){
        e.printStackTrace()
        return result
    }


        }

//execution of downloaded data in done in the below function
        override fun onPostExecute(result: String?) {


    try{
        //just printing the downloaded content
        val jSONObject=JSONObject(result)
        println(jSONObject)
        //grabbing individual entities thorough JSON object
        val base=jSONObject.getString("base")
        println(base)
        val date=jSONObject.getString("date")
        println(date)
        val rates=jSONObject.getString("rates")
        println(rates)

        //to grab items that are particular to rates we have to dive deep into rates
        //therefore we create a new object
        val newJsonObject=JSONObject(rates)
        val chf=newJsonObject.getString("CHF")
        println(chf)
        val czk=newJsonObject.getString("CZK")
        val tl=newJsonObject.getString("TRY")



    }catch(e:Exception){
        e.printStackTrace()
    }


            super.onPostExecute(result)
        }


    }
}
