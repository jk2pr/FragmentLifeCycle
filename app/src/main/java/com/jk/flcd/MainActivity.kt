package com.jk.flcd

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.util.Log
import android.view.View
import android.widget.Button
import com.jk.flcd.fragments.BlankFragment
import com.jk.flcd.utils.Constant.TAG0
import com.jk.flcd.utils.Constant.TAG1
import com.jk.flcd.utils.Constant.log
import com.jk.flcd.utils.Constant.rnd
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private val map: MutableMap<Int, BlankFragment> = mutableMapOf()
    private var count = 0


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
                    supportFragmentManager.beginTransaction().remove(fragmentToRemove)
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
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment)
                        //.addToBackStack(fragment.toString())
                        .commit()


            }
            R.id.hide_frag -> {
                if (supportFragmentManager.fragments.isNotEmpty()) {
                    val ide = count - 1
                    val fragmentToHide = map[ide]
                    if (fragmentToHide!!.isHidden)
                        supportFragmentManager.beginTransaction().show(fragmentToHide)
                                //addToBackStack(fragmentToHide.toString())
                                .commit()
                    else
                        supportFragmentManager.beginTransaction().hide(fragmentToHide)
                                //.addToBackStack(fragmentToHide.toString())
                                .commit()

                }
            }
            R.id.clear -> {
                txt_log.text = ""
                log.delete(0, log.length)
            }

        }
        /* Log.d(javaClass.simpleName, (v as Button).text.toString())
         Log.d(javaClass.simpleName + "commitId= ", commitId.toString())
         Log.d(javaClass.simpleName + " BackStackCount is ", supportFragmentManager.backStackEntryCount.toString())
         supportFragmentManager.fragments.forEach {
             Log.d(javaClass.simpleName + " Fragments in Stack is ", (it as BlankFragment).count.toString())
         }*/

    }

    private fun displayActivityLog(text: String) {
        log.append("\n").append(TAG0).append(text)
        txt_log.text = log
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_DOWN)
        }

    }

    fun displayFragmentLog(text: String) {

        log.append("\n").append(TAG1).append(text)
        txt_log.text = log
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_DOWN)
        }


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
        displayActivityLog("OnStart")
    }

    override fun onRestart() {
        super.onRestart()
        displayActivityLog("OnRestart")
    }

    override fun onSaveInstanceState(outState: Bundle? ) {
        super.onSaveInstanceState(outState)
        displayActivityLog("OnSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
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
