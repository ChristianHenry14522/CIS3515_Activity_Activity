package edu.temple.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Array of integers that are multiples of 5 (5..100)
        val textSizes = Array(20) { (it + 1) * 5 }
        Log.d("Array values", textSizes.contentToString())

        with(findViewById<RecyclerView>(R.id.textSizeSelectorRecyclerView)) {
            // Step 2: launch DisplayActivity and pass selected value
            adapter = TextSizeAdapter(textSizes) { selectedSize ->
                val intent = Intent(this@MainActivity, DisplayActivity::class.java).apply {
                    putExtra("TEXT_SIZE", selectedSize)
                }
                startActivity(intent)
            }
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}

/* ===== RecyclerView.Adapter in same file ===== */
class TextSizeAdapter(
    private val textSizes: Array<Int>,
    private val callback: (Int) -> Unit
) : RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>() {

    inner class TextSizeViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {
        init {
            // Step 1: return selected number on click
            textView.setOnClickListener { callback (textSizes[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {
        return TextSizeViewHolder(
            TextView(parent.context).apply {
                setPadding(5, 20, 5, 20)
            }
        )
    }

    override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
        holder.textView.apply {
            text = textSizes[position].toString()
            textSize = textSizes[position].toFloat()
        }
    }

    override fun getItemCount(): Int = textSizes.size
}
