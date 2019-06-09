package com.elegion.android.template.extension.android.support.v4.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.aytlo.tony.aviasales.extension.android.support.v4.app.Transaction

fun FragmentManager.addFragment(fragment: Fragment): Transaction {
    return Transaction(this, fragment, Transaction.Type.ADD)
}

fun FragmentManager.replaceFragment(fragment: Fragment): Transaction {
    return Transaction(this, fragment, Transaction.Type.REPLACE)
}
