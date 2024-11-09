package com.example.notesproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditNoteActivity extends AppCompatActivity {
    private EditText titleEditText, contentEditText, categoryEditText;
    private Button saveButton;
    private NotesDatabaseHelper dbHelper;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        titleEditText = findViewById(R.id.edit_text_title);
        contentEditText = findViewById(R.id.edit_text_content);
        categoryEditText = findViewById(R.id.edit_text_category); // Changed Spinner to EditText
        saveButton = findViewById(R.id.button_save);

        dbHelper = new NotesDatabaseHelper(this);

        int noteId = getIntent().getIntExtra("noteId", -1);
        if (noteId != -1) {
            note = dbHelper.getNoteById(noteId);
            titleEditText.setText(note.getTitle());
            contentEditText.setText(note.getContent());
            categoryEditText.setText(note.getCategory()); // Set category text
        }

        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String content = contentEditText.getText().toString().trim();
            String category = categoryEditText.getText().toString().trim();

            if (note == null) {
                dbHelper.insertNote(new Note(title, content, category));
            } else {
                note.setTitle(title);
                note.setContent(content);
                note.setCategory(category);
                dbHelper.updateNote(note);
            }
            finish();
        });
    }
}
