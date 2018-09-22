package com.kanetik.blockone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kanetik.blockone.ui.blocklist.BlockListFragment

fun Context.BlockListIntent(count: kotlin.Int): Intent {
    return Intent(this, BlockListActivity::class.java).apply {
        putExtra(INTENT_BLOCK_COUNT, count)
    }
}

private const val INTENT_BLOCK_COUNT = "count"

class BlockListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.block_list_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BlockListFragment.newInstance())
                    .commitNow()
        }
    }

}
