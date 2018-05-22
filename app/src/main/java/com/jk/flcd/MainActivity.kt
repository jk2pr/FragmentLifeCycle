package com.jk.flcd

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jk.flcd.fragments.BlankFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val log: StringBuffer = StringBuffer()
    private val TAG0: String = "Activity : "
    private val TAG1: String = "Fragment : "
    val rnd = Random()
    private val map: MutableMap<Int, BlankFragment> = mutableMapOf()
    var count = 0
    override fun onClick(v: View) {
        val bundle = Bundle()
        when (v.id) {
            R.id.add_frag -> {
                val fragment = BlankFragment()
                map[count++] = fragment
                val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                bundle.putInt("ID", color)
                bundle.putInt("count", count)
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction().add(R.id.content, fragment, fragment.toString()).commit()

            }
            R.id.remove_frag -> {
                if (supportFragmentManager.fragments.isNotEmpty()) {
                    val ide = --count
                    val fragmentToRemove = map[ide]
                    supportFragmentManager.beginTransaction().remove(fragmentToRemove).commit()
                    map.remove(ide)
                }
            }
            R.id.replace_frag -> {
                map.clear()
                val fragment = BlankFragment()
                map[count++] = fragment
                bundle.putInt("ID", Color.GRAY)
                bundle.putInt("count", count)
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()


            }
            R.id.hide_frag -> {
                if (supportFragmentManager.fragments.isNotEmpty()) {
                    val ide = count - 1
                    val fragmentToHide = map[ide]
                    if (fragmentToHide!!.isHidden)
                        supportFragmentManager.beginTransaction().show(fragmentToHide).commit()
                    else
                        supportFragmentManager.beginTransaction().hide(fragmentToHide).commit()

                }
            }
            R.id.clear -> {
                txt_log.text = ""
                log.delete(0, log.length)
            }


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
