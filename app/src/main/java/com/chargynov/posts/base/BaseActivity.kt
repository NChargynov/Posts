package com.chargynov.posts.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<ViewModel : BaseViewModel>(private val layoutId: Int) :
    AppCompatActivity() {
    protected abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        setUpViews()
        setUpObservers()
    }

    abstract fun setUpViews()
    abstract fun setUpObservers()
}