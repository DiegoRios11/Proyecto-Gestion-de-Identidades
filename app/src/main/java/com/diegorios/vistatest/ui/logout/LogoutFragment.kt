package com.diegorios.vistatest.ui.logout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.diegorios.vistatest.databinding.FragmentLogoutBinding

class LogoutFragment : Fragment() {

    private var _binding: FragmentLogoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val logoutViewModel =
            ViewModelProvider(this).get(LogoutViewModel::class.java)

        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Observa el texto del ViewModel
        val textView: TextView = binding.textLogout
        logoutViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Configura el listener para el botón de logout
        binding.btnLogout.setOnClickListener {
            // Cierra la actividad cuando se presiona el botón
            requireActivity().finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

