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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        holdingActivity.displayFragmentLog("onAttach")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        holdingActivity.displayFragmentLog("onCreate")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        holdingActivity.displayFragmentLog("onCreateView")
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        holdingActivity.displayFragmentLog("onViewCreated")
        val id = arguments?.getInt("ID")
        fragment?.setBackgroundColor(id!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        holdingActivity.displayFragmentLog("onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        holdingActivity.displayFragmentLog("onStart")
    }

    override fun onResume() {
        super.onResume()
        holdingActivity.displayFragmentLog("onResume")
    }

    override fun onPause() {
        super.onPause()
        holdingActivity.displayFragmentLog("onPause")
    }

    override fun onStop() {
        super.onStop()
        holdingActivity.displayFragmentLog("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        holdingActivity.displayFragmentLog("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        holdingActivity.displayFragmentLog("onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        holdingActivity.displayFragmentLog("onDetach")
    }


}
