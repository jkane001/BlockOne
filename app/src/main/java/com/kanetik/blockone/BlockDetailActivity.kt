package com.kanetik.blockone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kanetik.blockone.ui.blockdetail.BlockDetailFragment

private const val INTENT_BLOCK_ID = "id"

fun Context.blockDetailIntent(id: String): Intent {
    return Intent(this, BlockDetailActivity::class.java).apply {
        putExtra(INTENT_BLOCK_ID, id)
    }
}

class BlockDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.block_detail_activity)

        if (savedInstanceState == null) {
            val id = intent?.getStringExtra(INTENT_BLOCK_ID)

            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BlockDetailFragment.newInstance(id ?: ""))
                    .commitNow()
        }
    }

}
