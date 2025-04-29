package com.example.notego

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notego.Models.Note
import com.example.notego.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddNote : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var note: Note
    private lateinit var old_note: Note
    private var isUpdate = false
    private var selectedPriority: String = "Low"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Try to get existing note data (for updating)
        try {
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.etTitle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            selectedPriority =
                (old_note.priority ?: "Low").toString()

            isUpdate = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Set up priority buttons
        binding.btnHigh.setOnClickListener {
            setPriority("High")
        }

        binding.btnMedium.setOnClickListener {
            setPriority("Medium")
        }

        binding.btnLow.setOnClickListener {
            setPriority("Low")
        }

        // Handle save (checkmark button)
        binding.imgCheck.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val note_desc = binding.etNote.text.toString()

            if (title.isNotEmpty() || note_desc.isNotEmpty()) {
                val formatter = SimpleDateFormat("EEE,d MMM yyyy HH:mm a")
                if (isUpdate) {
                    note = Note(
                        old_note.id, title, note_desc, selectedPriority, formatter.format(Date())
                    )
                } else {
                    note = Note(
                        null, title, note_desc, selectedPriority, formatter.format(Date())
                    )
                }
                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this@AddNote, "Please enter some data", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle back button click
        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }
    }


    private fun setPriority(priority: String) {
        selectedPriority = priority


        binding.btnHigh.setBackgroundColor(
            if (priority == "High") resources.getColor(R.color.red) else resources.getColor(R.color.transparent)
        )
        binding.btnMedium.setBackgroundColor(
            if (priority == "Medium") resources.getColor(R.color.orange) else resources.getColor(R.color.transparent)
        )
        binding.btnLow.setBackgroundColor(
            if (priority == "Low") resources.getColor(R.color.green) else resources.getColor(R.color.transparent)
        )
    }
}
