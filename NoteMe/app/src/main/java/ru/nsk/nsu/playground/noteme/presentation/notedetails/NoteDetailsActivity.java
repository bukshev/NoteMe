package ru.nsk.nsu.playground.noteme.presentation.notedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.nsk.nsu.playground.noteme.R;
import ru.nsk.nsu.playground.noteme.application.Constants;
import ru.nsk.nsu.playground.noteme.model.Note;

public class NoteDetailsActivity extends AppCompatActivity {

    public static Intent getIntent(final Context context, final Note note) {
        return new Intent(context, NoteDetailsActivity.class).putExtra(Constants.Extra.NOTE, note);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        if (getIntent().hasExtra(Constants.Extra.NOTE)) {
            final Note note = getIntent().getParcelableExtra(Constants.Extra.NOTE);
            if (null != note) {
                setTitle(note.getTitle());
            }
        }
    }
}