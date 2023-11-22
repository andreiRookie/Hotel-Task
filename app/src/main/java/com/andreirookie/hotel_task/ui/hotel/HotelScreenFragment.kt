package com.andreirookie.hotel_task.ui.hotel

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.andreirookie.hotel_task.R
import com.andreirookie.hotel_task.databinding.HotelScreenLayoutBinding
import com.andreirookie.hotel_task.di.ActivityComponentHolder
import com.andreirookie.hotel_task.di.HotelScreenComponent
import com.andreirookie.hotel_task.di.appComponent
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelScreenFragment : Fragment() {

    private var _binding: HotelScreenLayoutBinding? = null
    private val binding get() = _binding!!

    private var _adapter: HotelPhotoSliderAdapter? = null
    private val adapter: HotelPhotoSliderAdapter get() = _adapter!!

    @Inject
    lateinit var viewModel: HotelScreenViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        HotelScreenComponent.getComponent(ActivityComponentHolder.getComponent(context.appComponent))
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _adapter = HotelPhotoSliderAdapter()
        _binding = HotelScreenLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBinding()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    render(state)
                }
            }
        }
    }
    private fun render(state: HotelScreenState) {
        when (state) {
            is HotelScreenState.Data -> {
                adapter.submitList(state.hotel.imageUrls)
                with(binding) {
                    progressBar.isVisible = false
                    hotelErrorGroup.isVisible = false

                    hotelNameTextView.text = state.hotel.name
                    hotelLocationTextView.text = state.hotel.address

                    startPriceTextView.text = getString(R.string.hotel_price_template, state.hotel.minimalPrice)
                    priceRemarkTextView.text = state.hotel.priceForIt

                    ratingBlock.ratingNumber.text = state.hotel.rating
                    ratingBlock.ratingAsWord.text = state.hotel.ratingName

                    detailsInfoBlock.descriptionTextView.text = state.hotel.aboutTheHotel.description
                    initChipGroup(state.hotel.aboutTheHotel.peculiarities)


                }
            }
            is HotelScreenState.Loading -> {
                binding.progressBar.isVisible = true
                binding.hotelErrorGroup.isVisible = false
            }
            is HotelScreenState.Error -> {
                binding.progressBar.isVisible = false
                binding.hotelErrorGroup.isVisible = true
            }
            is HotelScreenState.Init -> {}
        }
    }

    private fun initChipGroup(peculiarities: List<String>) {
        peculiarities.forEach { peculiarity ->
            val chip = Chip(this.context)
            chip.text = peculiarity
            binding.detailsInfoBlock.peculiaritiesChipGroup.addView(chip)
        }
    }

    private fun setBinding() {
        with(binding) {
            goToRoomsButton.defaultNavigateButton.text= getString(R.string.go_to_hotel_rooms_button_text)

            hotelPhotosSlider.adapter = adapter

            facilitiesInfoBlock.facilitiesLine.facilitiesLineIcon.setImageResource(R.drawable.ic_round_tag_faces_24)
            facilitiesInfoBlock.facilitiesLine.facilitiesLineTitleTextView.text = getString(R.string.facilities)
            facilitiesInfoBlock.includedLine.facilitiesLineIcon.setImageResource(R.drawable.ic_baseline_check_circle_24)
            facilitiesInfoBlock.includedLine.facilitiesLineTitleTextView.text = getString(R.string.included)
            facilitiesInfoBlock.excludedLine.facilitiesLineIcon.setImageResource(R.drawable.ic_baseline_cancel_24)
            facilitiesInfoBlock.includedLine.facilitiesLineTitleTextView.text = getString(R.string.excluded)

            errorGroupRetryButton.setOnClickListener {
                viewModel.loadHotel()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }

    companion object {
        fun newInstance(): HotelScreenFragment = HotelScreenFragment()
//            val args = Bundle()
//
//            val fragment = ()
//            fragment.arguments = args
//            return HotelFragment()

        const val TAG = "HotelFragment"
    }
}