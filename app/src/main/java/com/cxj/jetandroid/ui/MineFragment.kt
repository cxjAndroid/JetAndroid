/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.cxj.lib_base.base.BaseMvvmFragment
import com.cxj.jetandroid.databinding.FragmentMineBinding
import com.cxj.jetandroid.viewmodel.HomeFragmentViewModel
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.sdk.ValueCallback
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

class MineFragment : BaseMvvmFragment<FragmentMineBinding, HomeFragmentViewModel>() {

    private var mTitle = ""

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            webView.webChromeClient = mWebChromeClient
            webView.webViewClient = mWebViewClient //如果不设置则跳系统浏览器
        }
    }


    override fun initData() {
        viewModel.bannerLiveData.observe(this){
            it?.apply {
                val banner = get(0)
                binding?.webView?.loadUrl(banner?.url)
                mTitle = banner?.title?:""
            }
        }
    }

    /**
     * 辅助 WebView 处理 Javascript 的对话框,网站图标,网站标题等等
     */
    private val mWebChromeClient = object : WebChromeClient() {
        /**
         * 网页Title更改
         */
        override fun onReceivedTitle(webView: WebView?, title: String?) {
            super.onReceivedTitle(webView, title)
            if (webView != null && !webView.canGoBack() && mTitle.isNotEmpty()) {
                binding?.titleBar?.setMiddleText(mTitle)
            } else {
                var titleResult = ""
                if (!title.isNullOrEmpty() && !title.startsWith("http", true)) {
                    titleResult = title
                }
                binding?.titleBar?.setMiddleText(titleResult)
            }
        }

        /**
         * 文件选择回调
         */
        override fun onShowFileChooser(
            webView: WebView?,
            back: ValueCallback<Array<Uri>>?,
            chooser: FileChooserParams?
        ): Boolean {
            return super.onShowFileChooser(webView, back, chooser)
        }

        /**
         * 获得网页的加载进度并显示
         */
        override fun onProgressChanged(webView: WebView?, process: Int) {
            super.onProgressChanged(webView, process)
            if (process == 100) {
                //dismissLoading()
            }
        }
    }

    /**
     * 处理各种通知 & 请求事件
     */
    private val mWebViewClient = object : WebViewClient() {
        /**
         * 网页加载完毕
         */
        override fun onPageFinished(webView: WebView?, url: String?) {
            super.onPageFinished(webView, url)
        }

        /**
         * 跳转拦截处理
         * 打开网页时，不调用系统浏览器进行打开，而是在本WebView中直接显示
         */
        override fun shouldOverrideUrlLoading(webview: WebView?, url: String?): Boolean {
            //处理url
            //mBinding.webView.loadUrl(url)
            return super.shouldOverrideUrlLoading(webview, url)
        }

        /**
         * 设置错误页面
         */
        override fun onReceivedError(webview: WebView?, p1: Int, p2: String?, p3: String?) {
            super.onReceivedError(webview, p1, p2, p3)
        }

        override fun onReceivedSslError(
            webView: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            super.onReceivedSslError(webView, handler, error)
            //忽略ssl错误
            handler?.proceed()
        }
    }
}