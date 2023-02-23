package com.example.onboard.ui.fragment.noteApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onboard.App
import com.example.onboard.R
import com.example.onboard.databinding.FragmentNoteAppBinding
import com.example.onboard.models.NoteAppModel
import com.example.onboard.ui.adapter.NoteAppAdapter

class NoteAppFragment : Fragment() {

    private lateinit var binding: FragmentNoteAppBinding
    private val noteAppAdapter = NoteAppAdapter(this::onItemClick)
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        setList()
        changeLayoutManager()
    }

    private fun initialize() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAppAdapter
        }
    }

    private fun changeLayoutManager() {
        if (App.preferenceHelper.onceBoard) {
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setList() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { list ->
            noteAppAdapter.setList(list as ArrayList<NoteAppModel>)
        }
    }

    private fun setupListener() {
        binding.btnToThirdFragLang.setOnClickListener {
            findNavController().navigate(R.id.action_noteAppFragment_to_noteAppDetailFragment)
        }
        binding.grid.setOnClickListener {
            App.preferenceHelper.onceBoard = false
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext() ,2)
            binding.grid.isVisible = false
            binding.linear.isVisible = true
        }
        binding.linear.setOnClickListener {
            App.preferenceHelper.onceBoard = true
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.grid.isVisible = true
            binding.linear.isVisible = false
        }
    }

    private fun onItemClick(model: NoteAppModel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Are you sure you want to delete?")
        builder.setPositiveButton(
            "Delete"
        ) { dialog, id ->
            App.appDataBase?.noteDao()?.delete(model)
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, id ->
            dialog.cancel()
        }
        builder.show()
    }
}
