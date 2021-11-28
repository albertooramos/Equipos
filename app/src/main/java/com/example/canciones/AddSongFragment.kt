package com.example.canciones

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.canciones.databinding.FragmentAddSongBinding
import com.example.canciones.viewmodel.SongViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddSongFragment : Fragment() {

    private var _binding: FragmentAddSongBinding? = null
    private lateinit var viewModel: SongViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this)[SongViewModel::class.java]
        _binding = FragmentAddSongBinding.inflate(inflater, container, false)
        binding.Submit.setOnClickListener {
            addNewSong()
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addNewSong() {
        if (isEntryValid()) {
            viewModel.addNewSong(
                binding.title.text.toString(),
                binding.artist.text.toString(),
                binding.album.text.toString(),
                binding.year.text.toString()
            )
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun isEntryValid(): Boolean {
        val title = binding.title.text.toString()
        val author = binding.artist.text.toString()
        val album = binding.album.text.toString()
        val year = binding.year.text.toString()
        if (title.isBlank() && author.isBlank() && album.isBlank() && year.isBlank()) {
            Toast.makeText(activity, "Fill all fields", Toast.LENGTH_LONG).show()
            return false;
        }
        return true;
    }
}