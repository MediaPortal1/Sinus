package com.alexpoltavets.sinusview.lib

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.alexpoltavets.sinusview.R

class SinusSurfaceView : SurfaceView, SurfaceHolder.Callback {

    val drawThread = SinusThread(holder, context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        holder.addCallback(this)
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        val retry = true
        drawThread.runFlag = false
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        drawThread.runFlag = true
        val canvas = holder?.lockCanvas()
        canvas?.drawColor(ContextCompat.getColor(context, R.color.colorAccent))
        holder?.unlockCanvasAndPost(canvas)
        drawThread.start()
    }
}
