package com.example.vanniery.academymais.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.vanniery.academymais.Informacao
import com.example.vanniery.academymais.R
import com.example.vanniery.academymais.models.Academia
import com.squareup.picasso.Picasso

/**
 * Created by Vanniery on 22/11/2017.
 */
class MyAdapter(val mCtx : Context , val layoutId:Int , val academiaList:List<Academia>)
    : ArrayAdapter<Academia>(mCtx,layoutId,academiaList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val convertView = layoutInflater.inflate(layoutId, null)

        val img = convertView.findViewById<ImageView>(R.id.img)
        val nome = convertView.findViewById<TextView>(R.id.tvNome)
        val endereco = convertView.findViewById<TextView>(R.id.tvEndereco)
        val cidade = convertView.findViewById<TextView>(R.id.tvCidade)


        val academia = academiaList[position]


        nome.text = academia.name
        endereco.text = academia.street
        cidade.text = academia.city

        Picasso.with(mCtx).load(academia.img).into(img)

        convertView.setOnClickListener(object:View.OnClickListener {


            override fun onClick(v: View) {
                //OPEN DETAIL
                openDetailActivity(academia.name, academia.city, academia.street, academia.lat, academia.lng,academia.email,academia.phone,academia.img)
            }
        })

        return convertView
    }

    private fun openDetailActivity(vararg details:String) {
        val i = Intent(mCtx, Informacao::class.java)
        i.putExtra("NAME_ACADEMIA", details[0])
        i.putExtra("CITY", details[1])
        i.putExtra("STREET", details[2])
        i.putExtra("LAT", details[3])
        i.putExtra("LNG", details[4])
        i.putExtra("EMAIL",details[5])
        i.putExtra("PHONE",details[6])
        i.putExtra("IMG",details[7])
        mCtx.startActivity(i)
    }


}