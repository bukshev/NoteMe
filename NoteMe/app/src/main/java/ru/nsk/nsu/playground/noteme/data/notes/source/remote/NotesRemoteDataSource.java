//
//  File: NotesRemoteDataSource.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.data.notes.source.remote;

import ru.nsk.nsu.playground.noteme.data.INotesDataSource;

public final class NotesRemoteDataSource implements INotesDataSource {

    private static NotesRemoteDataSource INSTANCE;

    private NotesRemoteDataSource() {
    }

    public static NotesRemoteDataSource getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new NotesRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void loadNotes(final ILoadNotesCallback callback) {
        // TODO: Method without implementation
    }
}
