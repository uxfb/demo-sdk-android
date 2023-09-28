package ru.uxfeedback.demoapplication.ui.common.interfaces

import ru.uxfeedback.pub.sdk.UxFbColor

interface OnUxFbColorClickListener {
    fun colorClick(colorName: String, color: UxFbColor)
}