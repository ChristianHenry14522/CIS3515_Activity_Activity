package edu.temple.activities

import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val selectedSize = intent.getIntExtra("TEXT_SIZE", 20)

        with(findViewById<TextView>(R.id.lyricsDisplayTextView)) {

            // this.textSize = selectedSize.toFloat()
            setTextSize(TypedValue.COMPLEX_UNIT_SP, selectedSize.toFloat() )
        }
    }
}
