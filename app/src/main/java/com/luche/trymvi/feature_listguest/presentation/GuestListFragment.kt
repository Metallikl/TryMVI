package com.luche.trymvi.feature_listguest.presentation

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luche.trymvi.R
import com.luche.trymvi.databinding.FragmentGuestListBinding
import com.luche.trymvi.extension.updateShimmerState
import com.luche.trymvi.feature_listguest.presentation.adapter.GuestListAdapater
import com.luche.trymvi.feature_listguest.presentation.viewaction.GuestListAction
import com.luche.trymvi.feature_listguest.presentation.viewintent.GuestListIntent
import com.luche.trymvi.feature_listguest.presentation.viewmodel.GuestListViewModel
import com.luche.trymvi.feature_listguest.presentation.viewstate.GuestListViewState
import com.luche.trymvi.feature_savename.adapter.showShimmerEffect
import com.luche.trymvi.feature_savename.data.entity.Guest
import com.luche.trymvi.feature_savename.di.DatabaseModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class GuestListFragment : Fragment() {
    private lateinit var binding: FragmentGuestListBinding
    private val viewModel: GuestListViewModel by viewModel()
    private val adapter: GuestListAdapater by lazy {
        GuestListAdapater(
            this::processGuestClick
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentGuestListBinding.inflate(
            inflater,
            container,
            false
        ).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabBar()
        setupViews()
        initObservers()
    }

    private fun setupTabBar() {
        binding.toolbar.subtitle = getString(R.string.guest_ttl)
        binding.toolbar.menu.findItem(R.id.app_bar_search)?.let { menuItem->
            (menuItem.actionView as? SearchView)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(newText: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { queryText ->
                        viewModel.dispatchIntentToVM(
                            GuestListIntent.SearchGuest(queryText)
                        )
                    }
                    return true
                }
            })
        }
    }

    private fun setupViews() {
        binding.rvGuests.adapter = adapter
        viewModel.dispatchIntentToVM(
            GuestListIntent.InitScreen
        )

        binding.fabAddGuest.setOnClickListener {
            viewModel.dispatchIntentToVM(
                GuestListIntent.AddGuest
            )
        }
    }

    private fun initObservers() {
        viewModel.viewState.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                GuestListViewState.State.INITAL_LOADING -> {
                    updateShimmerState()
                    updateRecyclerViewState(false)
                }
                GuestListViewState.State.SEARCH_LOADING -> {}
                GuestListViewState.State.CLEAR_LOADING -> {
                    updateShimmerState()
                    updateRecyclerViewState(false)
                }
                GuestListViewState.State.SUCCESS ->
                {
                    updateShimmerState(false)
                    updateRecyclerViewState()
                }
                GuestListViewState.State.ERROR -> {
                    updateShimmerState(false)
                }
            }
        }

        viewModel.viewState.guestList.observe(viewLifecycleOwner) { guestList ->
            adapter.submitList(guestList)
        }

        viewModel.viewAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                is GuestListAction.ShowError -> {}
                null -> {}
                is GuestListAction.NavegateToEditScreen -> navegateToEdit(action.guestId)
                GuestListAction.NavegateToAddGuestScreen -> navegatoToAddGuest()
            }
        }
    }

    private fun navegatoToAddGuest() {
        findNavController().navigate(
            R.id.action_guestListFragment_to_saveNameFragment
        )
    }

    private fun updateRecyclerViewState(isVisible: Boolean = true) {
        binding.rvGuests.isVisible = isVisible
    }

    private fun updateShimmerState(show: Boolean = true) {
        binding.shimmerInclude.root.updateShimmerState(show)
    }

    private fun navegateToEdit(guestId: String) {
        Toast.makeText(
            context,
            guestId,
            Toast.LENGTH_SHORT
        ).show()
//        findNavController().navigate(
//            R.id.action_guestListFragment_to_saveNameFragment
//        )
    }

    fun processGuestClick(guestId: Int) {
        viewModel.dispatchIntentToVM(
            GuestListIntent.EditGuest(
                guestId = guestId.toString()
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.guest_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> {
                (item as SearchView).let { it ->
                    it.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(newText: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            newText?.let { queryText ->
                                viewModel.dispatchIntentToVM(
                                    GuestListIntent.SearchGuest(queryText)
                                )
                            }
                            return true
                        }
                    })
                }
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}