package com.kanetik.blockone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kanetik.blockone.ui.blocklist.BlockListFragment

private const val DEFAULT_MAX_BLOCK_COUNT = 20
private const val INTENT_BLOCK_COUNT = "maxBlocks"

fun Context.blockListIntent(maxBlocks: kotlin.Int): Intent {
    return Intent(this, BlockListActivity::class.java).apply {
        putExtra(INTENT_BLOCK_COUNT, maxBlocks)
    }
}

class BlockListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.block_list_activity)

        if (savedInstanceState == null) {
            val maxBlocks = intent?.getIntExtra(INTENT_BLOCK_COUNT, DEFAULT_MAX_BLOCK_COUNT)

            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BlockListFragment.newInstance(maxBlocks ?: DEFAULT_MAX_BLOCK_COUNT))
                    .commitNow()
        }
    }

}
