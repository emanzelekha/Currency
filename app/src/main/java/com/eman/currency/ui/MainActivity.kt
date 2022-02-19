package com.eman.currency.ui

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.common.model.CountriesList
import com.ui.core.presentation.activity.DefaultBaseActivity
import dagger.hilt.android.AndroidEntryPoint
import eman.currency.R

@AndroidEntryPoint
class MainActivity : DefaultBaseActivity() {
     val _from = MutableLiveData<CountriesList>()
    val from: LiveData<CountriesList>
        get() = _from

     val _to = MutableLiveData<CountriesList>()
    val to: LiveData<CountriesList>
        get() = _to
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
