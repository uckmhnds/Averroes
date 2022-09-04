package com.uckmhnds.averroes.utils

object Constants {

    const val TRAVEL:String     = "Travel"
    const val PERSONAL:String   = "Personal"
    const val LIFE:String       = "Life"
    const val WORK:String       = "Work"
    const val NOCAT:String      = "No category"

    const val TRAVEL_COLOR:String       = "@color/travel"
    const val PERSONAL_COLOR:String     = "@color/personal"
    const val LIFE_COLOR:String         = "@color/life"
    const val WORK_COLOR:String         = "@color/work"
    const val NOCAT_COLOR:String        = "@color/nocat"

    fun categoryNameList():ArrayList<String>{

        val list    = ArrayList<String>()

        list.add(TRAVEL)
        list.add(PERSONAL)
        list.add(LIFE)
        list.add(WORK)
        list.add(NOCAT)

        return list
    }

    fun categoryColorList(): ArrayList<String>{

        val list    = ArrayList<String>()

        list.add(TRAVEL_COLOR)
        list.add(PERSONAL_COLOR)
        list.add(LIFE_COLOR)
        list.add(WORK_COLOR)
        list.add(NOCAT_COLOR)

        return list
    }
}