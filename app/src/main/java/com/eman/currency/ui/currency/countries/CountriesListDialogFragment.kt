package com.eman.currency.ui.currency.countries

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import eman.currency.databinding.FragmentCountriesListDialogListDialogBinding
import com.eman.currency.ui.MainActivity
import com.eman.currency.ui.currency.convert.ConvertIntent
import com.eman.currency.ui.currency.convert.ConvertViewModel
import com.domain.common.model.CountriesList
import com.domain.common.viewstate.currency.CountriesStateResult
import com.domain.core.viewstate.BaseVS
import com.domain.core.viewstate.Failure
import com.domain.core.viewstate.Loading
import com.ui.core.presentation.fragment.BaseBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

const val COUNTRY_TYPE = "countryType" // 1 from , 2 to

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CountriesListDialogFragment :
    BaseBottomSheetFragment<ConvertViewModel, ConvertIntent>(ConvertViewModel::class.java),
    SwipeRefreshLayout.OnRefreshListener, onClickCallBack {
    private lateinit var countriesAdapter: CountriesAdapter

    private var _binding: FragmentCountriesListDialogListDialogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun render(state: BaseVS) {
        super.render(state)
        when (state) {
            is Loading -> {

            }
            is Failure -> {
                errorMessage(state.message ?: "")
            }


            is CountriesStateResult -> {
                countriesAdapter.submitList(state.data)

            }

        }

    }

    private fun initRecyclerView() {
        countriesAdapter = CountriesAdapter(this)
        binding.list.apply {
            layoutManager = LinearLayoutManager(this@CountriesListDialogFragment.requireContext())
            adapter = countriesAdapter
        }

        binding.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == binding.list.adapter?.itemCount?.minus(1)) {
                    viewModel.countriesPage++
                    getCountries()
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCountriesListDialogListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getCountries()

        initRecyclerView()


    }

    private fun errorMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun getCountries() {
        sendIntent(ConvertIntent.CountriesList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRefresh() {

    }

    override fun onClick(countriesList: CountriesList) {
        if (arguments?.getInt(COUNTRY_TYPE) == 1) {
            (activity as MainActivity)._from.value = countriesList
        } else
            (activity as MainActivity)._to.value = countriesList
        this.dismiss()
    }
}
