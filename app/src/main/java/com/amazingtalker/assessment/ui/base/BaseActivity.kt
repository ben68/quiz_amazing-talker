package com.amazingtalker.assessment.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V: ViewBinding, M: ViewModel>: AppCompatActivity() {
    abstract val mBinding: V
    abstract val mModel: M

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(mBinding.root)
    }
}
