package com.auyon.lab12joseauyon201579

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.auyon.lab12joseauyon201579.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val secondViewModel: SecondViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setObservers()
    }

    private fun setListeners() {
        binding.btnSesionActiva.setOnClickListener {
            secondViewModel.checkIn()
        }
        binding.btnCerrar.setOnClickListener {
            secondViewModel.reset()
        }
    }


    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            secondViewModel.checkInStatus.collectLatest {
                handleCheckInStatus(it)
            }
        }
    }


        private fun handleCheckInStatus(status: SecondViewModel.CheckInStatus) {
            when (status) {
                is SecondViewModel.CheckInStatus.Missing -> {
                    binding.apply {
                        btnEmpty.visibility = View.INVISIBLE
                        imgSuccess.visibility = View.VISIBLE
                        textView3.text = "Sin resultados"
                    }
                }
                is SecondViewModel.CheckInStatus.Failure -> {
                    binding.apply {
                        btnFailure.visibility = View.INVISIBLE
                        imgFailure.visibility = View.VISIBLE
                        textView3.text = "¡Operación fallida!"
                    }
                }
                SecondViewModel.CheckInStatus.Loading -> {
                    binding.apply {
                        btnDefault.visibility = View.GONE
                        android.visibility = View.VISIBLE
                        textView3.text = "Selecciona una opción"
                    }
                }
                is SecondViewModel.CheckInStatus.Success -> {
                    binding.apply {
                        btnSuccess.visibility = View.GONE
                        imgSuccess.visibility = View.VISIBLE
                        textView3.text = "¡Operación exitosa!"


                    }
                }
            }
        }
    }