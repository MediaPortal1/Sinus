package com.alexpoltavets.sinusview.lib

import android.content.Context
import android.content.res.Resources
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.view.SurfaceHolder
import com.alexpoltavets.sinusview.R

class SinusThread(val holder: SurfaceHolder, val context: Context) : Thread() {

    var prevTime: Long = System.currentTimeMillis()
    var prevX = 0f
    var prevY = 0f
    var paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.strokeWidth = 3f
        it.style = Paint.Style.STROKE
        it.color = ContextCompat.getColor(context, R.color.colorAccent)
    }

    var runFlag: Boolean = false

    override fun run() {
        super.run()
        while (runFlag) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - prevTime > 2) {
                prevTime = currentTime
                val canvas = holder.lockCanvas()
                if(prevY == 0f){
                    prevY = (canvas.height/2).toFloat()
                }
                canvas.drawLine(prevX,prevY, prevX+0.3f, prevY, paint)
                prevX += 0.3f
                holder.unlockCanvasAndPost(canvas)
            }
        }
    }
}