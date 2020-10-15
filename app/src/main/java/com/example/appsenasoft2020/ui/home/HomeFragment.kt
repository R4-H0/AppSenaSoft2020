package com.example.appsenasoft2020.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appsenasoft2020.R
import com.example.appsenasoft2020.ScanKitActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val btnViaje: Button = root.findViewById(R.id.btnViaje1)

        btnViaje.setOnClickListener {
            //FirebaseAuth.getInstance().signOut()
            val intent = Intent (getActivity(), ScanKitActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        //homeViewModel.text.observe(viewLifecycleOwner, Observer {
         //   textView.text = it
        //})
        return root
    }
}