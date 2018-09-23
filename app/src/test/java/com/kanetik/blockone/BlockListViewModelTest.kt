package com.kanetik.blockone

import androidx.lifecycle.MediatorLiveData
import com.kanetik.blockone.ui.blocklist.BlockListViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class BlockListViewModelTest {
    lateinit var viewModel: BlockListViewModel

    @Before
    fun setUp() {
        viewModel = mock()
    }

    @Test
    fun getBlocks_ReturnsMediatorLiveData() {
        whenever(viewModel.getBlocks()).thenReturn(MediatorLiveData())
        assertThat(viewModel.getBlocks(), instanceOf(MediatorLiveData::class.java))
    }
}