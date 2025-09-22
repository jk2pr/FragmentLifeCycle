package com.jk.flcd.ui.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.jk.flcd.R
import com.jk.flcd.ui.fragments.BlankFragment
import com.jk.flcd.utils.Constant

internal fun MainActivity.handleFragmentAction(action: String) {
    when (action) {
        "add_frag" -> addFragment()
        "remove_frag" -> removeFragment()
        "replace_frag" -> replaceFragment()
        "hide_frag" -> toggleHideFragment()
        "clear_log" -> Constant.log.delete(0, Constant.log.length)
    }
    logTextState = Constant.log.toString() // Update state after action
    logFragmentManagerState(action)
}

// --- Helper functions for Fragment Operations ---
internal fun MainActivity.addFragment() {
    val fragment = BlankFragment()
    val tag = fragment.toString()
    Constant.map[Constant.count++] = tag
    val color = Color.argb(255, Constant.rnd.nextInt(256), Constant.rnd.nextInt(256), Constant.rnd.nextInt(256))
    val bundle = Bundle().apply {
        putInt("ID", color)
        putInt("count", Constant.count)
    }
    fragment.arguments = bundle
    supportFragmentManager.commit {
        add(R.id.compose_fragment_container, fragment, tag)
        addToBackStack(tag) // Add this transaction to the back stack
    }
}

internal fun MainActivity.removeFragment() {
    if (supportFragmentManager.fragments.isEmpty()) {
        displayActivityLog("Remove Error: No fragments to remove.")
        return
    }
    if (Constant.map.isEmpty() || Constant.count <= 0) {
        displayActivityLog("Remove Error: Map is empty or count is invalid.")
        Constant.count = 0
        Constant.map.clear()
        return
    }

    val ide = --Constant.count
    val tagFragmentToRemove = Constant.map[ide]
    val fragmentToRemove: Fragment? = supportFragmentManager.findFragmentByTag(tagFragmentToRemove)

    fragmentToRemove?.let {
        supportFragmentManager.commit {
            remove(it)
        }
        Constant.map.remove(ide)
        // Note: Removing a fragment doesn't usually get added to backstack in a way that 'back' undoes the remove.
        // If you wanted 'back' to re-add the removed fragment, the logic would be more complex.
        // The current back press logic in MainActivity pops the stack from add/replace operations.
    } ?: run {
        displayActivityLog("Remove Warning: Fragment with tag $tagFragmentToRemove (map key: $ide) not found.")
        if (supportFragmentManager.fragments.isNotEmpty()) {
            val lastFragment = supportFragmentManager.fragments.last()
            supportFragmentManager.commit { remove(lastFragment) }
            displayActivityLog("Removed last available fragment from FragmentManager as a fallback.")
        }
        Constant.map.remove(ide) 
    }
    if (Constant.map.isEmpty()) Constant.count = 0
}

internal fun MainActivity.replaceFragment() {
    Constant.map.clear()
    Constant.count = 0 

    val fragment = BlankFragment()
    val tag = fragment.toString()
    Constant.map[Constant.count++] = tag
    val color = Color.argb(255, Constant.rnd.nextInt(256), Constant.rnd.nextInt(256), Constant.rnd.nextInt(256))
    val bundle = Bundle().apply {
        putInt("ID", color)
        putInt("count", Constant.count)
    }
    fragment.arguments = bundle
    supportFragmentManager.commit {
        replace(R.id.compose_fragment_container, fragment, tag)
        addToBackStack(tag) // Add this transaction to the back stack
    }
}

internal fun MainActivity.toggleHideFragment() {
    if (supportFragmentManager.fragments.isEmpty()) {
        displayActivityLog("Hide/Show Error: No fragments to toggle.")
        return
    }
    if (Constant.map.isEmpty() || Constant.count <= 0) {
        displayActivityLog("Hide/Show Error: Map is empty or count is invalid for finding fragment to toggle.")
        Constant.count = 0
        Constant.map.clear()
        return
    }

    val ide = Constant.count - 1 
    val tagFragmentToToggle = Constant.map[ide]
    val fragmentToToggle: Fragment? = supportFragmentManager.findFragmentByTag(tagFragmentToToggle)

    fragmentToToggle?.let {
        supportFragmentManager.commit {
            if (it.isHidden) show(it) else hide(it)
            // Hide/show operations are also typically not added to the back stack for 'undo' via back press.
            // addToBackStack(null) // If you wanted hide/show to be on backstack, you'd add this.
        }
    } ?: displayActivityLog("Hide/Show Error: Fragment with tag $tagFragmentToToggle (map key: $ide) not found.")
}

internal fun MainActivity.logFragmentManagerState(action: String) {
    Log.d(
        this.javaClass.simpleName, // Corrected to use 'this.javaClass.simpleName'
        "Action: $action, BackStack: ${supportFragmentManager.backStackEntryCount}, FM Fragments: ${supportFragmentManager.fragments.size}, Map Size: ${Constant.map.size}, Count: ${Constant.count}"
    )
    supportFragmentManager.fragments.forEach { frag: Fragment ->
        if (frag is BlankFragment) {
            Log.d(this.javaClass.simpleName, "  In FM: BlankFragment Count ${frag.count}, Tag ${frag.tag}")
        }
    }
}

internal fun MainActivity.displayActivityLog(text: String) {
    Constant.log.append("\n${Constant.TAG0}$text")
    logTextState = Constant.log.toString().trim()
}