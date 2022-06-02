package com.luche.trymvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.luche.trymvi.databinding.ActivityMainBinding
import com.luche.trymvi.extension.hideKeyboard
import com.luche.trymvi.viewAction.MainViewAction
import com.luche.trymvi.viewModel.MainActViewModel
import com.luche.trymvi.viewState.MainActViewState
import com.luche.trymvi.viewState.MainActViewState.*
import com.luche.trymvi.viewState.MainActViewState.STATE.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        setupDataBinding()
        setupObservers()
        setupInitScreen()
        setupTextView()
        setupButtons()
    }

    private fun setupButtons() {
        binding.ivValidade.setOnClickListener {
            it.hideKeyboard()
            //
            viewModel.dispacherViewAction(
                MainViewAction.ValidateName(
                    binding.etName.text.toString().trim()
                )
            )
        }
    }

    private fun setupTextView() {
        binding.etName.doOnTextChanged { _, _, _, _ ->
            viewModel.dispacherViewAction(
                MainViewAction.UpdateName(binding.etName.text.toString())
            )
        }
    }

    private fun setupInitScreen() {
        viewModel.dispacherViewAction(
            MainViewAction.initScreen
        )
    }

    private fun setupObservers() {
        viewModel.viewState.state.observe(this, Observer { state ->
            when (state) {
                LOADING -> {
                    showToast(LOADING.toString())
                    hideSendButton()
                    startShimmer()
                }
                ERROR -> {
                    showToast(ERROR.toString())
                    stopShimmer()
                }
                SUCCESS -> {
                    showToast(SUCCESS.toString())
                    stopShimmer()
                }
                else -> {
                    showToast("Vizi")
                    stopShimmer()
                }
            }
        })
        viewModel.viewState.interaction.observe(this, Observer { interaction ->
            when (interaction) {
                ViewInteraction.HideSaveBtn -> {
                    hideSendButton()
                }
                is ViewInteraction.ShowErrorSnack -> showSnackBar(interaction.error)
                ViewInteraction.ShowSaveBtn -> {
                    binding.btnSendName.visibility = View.VISIBLE
                }
                is ViewInteraction.InvalidateNameError -> {
                    showSnackBar(interaction.error)
                    hideSendButton()
                }
                null -> showToast("Vizi interatction")
            }
        })
        viewModel.viewState.counter.observe(this, Observer {
            // binding.tvCounter.text = it.toString()
        })
    }

    private fun stopShimmer() {
        binding.sfLayout.showShimmer(false)
        binding.sfLayout.isVisible = false
    }

    private fun startShimmer() {
        binding.sfLayout.showShimmer(true)
        binding.sfLayout.isVisible = true
    }

    private fun hideSendButton() {
        binding.btnSendName.visibility = View.GONE
    }

    private fun showSnackBar(value: String) {
        Snackbar.make(
            binding.root,
            value,
            Snackbar.LENGTH_SHORT
        ).show()

    }

    private fun setupDataBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun showToast(text: String) = Toast.makeText(
        baseContext,
        text,
        Toast.LENGTH_SHORT
    ).show()
}