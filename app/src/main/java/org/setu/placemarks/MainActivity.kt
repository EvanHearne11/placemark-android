package org.setu.placemarks

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import kotlin.jvm.java
import timber.log.Timber
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Timber.forest().isEmpty()) {
            Timber.plant(Timber.DebugTree())
        }
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val viewMarksButton =
            findViewById<Button>(R.id.viewMarkButton)

        val addMarkButton =
            findViewById<Button>(R.id.addMarkButton)

        viewMarksButton.setOnClickListener {
            startActivity(
                Intent(this, MarkListActivity::class.java)
            )
        }

        addMarkButton.setOnClickListener {
            startActivity(
                Intent(this, AddEditActivity::class.java)
            )
        }
        viewMarksButton.setOnClickListener {
            Timber.d("Opening mark list")
            startActivity(Intent(this, MarkListActivity::class.java))
        }

        addMarkButton.setOnClickListener {
            Timber.d("Opening add mark screen")
            startActivity(Intent(this, AddEditActivity::class.java))
        }

    }
}
