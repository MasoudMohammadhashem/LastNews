package com.mohammadhashem.lastnews.presentation.ui.adapter.listerner

interface OnClickAdapter {
    fun onClickRoot(SourceId: String,SourceName:String)
    fun onClickLink(link: String)
}