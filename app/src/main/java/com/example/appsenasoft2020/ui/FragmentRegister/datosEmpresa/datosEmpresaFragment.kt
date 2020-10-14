package com.example.appsenasoft2020.ui.FragmentRegister.datosEmpresa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appsenasoft2020.R

class datosEmpresaFragment : Fragment() {

    private lateinit var datosEmpresaViewModel: datosEmpresaViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        datosEmpresaViewModel =
                ViewModelProvider(this).get(datosEmpresaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_register_empresa, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        datosEmpresaViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}