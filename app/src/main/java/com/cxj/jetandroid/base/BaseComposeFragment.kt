/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.compose.ui.theme.ComposeTheme
import java.lang.reflect.ParameterizedType

abstract class BaseComposeFragment<VM: ViewModel> : BaseFragment() {
    lateinit var viewModel: VM
    open var booleanBindActivityLifecycle = true
    override fun getLayoutResId(): Int {
        return 0
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    open fun initViewModel() {
        val argument = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        val viewModelStore: ViewModelStoreOwner = if(booleanBindActivityLifecycle) requireActivity() else this
        viewModel = ViewModelProvider(viewModelStore)[argument[0] as Class<VM>]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        return ComposeView(requireActivity()).apply {
            setContent {
                MyComposeTheme {
                    SetComposeView()
                }
            }

        }
    }

    @Composable
    abstract fun SetComposeView()

    @Composable
    fun MyComposeTheme(content: @Composable () -> Unit){
        MaterialTheme {
            // 设置全局参数，去除默认点击效果
            CompositionLocalProvider(
                LocalIndication provides NoIndication
            ) {
                ProvideTextStyle(value = MaterialTheme.typography.bodySmall, content = content)

            }
        }
    }
}


object NoIndication : Indication {
    private object NoIndicationInstance : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return NoIndicationInstance
    }
}