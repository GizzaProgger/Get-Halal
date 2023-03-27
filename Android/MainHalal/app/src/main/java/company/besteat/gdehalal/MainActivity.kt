package company.besteat.gdehalal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
//import com.android.gdehalal.R

//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}


class MainActivity : Activity() {

    var mainWebV: WebView? = null

    private val PAYMENT_REQUEST_CODE : Int = 11


    companion object{

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {1
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideSystemUI()

        mainWebV =  findViewById(R.id.web)   //WebView(this) // was getApplicationContext

        val CookieManagerKL = CookieManager.getInstance()
        println(" created javaView \n")
        CookieManagerKL.setAcceptCookie(true)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        mainWebV?.settings?.javaScriptEnabled = true
        mainWebV?.settings?.javaScriptCanOpenWindowsAutomatically = true
        mainWebV?.settings?.loadWithOverviewMode = true
        mainWebV?.settings?.domStorageEnabled = true
        mainWebV?.settings?.setSupportZoom(false)
        mainWebV?.settings?.allowFileAccess = true
        mainWebV?.settings?.allowContentAccess = true

        mainWebV?.settings?.useWideViewPort = true
        mainWebV?.settings?.loadWithOverviewMode = true
        mainWebV?.settings?.allowFileAccess = true
        mainWebV?.settings?.javaScriptCanOpenWindowsAutomatically = true
        mainWebV?.settings?.builtInZoomControls = true
        mainWebV?.settings?.setPluginState(WebSettings.PluginState.ON)
        mainWebV?.settings?.setSupportZoom(true)
        mainWebV?.settings?.allowContentAccess = true
        mainWebV?.settings?.displayZoomControls = false
        mainWebV?.settings?.domStorageEnabled = true
        mainWebV?.settings?.defaultTextEncodingName = "utf-8"
        mainWebV?.isHorizontalScrollBarEnabled = true
        mainWebV?.isVerticalScrollBarEnabled = true
        mainWebV?.settings?.useWideViewPort = true
        mainWebV?.settings?.loadWithOverviewMode = true
        mainWebV?.settings?.databaseEnabled = true

        mainWebV?.webChromeClient = object : WebChromeClient() {

        }

        mainWebV?.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
              //  view!!.loadUrl("javascript:alert(showVersion('calling by Android'))")
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val ss: String = request!!.url.toString()
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
            }


            override fun onReceivedError(
                view: WebView,
                req: WebResourceRequest,
                rerr: WebResourceError
            ) {
                onReceivedError(
                    view,
                    rerr.errorCode,
                    rerr.description.toString(),
                    req.url.toString()
                )
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
            }

            override fun onLoadResource(view: WebView?, url: String?) {
                //             //
                // super.onLoadResource(view, "http://as-doors.ru")
                super.onLoadResource(view, "https://gdehalal.ru/")
            }

        }


        mainWebV!!.loadUrl("https://gdehalal.ru/")
        val webSet = mainWebV!!.settings
        webSet.javaScriptEnabled = true
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }



    override fun onBackPressed() {


        // getToastMessage();
        // (function(){return 'test'})()
//        mainWebV!!.evaluateJavascript("(function(){return getTest() })()", ValueCallback<String?> { value ->
//
//              println(" call this js code - $value " )
//
//
////            if (callback != null) {
////                callback.onReceiveValue(value)
////
////
//        })


//        mainWebV!!.evaluateJavascript("", ValueCallback<String>){
//
//        }

//        if (mainWebV?.canGoBack()!!) {
//            mainWebV?.goBack()
//        } else {
//            if (mainWebV?.canGoBack()!!) {
//                mainWebV?.goBack()
//            } else {
//                super.onBackPressed()
//            }
//        }

    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        println(" ok payment system ocd e 111")

        if( requestCode == PAYMENT_REQUEST_CODE){
            println(" ok payment system ocd e ")
        }
    }

}
