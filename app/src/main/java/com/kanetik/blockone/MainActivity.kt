package com.kanetik.blockone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewBlocks.setOnClickListener { goToSummary() }
    }

    private fun goToSummary() {
        startActivity(BlockListIntent(1))
    }
}
