package com.example.jonathan.gesturenotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import org.w3c.dom.Text
import java.lang.Math.abs

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    var movement: GestureDetectorCompat? = null
    lateinit var gesture: TextView
    lateinit var movingImage: ImageView
    var x1: Float = 0.0f
    var x2: Float = 0.0f
    var y1: Float = 0.0f
    var y2: Float = 0.0f
    var movementX: Int = 0
    var movementY: Int = 0

    companion object{
        const val MIN_DISTANCE = 150
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gesture = findViewById(R.id.gesture_text)
        movement = GestureDetectorCompat(this, this)
        movement?.setOnDoubleTapListener(this)
        movingImage = findViewById(R.id.movingImage)


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        movement?.onTouchEvent(event!!)

        when (event?.action) {
            0-> //When we start to swipe
            {
                x1 = event.x
                y1 = event.y
            }
            1-> //When we end the swipe
            {
                x2 = event.x
                y2 = event.y

                val changeX: Float = x2 - x1
                val changeY: Float = y2 - y1

                if (abs(changeX) > MIN_DISTANCE) {
                    if (x2 > x1) {
                        Toast.makeText(this, "You swiped right", Toast.LENGTH_SHORT).show()
                        movementX += 50
                        refresh()

                 }else {
                    Toast.makeText(this, "You swiped left", Toast.LENGTH_SHORT).show()
                        movementX -= 50
                        refresh()
                }
            } else if (abs(changeY) > MIN_DISTANCE) {
                if (y2 > y1) {
                    Toast.makeText(this, "You swiped down", Toast.LENGTH_SHORT).show()
                    movementY += 50
                    refresh()
                } else {
                    Toast.makeText(this, "You swiped up", Toast.LENGTH_SHORT).show()
                    movementY -= 50
                    refresh()
                }
                }
        }
        }

        return super.onTouchEvent(event)
    }

    private fun refresh() {
        movingImage.translationX = movementX.toFloat()
        movingImage.translationY = movementY.toFloat()
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        gesture.text = "onDown"
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {
        gesture.text = "onShowPress"
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        gesture.text = "onSingleTapUp"
        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        gesture.text = "onScroll"
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {
        gesture.text = "onLongPress"
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        gesture.text = "onFling"
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
        gesture.text = "onSingleTapConfirmed"
        return true

    }

    override fun onDoubleTap(p0: MotionEvent?): Boolean {
        gesture.text = "onDoubleTap"
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
        gesture.text = "onDoubleTapEvent"
        return true
    }
}