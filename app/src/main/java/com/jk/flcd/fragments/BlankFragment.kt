package com.jk.flcd.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jk.flcd.MainActivity

import com.jk.flcd.R
import kotlinx.android.synthetic.main.fragment_blank.*


class BlankFragment : Fragment() {

    private val holdingActivity: MainActivity by lazy { activity as MainActivity }
    private val count: Int by lazy { arguments!!.getInt("count") }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        holdingActivity.displayFragmentLog(count.toString() + " onAttach")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        holdingActivity.displayFragmentLog(count.toString() + " onCreate")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        holdingActivity.displayFragmentLog(count.toString() + " onCreateView")
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        holdingActivity.displayFragmentLog(count.toString() + " onViewCreated")
        val id = arguments?.getInt("ID")
        fragment?.setBackgroundColor(id!!)



        blank_text.text = String.format(resources.getString(R.string.count), count)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        holdingActivity.displayFragmentLog(count.toString() + " onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        holdingActivity.displayFragmentLog(count.toString() + " onStart")
    }

    override fun onResume() {
        super.onResume()
        holdingActivity.displayFragmentLog(count.toString() + " onResume")
    }

    override fun onPause() {
        super.onPause()
        holdingActivity.displayFragmentLog(count.toString() + " onPause")
    }

    override fun onStop() {
        super.onStop()
        holdingActivity.displayFragmentLog(count.toString() + " onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        holdingActivity.displayFragmentLog(count.toString() + " onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        holdingActivity.displayFragmentLog(count.toString() + " onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        holdingActivity.displayFragmentLog(count.toString() + " onDetach")
    }


}
