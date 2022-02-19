package com.eman.currency.ui.currency.convert

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.eman.currency.ui.MainActivity
import com.domain.common.model.ConvertCurrencyRequest
import com.domain.common.viewstate.currency.ConvertStateResult
import com.domain.common.viewstate.currency.CurrencyHistoryStateResult
import com.domain.core.viewstate.BaseVS
import com.domain.core.viewstate.Failure
import com.domain.core.viewstate.Loading
import com.ui.core.presentation.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import eman.currency.databinding.FragmentConvertBinding
import eman.currency.ui.currency.convert.ConvertFragmentDirections
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ConvertFragment :
    BaseFragment<ConvertViewModel, ConvertIntent>(ConvertViewModel::class.java),
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentConvertBinding
    private lateinit var historyAdapter: HistoryAdapter

    override fun render(state: BaseVS) {
        super.render(state)
        when (state) {
            is Loading -> {
                if (state.isLoading) {
                    binding.recyclerView.loading.visibility = View.VISIBLE
                } else
                    binding.recyclerView.loading.visibility = View.GONE

            }
            is Failure -> {

                when (state.type) {
                    CurrencyHistoryStateResult.TYPE -> {
                    }
                    ConvertStateResult.TYPE -> {
                    }
                }
            }
            is CurrencyHistoryStateResult -> {

                historyAdapter.submitList(state.data)
            }

            is ConvertStateResult -> {
                viewModel.price = state.data.price ?: 1.0
                clearData()

            }

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentConvertBinding.inflate(inflater, container, false)
        getHistory()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.swipeRefresh.setOnRefreshListener(this)
        navControl = Navigation.findNavController(view)

        initRecyclerView()
        binding.from.setOnClickListener {
            navControl.navigate(
                ConvertFragmentDirections.actionConvertFragmentToCountriesListDialogFragment(
                    1
                )
            )
        }

        binding.to.setOnClickListener {
            navControl.navigate(
                ConvertFragmentDirections.actionConvertFragmentToCountriesListDialogFragment(
                    2
                )
            )
        }
        (activity as MainActivity).from.observe(viewLifecycleOwner) {
            binding.from.text = "${it.currencyName}  (${it.currencySymbol})"
            viewModel._from.value = it
            getConvert()
        }
        (activity as MainActivity).to.observe(viewLifecycleOwner) {
            binding.to.text = "${it.currencyName}  (${it.currencySymbol})"
            viewModel._to.value = it
            getConvert()
        }

        binding.fromValue.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                viewModel.price.let {
                    if (s.isNotEmpty() && binding.fromValue.tag == null) {
                        binding.toValue.setTag("progrmer")
                        binding.toValue.setText(
                            viewModel.calculateValue(
                                it,
                                s.toString().toDouble()
                            ).toString()
                        )
                        binding.toValue.setTag(null)


                    } else if (s.isEmpty() && binding.fromValue.tag == null) {
                        clearData()


                    }

                }
            }
        })

        binding.toValue.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                viewModel.price.let {
                    if (s.isNotEmpty() && binding.toValue.tag == null) {
                        binding.fromValue.setTag("progrmer")
                        binding.fromValue.setText(
                            viewModel.calculateValue(
                                it,
                                s.toString().toDouble()
                            ).toString()
                        )
                        binding.fromValue.setTag(null)


                    } else if (s.isEmpty() && binding.toValue.tag == null) {
                        clearData()
                    }
                }
            }
        })

    }

    private fun getConvert() {
        if (viewModel.from.value != null && viewModel.to.value != null)
            sendIntent(
                ConvertIntent.ConvertCurrency(
                    ConvertCurrencyRequest(
                        "${viewModel.from.value?.currencyId}_${viewModel.to.value?.currencyId}",
                        1.0
                    )
                )
            )

    }

    private fun getHistory() {
//        sendIntent(ConvertIntent.HistoryConvert)
    }

    private fun initRecyclerView() {
        historyAdapter = HistoryAdapter()
        binding.recyclerView.historyRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@ConvertFragment.requireContext())
            adapter = historyAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.historyRecyclerview.adapter = null // clean references for memory leaks
    }

    override fun onRefresh() {
        getHistory()
        binding.recyclerView.swipeRefresh.isRefreshing = false

    }

    private fun clearData() {
        binding.toValue.setTag("progrmer")
        binding.fromValue.setTag("progrmer")
        binding.fromValue.setText("1")
        binding.toValue.setText(viewModel.price.toString())
        binding.toValue.setTag(null)
        binding.fromValue.setTag(null)
    }
}
