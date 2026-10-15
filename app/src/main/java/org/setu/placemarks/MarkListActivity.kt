package org.setu.placemarks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
import timber.log.Timber
class MarkListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlacedMarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mark_list)

        recyclerView =
            findViewById(R.id.marksRecyclerView)


        adapter = PlacedMarkAdapter(
            marks = AppData.placedMarks.findAll(),
            listener = object : OnMarkListener {

                override fun onMarkClick(mark: PlacedMark) {
                    editMark(mark)
                }

                override fun onMarkLongClick(mark: PlacedMark): Boolean {
                    confirmDeleteMark(mark)
                    return true
                }
            }
        )


        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter = adapter

        val returnButton =
            findViewById<Button>(R.id.returnButton)

        returnButton.setOnClickListener {

            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }

    override fun onResume() {
        super.onResume()

        if (::adapter.isInitialized) {

            adapter.updateMarks(
                AppData.placedMarks.findAll()
            )
        }
    }

    private fun editMark(mark: PlacedMark) {
        Timber.d("Editing mark id=${mark.id}")

        val intent = Intent(this, AddEditActivity::class.java)
        intent.putExtra("id", mark.id)
        startActivity(intent)
    }

    private fun deleteMark(mark: PlacedMark) {
        AppData.placedMarks.delete(mark.id)
        Timber.d("Deleted mark id=${mark.id}")

        adapter.updateMarks(AppData.placedMarks.findAll())
    }

    private fun confirmDeleteMark(mark: PlacedMark) {
        AlertDialog.Builder(this)
            .setTitle("Delete mark")
            .setMessage("Delete this mark?")
            .setPositiveButton("Delete") { _, _ ->
                deleteMark(mark)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                Timber.d("Deletion cancelled: mark id=${mark.id}")
            }
            .show()
    }

}
