package com.example.vanniery.academymais

import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var nomeAcademiaText: TextView
    var br = ConnectivityReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        nomeAcademiaText = findViewById<TextView>(R.id.nomeAcademiaText)
        registerReceiver(br, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        var i = this.intent

        var nomeAcademia = i.getExtras().getString("NAME_ACADEMIA")
        var lat= i.getExtras().getString("LAT")
        var lng= i.getExtras().getString("LNG")

        nomeAcademiaText.setText(nomeAcademia)

        val latitude: Double = lat.toDouble()
        val longitude:Double = lng.toDouble()

        val academia = LatLng(latitude, longitude)
        mMap.addMarker(createMarkers(academia,nomeAcademia))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(academia,18.5f))
    }

    fun createMarkers(latLng: LatLng, title:String): MarkerOptions{
        return MarkerOptions()
                .position(latLng)
                .title(title)


    }
}
