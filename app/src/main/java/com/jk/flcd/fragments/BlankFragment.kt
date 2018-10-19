package com.jk.flcd.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jk.flcd.MainActivity

import com.jk.flcd.R
import com.jk.flcd.utils.ViewUtils
import kotlinx.android.synthetic.main.fragment_blank.*


class BlankFragment : Fragment() {

    private lateinit var holdingActivity: MainActivity
    val count: Int by lazy { arguments!!.getInt("count") }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        holdingActivity = activity as MainActivity
        holdingActivity.displayFragmentLog(count.toString() + " OnAttach")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        holdingActivity.displayFragmentLog(count.toString() + " OnCreate")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        holdingActivity.displayFragmentLog(count.toString() + " OnCreateView")
        return inflater.inflate(
                R.layout.fragment_blank,
                container,
                false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        holdingActivity.displayFragmentLog(count.toString() + " OnViewCreated")
        val id = arguments?.getInt("ID")
        fragment?.setBackgroundColor(id!!)


        blank_text.apply {

            setTextColor(ViewUtils.getLabelTextColor(holdingActivity, id!!))
            text = String.format(resources.getString(R.string.count), count)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        holdingActivity.displayFragmentLog(count.toString() + " OnActivityCreated")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        holdingActivity.displayFragmentLog(count.toString() + " OnSaveInstanceState")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        holdingActivity.displayFragmentLog(count.toString() + " OnViewStateRestored")
    }
    override fun onStart() {
        super.onStart()
        holdingActivity.displayFragmentLog(count.toString() + " OnStart")
    }

    override fun onResume() {
        super.onResume()
        holdingActivity.displayFragmentLog(count.toString() + " OnResume")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        holdingActivity.displayFragmentLog(count.toString() + " OnHiddenChanged: "+ hidden)
    }
    override fun onPause() {
        super.onPause()
        holdingActivity.displayFragmentLog(count.toString() + " OnPause")
    }

    override fun onStop() {
        super.onStop()
        holdingActivity.displayFragmentLog(count.toString() + " OnStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        holdingActivity.displayFragmentLog(count.toString() + " OnDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        holdingActivity.displayFragmentLog(count.toString() + " OnDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        holdingActivity.displayFragmentLog(count.toString() + " OnDetach")
    }


}
