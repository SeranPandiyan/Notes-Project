// MainActivity.java
package com.example.notesproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView notesRecyclerView;
    private FloatingActionButton addNoteButton;
    private NotesAdapter notesAdapter;
    private NotesDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new NotesDatabaseHelper(this);
        notesRecyclerView = findViewById(R.id.recyclerView);
        addNoteButton = findViewById(R.id.addNoteButton);

        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadNotes();

        addNoteButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddEditNoteActivity.class)));
    }

    private void loadNotes() {
        List<Note> notes = dbHelper.getAllNotes();
        notesAdapter = new NotesAdapter(notes);
        notesRecyclerView.setAdapter(notesAdapter);
    }
}
