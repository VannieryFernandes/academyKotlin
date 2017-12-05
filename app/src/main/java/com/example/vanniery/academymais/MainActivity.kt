package com.example.vanniery.academymais

import android.app.ProgressDialog
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView

import com.example.vanniery.academymais.adapters.MyAdapter
import com.example.vanniery.academymais.models.Academia
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    var br = ConnectivityReceiver()
    lateinit var ref : DatabaseReference
    lateinit var academiaList:MutableList<Academia>
    lateinit var rvAcademias:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        registerReceiver(br,IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

        academiaList = mutableListOf()


        rvAcademias = findViewById(R.id.rvAcademias)

        ref = FirebaseDatabase.getInstance().getReference("Academias")


        ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()){
                    academiaList.clear()

                    for (e in p0.children){
                        val academia = e.getValue(Academia::class.java)
                        academiaList.add(academia!!)

                    }

                    val adapter = MyAdapter(this@MainActivity,R.layout.item_list,academiaList)
                    rvAcademias.adapter = adapter

                }
            }

        })




        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_ficha_de_treino) {
            var i = Intent(this@MainActivity, FichaDeTreino::class.java)
            startActivity(i)
            // Handle the camera action
        } else if (id == R.id.nav_perfil) {
            var i = Intent(this@MainActivity, Cadastro::class.java)
            startActivity(i)

        } else if (id == R.id.nav_ajuda) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://vanniery4.wixsite.com/academy")))

        } else if (id == R.id.nav_pagamento) {
            var i = Intent(this@MainActivity, Informacao::class.java)
            startActivity(i)

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            var intentEmail: Intent = Intent(Intent.ACTION_SENDTO,
                    Uri.fromParts("ACADEMY", "reclamacao@academy.com", null))
            intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Reclamaçao")
            intentEmail.putExtra(Intent.EXTRA_TEXT, "Sua reclamação é:")
            startActivity(Intent.createChooser(intentEmail, "Enviar email..."))

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true


    }

}