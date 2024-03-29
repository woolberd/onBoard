package com.example.onboard.ui.fragment.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onboard.R
import com.example.onboard.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_PAGE_POSITION)) {
            0 -> {
                lottie.setAnimation(R.raw.lottie1)
                tvText.text = "Очень удобный функционал"
            }
            1 -> {
                lottie.setAnimation(R.raw.lottie2)
                tvText.text = "Быстрый, качественный продукт"
            }
            2 -> {
                lottie.setAnimation(R.raw.lottie3)
                tvText.text = "Куча функций и интересных фишек"
            }
        }
    }

    companion object {
        const val ARG_ONBOARD_PAGE_POSITION = "OnBoardPage"
    }
}