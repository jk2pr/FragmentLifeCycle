package com.jk.flcd.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.fragment.app.FragmentActivity
import com.jk.flcd.ui.theme.FragmentLifeCycleTheme
import com.jk.flcd.utils.Constant

class MainActivity : FragmentActivity() {

    internal var logTextState by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (savedInstanceState != null) {
            Constant.log.delete(0, Constant.log.length)
            Constant.log.append(savedInstanceState.getString("logContent", ""))
            logTextState = Constant.log.toString()
        }
        displayActivityLog("OnCreate")

        setContent {
            FragmentLifeCycleTheme {
                Scaffold { innerPadding ->
                    MainScreenLayout(
                        onFragmentAction = this::handleFragmentAction,
                        paddingValues = innerPadding,
                        logText = logTextState
                    )
                }
            }
        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    displayActivityLog("onBackPressed - Popping fragment back stack...")
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed() // Triggers the default behavior (pops fragment)
                    isEnabled = true // Re-enable for future back presses
                } else {
                    displayActivityLog("onBackPressed - Back stack empty, exiting...")
                    finish() // Standard way to finish the Activity and exit if it's the last one
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    // displayFragmentLog is intentionally kept here as it's public and was not part of the private methods to move.
    fun displayFragmentLog(text: String) {
        Constant.log.append("\n${Constant.TAG1}$text")
        logTextState = Constant.log.toString().trim()
        Log.d("FragmentLog", text)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("logContent", Constant.log.toString())
        displayActivityLog("OnSaveInstanceState")
    }

    override fun onStart() {
        super.onStart()
        displayActivityLog("OnStart")
    }

    override fun onRestart() {
        super.onRestart()
        displayActivityLog("OnRestart")
    }

    override fun onResume() {
        super.onResume()
        displayActivityLog("OnResume")
    }

    override fun onPause() {
        super.onPause()
        displayActivityLog("OnPause")
    }

    override fun onStop() {
        super.onStop()
        displayActivityLog("OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        displayActivityLog("OnDestroy")
    }
}
