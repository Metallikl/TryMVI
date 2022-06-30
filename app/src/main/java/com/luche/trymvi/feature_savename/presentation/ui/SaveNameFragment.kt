package com.luche.trymvi.feature_savename.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.luche.trymvi.R
import com.luche.trymvi.databinding.FragmentSaveNameBinding
import com.luche.trymvi.extension.hideKeyboard
import com.luche.trymvi.feature_savename.presentation.viewAction.SaveGuestAction
import com.luche.trymvi.feature_savename.presentation.viewModel.SaveGuestViewModel
import com.luche.trymvi.feature_savename.presentation.viewState.SaveGuestState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaveNameFragment : Fragment() {

    private lateinit var binding: FragmentSaveNameBinding
    private val viewModel: SaveGuestViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_save_name,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
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
                SaveGuestAction.ValidateGuest(
                    binding.etName.text.toString().trim()
                )
            )
        }
        //
        binding.btnSendName.setOnClickListener {
            viewModel.dispacherViewAction(
                SaveGuestAction.SendGuest(
                    binding.etName.text.toString().trim()
                )
            )
        }
    }

    private fun setupTextView() {
        binding.etName.doOnTextChanged { _, _, _, _ ->
            viewModel.dispacherViewAction(
                SaveGuestAction.UpdateGuest(binding.etName.text.toString())
            )
        }
    }

    private fun setupInitScreen() {
        viewModel.dispacherViewAction(
            SaveGuestAction.initScreen
        )
    }

    private fun setupObservers() {
        viewModel.viewState.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                SaveGuestState.STATE.INITIAL -> {
                    //stopShimmer()
                }
                SaveGuestState.STATE.LOADING -> {
                    //showToast(SaveGuestState.STATE.LOADING.toString())
                    hideSendButton()
                    //startShimmer()
                }
                SaveGuestState.STATE.ERROR -> {
                    //showToast(SaveGuestState.STATE.ERROR.toString())
                    //stopShimmer()
                }
                SaveGuestState.STATE.SUCCESS -> {
                    //showToast(SaveGuestState.STATE.SUCCESS.toString())
                    //stopShimmer()
                }
                else -> {
                    showToast("Vizi")
                    //stopShimmer()
                }
            }
        })
        viewModel.viewState.interaction.observe(viewLifecycleOwner, Observer { interaction ->
            when (interaction) {
                SaveGuestState.ViewInteraction.HideSaveBtn -> {
                    hideSendButton()
                }
                is SaveGuestState.ViewInteraction.ShowErrorSnack -> showSnackBar(interaction.error)
                SaveGuestState.ViewInteraction.ShowSaveBtn -> {
                    binding.btnSendName.visibility = View.VISIBLE
                }
                is SaveGuestState.ViewInteraction.InvalidateNameError -> {
                    showSnackBar(interaction.error)
                    hideSendButton()
                }
                is SaveGuestState.ViewInteraction.NameSuccessfullySaved -> {
                    showToast(
                        "${interaction.name} enviado com sucesso !"
                    )
                    lifecycleScope.launch{
                        delay(1000)
                        viewModel.dispacherViewAction(SaveGuestAction.initScreen)
                    }
                }
                null -> showToast("Vizi interatction")
            }
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
        requireContext(),
        text,
        Toast.LENGTH_SHORT
    ).show()
}