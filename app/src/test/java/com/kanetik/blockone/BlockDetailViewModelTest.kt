package com.kanetik.blockone

import androidx.lifecycle.MutableLiveData
import com.kanetik.blockone.ui.blockdetail.BlockDetailViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BlockDetailViewModelTest {
    lateinit var viewModel: BlockDetailViewModel

    @Before
    fun setUp() {
        viewModel = mock()
    }

    @Test
    fun getInfoResponse_ReturnsMutableLiveData() {
        whenever(viewModel.getInfoResponse()).thenReturn(MutableLiveData())
        Assert.assertThat(viewModel.getInfoResponse(), CoreMatchers.instanceOf(MutableLiveData::class.java))
    }
}