package com.jk.flcd.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jk.flcd.R
import com.jk.flcd.databinding.FragmentBlankBinding
import com.jk.flcd.ui.main.MainActivity
import com.jk.flcd.utils.ViewUtils

class BlankFragment : Fragment() {

    private lateinit var holdingActivity: MainActivity
    val count: Int by lazy { requireArguments().getInt("count") }

    private var _binding: FragmentBlankBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        holdingActivity = activity as MainActivity
        holdingActivity.displayFragmentLog("$count OnAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        holdingActivity.displayFragmentLog("$count OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        holdingActivity.displayFragmentLog("$count OnCreateView")
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        holdingActivity.displayFragmentLog("$count OnViewCreated")
        val id = arguments?.getInt("ID")
          id?.let { binding.fragment.setBackgroundColor(it) }

        binding.blankText.apply {
            setTextColor(ViewUtils.getLabelTextColor(holdingActivity, id!!))
            text = String.format(resources.getString(R.string.count), this@BlankFragment.count) // use this@BlankFragment.count for clarity
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        holdingActivity.displayFragmentLog("$count OnActivityCreated")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        holdingActivity.displayFragmentLog("$count OnSaveInstanceState")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        holdingActivity.displayFragmentLog("$count OnViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        holdingActivity.displayFragmentLog("$count OnStart")
    }

    override fun onResume() {
        super.onResume()
        holdingActivity.displayFragmentLog("$count OnResume")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        holdingActivity.displayFragmentLog("$count OnHiddenChanged: $hidden")
    }

    override fun onPause() {
        super.onPause()
        holdingActivity.displayFragmentLog("$count OnPause")
    }

    override fun onStop() {
        super.onStop()
        holdingActivity.displayFragmentLog("$count OnStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        holdingActivity.displayFragmentLog("$count OnDestroyView")
        _binding = null // Clear the binding reference
    }

    override fun onDestroy() {
        super.onDestroy()
        holdingActivity.displayFragmentLog("$count OnDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        holdingActivity.displayFragmentLog("$count OnDetach")
    }
}
