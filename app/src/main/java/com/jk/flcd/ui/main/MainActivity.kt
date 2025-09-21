package com.jk.flcd.ui.main

import android.graphics.Color
import android.os.Bundle
import android.os.Process
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.jk.flcd.R
import com.jk.flcd.databinding.ActivityMainBinding
import com.jk.flcd.ui.fragments.BlankFragment
import com.jk.flcd.utils.Constant.TAG0
import com.jk.flcd.utils.Constant.TAG1
import com.jk.flcd.utils.Constant.count
import com.jk.flcd.utils.Constant.log
import com.jk.flcd.utils.Constant.map
import com.jk.flcd.utils.Constant.rnd
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onClick(v: View) {
        val bundle = Bundle()
        var commitId = -1
        when (v.id) {
            R.id.add_frag -> {
                val fragment = BlankFragment()
                val tag = fragment.toString()
                map[count++] = tag
                val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                bundle.putInt("ID", color)
                bundle.putInt("count", count)
                fragment.arguments = bundle
                commitId =
                    supportFragmentManager.beginTransaction().add(R.id.content, fragment, tag)
                        .commit()
            }
            R.id.remove_frag -> {
                if (supportFragmentManager.fragments.isNotEmpty()) {
                    val ide = --count
                    val tagFragmentToRemove = map[ide]
                    val fragmentToRemove =
                        supportFragmentManager.findFragmentByTag(tagFragmentToRemove)
                    fragmentToRemove?.let {
                        commitId =
                            supportFragmentManager.beginTransaction().remove(fragmentToRemove)
                                .commit()
                        map.remove(ide)
                    }
                    if (map.isEmpty())
                        count = 0
                }
            }
            R.id.replace_frag -> {
                map.clear()
                val fragment = BlankFragment()
                val tag = fragment.toString()
                map[count++] = tag
                val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                bundle.putInt("ID", color)
                bundle.putInt("count", count)
                fragment.arguments = bundle
                commitId =
                    supportFragmentManager.beginTransaction().replace(R.id.content, fragment, tag)
                        .commit()
            }
            R.id.hide_frag -> {
                if (supportFragmentManager.fragments.isNotEmpty()) {
                    val ide = count - 1
                    val tagFragmentToHide = map[ide]
                    val fragmentToHide = supportFragmentManager.findFragmentByTag(tagFragmentToHide)
                    fragmentToHide?.let {
                        commitId = if (fragmentToHide.isHidden)
                            supportFragmentManager.beginTransaction().show(fragmentToHide)
                                .commit()
                        else
                            supportFragmentManager.beginTransaction().hide(fragmentToHide)
                                .commit()
                    }
                }
            }
            R.id.clear -> {
                binding.txtLog.text = ""
                log.delete(0, log.length)
            }
        }
        Log.d(javaClass.simpleName, (v as Button).text.toString())
        Log.d(javaClass.simpleName + "commitId= ", commitId.toString())
        Log.d(
            javaClass.simpleName + " BackStackCount is ",
            supportFragmentManager.backStackEntryCount.toString()
        )
        supportFragmentManager.fragments.forEach {
            Log.d(
                javaClass.simpleName + " Fragments in Stack is ",
                (it as BlankFragment).count.toString()
            )
        }
    }

    private fun displayActivityLog(text: String) {
        log.append("\n").append(TAG0).append(text)
        binding.txtLog.text = log.trim()
        binding.scrollView.post {
            binding.scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }

    fun displayFragmentLog(text: String) {
        log.append("\n").append(TAG1).append(text)
        binding.txtLog.text = log.trim()
        binding.scrollView.post {
            binding.scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtLog.movementMethod = ScrollingMovementMethod()
        displayActivityLog("OnCreate")
        binding.addFrag.setOnClickListener(this)
        binding.removeFrag.setOnClickListener(this)
        binding.replaceFrag.setOnClickListener(this)
        binding.hideFrag.setOnClickListener(this)
        binding.clear.setOnClickListener(this)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.txtLog.postDelayed({
                    Process.killProcess(Process.myPid())
                    exitProcess(1)
                }, 200)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        displayActivityLog("OnStart")
    }

    override fun onRestart() {
        super.onRestart()
        displayActivityLog("OnRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        displayActivityLog("OnSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        displayActivityLog("OnRestoreInstanceState")
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
