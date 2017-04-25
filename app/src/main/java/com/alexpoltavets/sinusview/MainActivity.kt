package com.alexpoltavets.sinusview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val mWaveDrawable = WaveDrawable(getDrawable(R.drawable.wave))
        mWaveDrawable.isIndeterminate = true
        mWaveDrawable.setWaveLength(100)
        image_view.setImageDrawable(mWaveDrawable)
        image_view.setOnClickListener { Toast.makeText(baseContext, "TEST", Toast.LENGTH_LONG).show() }
    }

    fun init() {
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getValueByPosition(position: Long): Float {
        return position.toFloat()
    }
}