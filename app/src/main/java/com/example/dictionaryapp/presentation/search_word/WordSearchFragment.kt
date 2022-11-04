package com.example.dictionaryapp.presentation.search_word

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.core.SetupUI
import com.example.dictionaryapp.databinding.FragmentWordSearchBinding
import com.example.dictionaryapp.presentation.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WordSearchFragment :
    BaseFragment<FragmentWordSearchBinding>(FragmentWordSearchBinding::inflate), SetupUI {

    private val viewModel = SearchWordViewModel(SearchWordState())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()

        viewModel.getWordsResult("LOL")

    }

    override fun setupView() {
        mBinding.shimmerLayout.startShimmer()
        mBinding.button.setOnClickListener{

        }
    }

    override fun setupObservers() {

        viewModel.uiState.onEach {
            Log.d("LOL", "action: ${it.word}")
        }.launchIn(lifecycleScope)


        lifecycleScope.launchWhenStarted {
            viewModel.actions.collectLatest {

            }

        }


    }

    override fun setupInitialState() {
        TODO("Not yet implemented")
    }

    override fun initToolBar() {
        TODO("Not yet implemented")
    }
}