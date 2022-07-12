package com.example.newsbit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.newsbit.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private var _binding : FragmentStartBinding? =null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.topHeadline.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_newsFragment)
        }
        binding.business.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_businessFragment)
        }
        binding.sports.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_sportsFragment)
        }
        binding.technology.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_technologyFragment)
        }
        binding.entertainment.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_entertainmentFragment)
        }
        binding.science.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_scienceFragment)
        }
        binding.health.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_healthFragment)
        }
        binding.general.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_generalFragment)
        }
    }
}