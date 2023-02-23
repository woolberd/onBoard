package com.example.onboard.ui.fragment.noteApp.detail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.onboard.App
import com.example.onboard.R
import com.example.onboard.databinding.FragmentNoteAppDetailBinding
import com.example.onboard.models.NoteAppModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NoteAppDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteAppDetailBinding
    private var backgroundColor = "#1B1A1A"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteAppDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendData()
        setupListener()
    }

    private fun setupListener() {
        binding.arrow.setOnClickListener {
            findNavController().navigate(R.id.action_noteAppDetailFragment_to_noteAppFragment)
        }
            binding.btnRadioFirst.setOnClickListener {
                backgroundColor = "#1E1E1E"
            }
            binding.btnSecond.setOnClickListener {
                backgroundColor = "#EBE4C9"
            }
            binding.btnThird.setOnClickListener {
                backgroundColor = "#571818"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendData() = with(binding) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMMM HH:mm")
        val date = current.format(formatter)
        binding.tvData.text = date.toString()

        sendData.setOnClickListener {
            if (editText1.text.isNotEmpty() || editText2.text.isNotEmpty()) {
                val title = editText1.text.toString()
                val description = editText2.text.toString()
                val data = tvData.text.toString()

                App().getInstance()?.noteDao()
                    ?.insert(
                        NoteAppModel(
                            title,
                            description,
                            color = backgroundColor,
                            data = data,
                        )
                    )
                findNavController().navigateUp()
            }
        }
    }
}