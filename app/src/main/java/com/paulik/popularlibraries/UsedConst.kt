package com.paulik.popularlibraries

import android.annotation.SuppressLint

class UsedConst {

    @SuppressLint("NotConstructor")
    private fun UsedConst() {
        //конструктор, это для того чтобы нельзя было сделать с этим классом
    }

    interface httpsConst {
        companion object {
            const val GIT_CONST = "https://api.github.com/"
        }
    }

    interface settingTimeConst {
        companion object {
            const val ITEM_OUT_CONST = 15L
        }
    }

    interface imageConst {
        companion object {
            const val DEFAULT_IMAGE_CONST = R.drawable.ic_launcher_foreground
        }
    }

    interface bdConstKey {
        companion object {
            const val USERS_BD_KEY = "USERS_BD_KEY"
            const val PROJECT_BD_KEY = "PROJECT_BD_KEY"
        }
    }

}