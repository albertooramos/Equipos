package com.example.canciones

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.canciones.database.Song
import com.example.canciones.databinding.FragmentSongListBinding
import com.example.canciones.viewmodel.SongViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SongListFragment : Fragment() {

    private var _binding: FragmentSongListBinding? = null
    private lateinit var viewModel: SongViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.s
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this)[SongViewModel::class.java]
        _binding = FragmentSongListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val songObserver = Observer<List<Song>> {
            updateDisplayText(viewModel.mAllSongs.value)
            binding.allSongsList.text = viewModel.displayText
        }
        viewModel.mAllSongs.observe(viewLifecycleOwner, songObserver)

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[SongViewModel::class.java]
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        if(item.itemId == R.id.delete){
            viewModel.deleteAllSongs()
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_button, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //usar un texto crear y llamarlo
    fun updateDisplayText(songs: List<Song>?) {
        if (songs != null) {
            for (song in songs) {
                viewModel.displayText += "${song.title}\n${song.album}\n${song.author}\n${song.year}"
            }
        }
    }
}