package com.example.vanniery.academymais

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast

class ConnectivityReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var cm:ConnectivityManager =context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager

        var netInfo = cm.activeNetworkInfo

        if(netInfo != null && netInfo.isConnectedOrConnecting){


        }else{
            val toast = Toast.makeText(context, "Opss, sem internet", Toast.LENGTH_SHORT)
            toast.show()
        }

    }
}
