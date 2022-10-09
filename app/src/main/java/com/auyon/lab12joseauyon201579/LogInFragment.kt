package com.auyon.lab12joseauyon201579
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
//import androidx.navigation.findNavController
import com.auyon.lab12joseauyon201579.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding
    private val firstViewModel : SessionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setListeners()
    }

    private fun setListeners() {
        binding.iniciarbtn.setOnClickListener {
            firstViewModel.checkIn()
            binding.iniciarbtn.visibility = View.GONE
            binding.loadProgress.visibility = View.VISIBLE
            requireView().findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToHomeFragment())

            }
    }
}