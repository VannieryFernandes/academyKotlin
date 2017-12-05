package com.example.vanniery.academymais.models

/**
 * Created by Vanniery on 22/11/2017.
 */


class Academia(val idAcademia: String, val name: String, val city: String, val street: String, val email: String
               , val img: String,val phone: String, val lat:String,val lng:String) {
    constructor() : this("", "", "", "", "", "", "", "", "") {}

}
