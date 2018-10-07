package com.jk.flcd

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.jk.flcd.fragments.BlankFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val log: StringBuffer = StringBuffer()
    private val rnd = Random()
    private val map: MutableMap<Int, BlankFragment> = mutableMapOf()
    private var count = 0

    companion object {
        private const val TAG0: String = "Activity : "
        private const val TAG1: String = "Fragment : "
    }

    override fun onClick(v: View) {
        val bundle = Bundle()
        var commitId = -1
        when (v.id) {
            R.id.add_frag -> {
                val fragment = BlankFragment()
                map[count++] = fragment
                val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                bundle.putInt("ID", color)
                bundle.putInt("count", count)
                fragment.arguments = bundle
                commitId = supportFragmentManager.beginTransaction().add(R.id.content, fragment, fragment.toString()).addToBackStack(fragment.toString()).commit()

            }
            R.id.remove_frag -> {
                if (supportFragmentManager.fragments.isNotEmpty()) {
                    val ide = --count
                    val fragmentToRemove = map[ide]
                    commitId = supportFragmentManager.beginTransaction().remove(fragmentToRemove)
                            //.addToBackStack(fragmentToRemove.toString())
                            .commit()
                    map.remove(ide)
                    if (map.isEmpty())
                        count = 0
                }
            }
            R.id.replace_frag -> {
                map.clear()
                val fragment = BlankFragment()
                map[count++] = fragment
                val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                bundle.putInt("ID", color)
                bundle.putInt("count", count)
                fragment.arguments = bundle
                commitId = supportFragmentManager.beginTransaction().replace(R.id.content, fragment)
                        //.addToBackStack(fragment.toString())
                .commit()


            }
            R.id.hide_frag -> {
                if (supportFragmentManager.fragments.isNotEmpty()) {
                    val ide = count - 1
                    val fragmentToHide = map[ide]
                    if (fragmentToHide!!.isHidden)
                        commitId = supportFragmentManager.beginTransaction().show(fragmentToHide)
                                //addToBackStack(fragmentToHide.toString())
                                .commit()
                    else
                        commitId = supportFragmentManager.beginTransaction().hide(fragmentToHide)
                                //.addToBackStack(fragmentToHide.toString())
                                .commit()

                }
            }
            R.id.clear -> {
                txt_log.text = ""
                log.delete(0, log.length)
            }

        }
        Log.d(javaClass.simpleName, (v as Button).text.toString())
        Log.d(javaClass.simpleName + "commitId= ", commitId.toString())
        Log.d(javaClass.simpleName + " BackStackCount is ", supportFragmentManager.backStackEntryCount.toString())
        supportFragmentManager.fragments.forEach {
            Log.d(javaClass.simpleName + " Fragments in Stack is ", (it as BlankFragment).count.toString())
        }

    }

    private fun displayActivityLog(text: String) {
        log.append("\n").append(TAG0).append(text)
        txt_log.text = log
        scrollView.scrollTo(0, scrollView.bottom)

    }

    fun displayFragmentLog(text: String) {
        log.append("\n").append(TAG1).append(text)
        txt_log.text = log
        scrollView.scrollTo(0, scrollView.bottom)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayActivityLog("OnCreate")
        add_frag.setOnClickListener(this)
        remove_frag.setOnClickListener(this)
        replace_frag.setOnClickListener(this)
        hide_frag.setOnClickListener(this)
        clear.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
        displayActivityLog("onStart")
    }

    override fun onResume() {
        super.onResume()
        displayActivityLog("onResume")
    }

    override fun onPause() {
        super.onPause()
        displayActivityLog("onPause")
    }

    override fun onStop() {
        super.onStop()
        displayActivityLog("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        displayActivityLog("onDestroy")
    }


}
