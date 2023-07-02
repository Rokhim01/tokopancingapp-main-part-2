package com.example.tokopancingapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokopancingapp.FirstFragmentDirections
import com.example.tokopancingapp.R
import com.example.tokopancingapp.aplication.PancingApp
import com.example.tokopancingapp.databinding.FragmentFirstBinding
import com.example.tokopancingapp.model.pancing

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val pancingviewmodel: pancingviewmodel by viewModels {
        PancingViewmodelFactory((applicationContext as PancingApp).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      _binding = FragmentFirstBinding.inflate(inflater, container, false)
      return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PancingListAdapter {pancing ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(pancing)
            findNavController().navigate(action)
        }

        binding.datarecyclerView.adapter = adapter
        binding.datarecyclerView.layoutManager = LinearLayoutManager(context)
        pancingviewmodel.allpancing.observe(viewLifecycleOwner) { pancings ->
            pancings.let {
                if (pancings.isEmpty()) {
                    binding.textView.visibility = View.VISIBLE
                    binding.imageView.visibility = View.VISIBLE
                } else{
                    binding.textView.visibility = View.GONE
                    binding.imageView.visibility = View.GONE
                }
                adapter.submitList(pancings)
            }
        }

        binding.addFAB.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(null)
            findNavController().navigate(action)
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}