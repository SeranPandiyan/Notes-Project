package com.example.notesproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView notesRecyclerView;
    private NotesAdapter notesAdapter;
    private NotesDatabaseHelper dbHelper;
    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new NotesDatabaseHelper(this);

        notesRecyclerView = findViewById(R.id.recycler_view_notes);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab_add_note);
        fab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddEditNoteActivity.class)));

        setupCategorySpinner();
        loadNotes();
    }

    private void loadNotes() {
        List<Note> notes = dbHelper.getAllNotes();
        if (notesAdapter == null) {
            notesAdapter = new NotesAdapter(notes, this);
            notesRecyclerView.setAdapter(notesAdapter);
        } else {
            notesAdapter.setNotes(notes);
            notesAdapter.notifyDataSetChanged(); // Refresh RecyclerView
        }
    }

    private void setupCategorySpinner() {
        categorySpinner = findViewById(R.id.category_spinner);
        refreshCategorySpinner();

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = (String) parent.getItemAtPosition(position);
                notesAdapter.setNotes(dbHelper.getNotesByCategory(category.equals("All") ? null : category));
                notesAdapter.notifyDataSetChanged(); // Refresh RecyclerView based on category selection
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void refreshCategorySpinner() {
        List<String> categories = dbHelper.getCategories();
        categories.add(0, "All");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes(); // Refresh notes list
        refreshCategorySpinner(); // Refresh categories in spinner
    }
}
