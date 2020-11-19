//
//  File: NotesMockDataSource.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.data.notes.source.mock;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import ru.nsk.nsu.playground.noteme.data.notes.INotesDataSource;
import ru.nsk.nsu.playground.noteme.model.Note;
import ru.nsk.nsu.playground.noteme.model.NotePriority;

public final class NotesMockDataSource implements INotesDataSource {

    private static final String LOG_TAG = NotesMockDataSource.class.getCanonicalName();
    private static final int MOCKED_NOTES_NUMBER = 20;

    private static NotesMockDataSource INSTANCE;

    private NotesMockDataSource() {
    }

    public static NotesMockDataSource getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new NotesMockDataSource();
        }
        return INSTANCE;
    }

    /**
     * Генерируем тестовые mock-данные типа Note.
     * Возвращаем List<Note> в callback как успех.
     */
    @Override
    public void loadNotes(final ILoadNotesCallback callback) {
        final List<Note> notes = getRandomNotes(MOCKED_NOTES_NUMBER);
        Log.d(LOG_TAG, "Generated " + notes.size() + " notes in Mock-storage.");
        callback.didLoad(notes);
    }

    private List<Note> getRandomNotes(final int count) {
        final List<Note> notes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            notes.add(getRandomNote());
        }
        return notes;
    }

    private Note getRandomNote() {
        return new Note(
                UUID.randomUUID(),
                getRandomPriority(),
                "This is a title",
                new Date(),
                "This is a content.",
                null
        );
    }

    private NotePriority getRandomPriority() {
        final NotePriority priority;

        final int seed = getRandomInt();
        if (0 == seed % 3) {
            priority = NotePriority.NOTE_PRIORITY_HIGH;
        } else if (0 == seed % 2) {
            priority = NotePriority.NOTE_PRIORITY_DEFAULT;
        } else {
            priority = NotePriority.NOTE_PRIORITY_LOW;
        }

        return priority;
    }

    private int getRandomInt() {
        return new Random().nextInt() % 100;
    }
}
