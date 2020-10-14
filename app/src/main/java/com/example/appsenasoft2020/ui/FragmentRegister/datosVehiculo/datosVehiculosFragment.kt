package com.example.appsenasoft2020.ui.FragmentRegister.datosVehiculo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appsenasoft2020.R

class datosVehiculosFragment : Fragment() {

    private lateinit var datosVehiculoViewModel: datosVehiculoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        datosVehiculoViewModel =
                ViewModelProvider(this).get(datosVehiculoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_register_conductor, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        datosVehiculoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}