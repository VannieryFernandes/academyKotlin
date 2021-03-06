package com.example.vanniery.academymais

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.vanniery.academymais.models.Academia
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_informacao.*

class Informacao : AppCompatActivity() {

    var br = ConnectivityReceiver()
    lateinit var nomeAcademiaText:TextView
    lateinit var cityText:TextView
    lateinit var streetText:TextView
    lateinit var emailText:TextView
    lateinit var phoneText:TextView
    lateinit var imgView:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacao)
        setSupportActionBar(toolbar)

        registerReceiver(br, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        imgView = findViewById<ImageView>(R.id.img)

        nomeAcademiaText = findViewById<TextView>(R.id.nomeAcademiaText)
        cityText=findViewById<TextView>(R.id.cityText)
        streetText = findViewById<TextView>(R.id.streetText)

        emailText = findViewById<TextView>(R.id.emailText)
        phoneText= findViewById<TextView>(R.id.phoneText)

        var i = this.intent


        var nomeAcademia = i.getExtras().getString("NAME_ACADEMIA")
        var city= i.getExtras().getString("CITY")
        var street = i.getExtras().getString("STREET")
        var lat= i.getExtras().getString("LAT")
        var lng= i.getExtras().getString("LNG")
        var email = i.getExtras().getString("EMAIL")
        var phone = i.getExtras().getString("PHONE")
        var img =  i.getExtras().getString("IMG")

        Picasso.with(this).load(img).into(imgView)
        nomeAcademiaText.setText(nomeAcademia)
        cityText.setText(city)
        streetText.setText(street)

        emailText.setText(email)
        phoneText.setText(phone)

        
        fab.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view:View) {
                val i = Intent(this@Informacao, MapsActivity::class.java)
                    i.putExtra("NAME_ACADEMIA", nomeAcademia)
                    i.putExtra("LAT", lat)
                    i.putExtra("LNG", lng)


                    startActivity(i)
                }
        })
    }

}

