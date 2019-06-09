package com.aytlo.tony.aviasales.presentation.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.aytlo.tony.aviasales.R
import com.aytlo.tony.aviasales.extension.android.support.v4.app.replaceFragment

abstract class SingleFragmentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState ?: supportFragmentManager.replaceFragment(createFragment()).commit()
    }

    @LayoutRes
    override fun getLayout() = R.layout.ac_single_frame

    abstract fun createFragment(): Fragment
}
