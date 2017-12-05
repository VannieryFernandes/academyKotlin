package com.example.vanniery.academymais.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vanniery.academymais.R

/**
 * A placeholder fragment containing a simple view.
 */
class FichaDeTreinoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ficha_de_treino, container, false)
    }
}
