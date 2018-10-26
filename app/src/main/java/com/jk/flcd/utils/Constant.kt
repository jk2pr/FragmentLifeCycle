package com.jk.flcd.utils

import com.jk.flcd.fragments.BlankFragment
import java.util.*

object Constant {
     val rnd = Random()
     var count = 0
     val map: MutableMap<Int, BlankFragment> = mutableMapOf()
    val log: StringBuffer = StringBuffer()
     const val TAG0: String = "Activity : "
     const val TAG1: String = "Fragment : "
}