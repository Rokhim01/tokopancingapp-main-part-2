package com.example.tokopancingapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tokopancingapp.R
import com.example.tokopancingapp.aplication.PancingApp
import com.example.tokopancingapp.databinding.FragmentSecondBinding
import com.example.tokopancingapp.model.pancing

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val pancingviewmodel: pancingviewmodel by viewModels {
        PancingViewmodelFactory((applicationContext as PancingApp).repository)
    }
    private val args: SecondFragmentArgs by navArgs()
    private var pancing: pancing? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pancing = args.pancing
        if (pancing != null) {
            binding.deleteButton.visibillity = View.VISIBLE
            binding.buttonSecond.text = "ubah"
        }
        val name = binding.namaeditTextText.text
        val address = binding.addresseditTextText.text
        val price = binding.priceText.text
        binding.buttonSecond.setOnClickListener {
            val pancing = pancing(0, name.toString(), address.toString(), price.toString())
            pancingviewmodel.insert(pancing)
            findNavController().popBackStack()  //untuk dismiss halaman ini
        }

        binding.deleteButton.setOnClickListener
        pancing?.let { pancingviewmodel.delete(it) }
        findNavController().popBackStack()
    }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class SecondFragmentArgs {

}
