package com.example.searchaddresskotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val webView: WebView by lazy { findViewById(R.id.webWiew) }
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(BridgeInterface(), "Android")
        webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                webView.loadUrl("javascript:sample2_execDaumPostcode();")
            }
        }

        webView.loadUrl("https://searchfinal-b0c22.web.app")


    }

    private inner class BridgeInterface {
        @JavascriptInterface
        fun processDATA(data: String){
            val zonecode = data.substring(0,5)
            val address = data.substring(6)
            val intent = Intent()
            intent.putExtra("address", address)
            intent.putExtra("zonecode", zonecode)
            setResult(RESULT_OK, intent)
            finish()
        }

    }


}