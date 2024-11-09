// AddEditNoteActivity.java
package com.example.notesproject;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditNoteActivity extends AppCompatActivity {

    private EditText titleEditText, contentEditText, categoryEditText;
    private NotesDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        dbHelper = new NotesDatabaseHelper(this);
        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
    }

    public void onSaveNoteClick(View view) {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        String category = categoryEditText.getText().toString();

        Note note = new Note(0, title, content, category);
        dbHelper.addOrUpdateNote(note);
        finish();
    }
}
