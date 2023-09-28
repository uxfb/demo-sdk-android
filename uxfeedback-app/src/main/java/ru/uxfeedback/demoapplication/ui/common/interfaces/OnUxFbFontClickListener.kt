package ru.uxfeedback.demoapplication.ui.common.interfaces

import ru.uxfeedback.pub.sdk.UxFbFont

interface OnUxFbFontClickListener {
    fun fontClick(fontName: String, font: UxFbFont)
}