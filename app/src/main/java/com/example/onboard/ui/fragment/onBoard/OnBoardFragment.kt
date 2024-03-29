package com.example.onboard.ui.fragment.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.onboard.R
import com.example.onboard.databinding.FragmentOnBoardBinding
import com.example.onboard.ui.adapter.OnBoardViewPagerAdapter
import com.example.onboard.utils.PreferenceHelper
import kotlin.Int
import kotlin.with

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private val preferenceHelper = PreferenceHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun initialize() = with(binding) {
        viewPager.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
        dotsIndicator.attachTo(viewPager)
        preferenceHelper.unit(requireContext())
        preferenceHelper.onceBoard = true
    }

    private fun setupListener() = with(binding.viewPager) {
        binding.skip.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 1, true)
            }
        }
        binding.txtStart.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardFragment_to_registrationFragment2)
        }
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.skip.isVisible = true
                        binding.txtStart.isVisible = false
                    }
                    1 -> {
                        binding.skip.isVisible = true
                        binding.txtStart.isVisible = false
                    }
                    2 -> {
                        binding.skip.isVisible = false
                        binding.txtStart.isVisible = true
                    }
                }
                super.onPageSelected(position)
            }
        })
    }
}
