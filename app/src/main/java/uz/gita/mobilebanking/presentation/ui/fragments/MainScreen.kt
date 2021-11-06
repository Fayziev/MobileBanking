package uz.gita.mobilebanking.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebankapplication.presentation.ui.screen.MainScreenDirections
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.retrofit.request.ResetRequest
import uz.gita.mobilebanking.data.retrofit.response.CardData
import uz.gita.mobilebanking.databinding.ScreenMainBinding
import uz.gita.mobilebanking.presentation.ui.adapter.CardAdapter
import uz.gita.mobilebanking.presentation.viewModel.MainViewModel
import uz.gita.mobilebanking.presentation.viewModel.impl.MainViewModelImpl
import uz.gita.mobilebanking.utils.scope

@AndroidEntryPoint
class MainScreen:Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val adapter by lazy { CardAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        viewModel.cardGet()
        tokenName.text = viewModel.getToken()

        cardRecyclerView.adapter = adapter
        cardRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        resetPassword.setOnClickListener {
            val dialog = ResetDialog()
            dialog.setListener {
                viewModel.resetUser(
                    ResetRequest(it)
                )
            }
            dialog.show(childFragmentManager, "resetDialog")
        }

        logOutButton.setOnClickListener {
            viewModel.logoutUser()
        }

        addCard.setOnClickListener {
            viewModel.cardAdd()
        }

        viewModel.disableAddCardLiveData.observe(viewLifecycleOwner, disableAddCardObserver)
        viewModel.enableAddCardLiveData.observe(viewLifecycleOwner, enableAddCardObserver)
        viewModel.disableLogoutButtonLiveData.observe(viewLifecycleOwner, disableLogoutObserver)
        viewModel.enableLogoutButtonLiveData.observe(viewLifecycleOwner, enableLogoutObserver)
        viewModel.disableResetButtonLiveData.observe(viewLifecycleOwner, disableResetObserver)
        viewModel.deleteCardRecyclerView.observe(viewLifecycleOwner, deleteCardRecyclerViewObserver)
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner, errorMessageObserver)
        viewModel.hideProgressLiveData.observe(viewLifecycleOwner, hideProgressObserver)
        viewModel.showProgressLiveData.observe(viewLifecycleOwner, showProgressObserver)
        viewModel.openAddCardScreenLiveData.observe(viewLifecycleOwner, openAddCardScreenObserver)
        viewModel.openEditCardScreenLiveData.observe(viewLifecycleOwner, openEditCardScreenObserver)
        viewModel.openLoginScreenLiveData.observe(viewLifecycleOwner, openLoginScreenObserver)
        viewModel.recyclerViewLiveData.observe(viewLifecycleOwner, recyclerViewObserver)
    }

    private val disableResetObserver = Observer<Unit> {
        binding.resetPassword.isEnabled = false
    }
    private val disableAddCardObserver = Observer<Unit> {
        binding.addCard.isEnabled = false
    }
    private val disableLogoutObserver = Observer<Unit> {
        binding.logOutButton.isEnabled = false
    }

    private val enableAddCardObserver = Observer<Unit> {
        binding.addCard.isEnabled = true
    }
    private val enableLogoutObserver = Observer<Unit> {
        binding.logOutButton.isEnabled = true
    }

    private val openLoginScreenObserver = Observer<Unit> {
        findNavController().navigate(R.id.authScreen)
    }
    private val openAddCardScreenObserver = Observer<Unit> {
        findNavController().navigate(MainScreenDirections.actionMainScreenToAddCardScreen())
    }
    private val openEditCardScreenObserver = Observer<CardData> {
        findNavController().navigate(MainScreenDirections.actionMainScreenToEditCardScreen(it))
    }
    private val showProgressObserver = Observer<Unit> {
        binding.progress.show()
    }
    private val hideProgressObserver = Observer<Unit> {
        binding.progress.hide()
    }
    private val errorMessageObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
    private val deleteCardRecyclerViewObserver = Observer<Int> {
        val list = adapter.currentList
        list.removeAt(it)
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
    }
    private val recyclerViewObserver = Observer<List<CardData>> {
        adapter.submitList(it)
        adapter.notifyDataSetChanged()
    }

}