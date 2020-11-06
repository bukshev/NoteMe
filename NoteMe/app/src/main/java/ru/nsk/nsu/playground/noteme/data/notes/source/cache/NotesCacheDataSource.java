//
//  File: NotesCacheDataSource.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.data.notes.source.cache;

import ru.nsk.nsu.playground.noteme.data.INotesDataSource;

public final class NotesCacheDataSource implements INotesDataSource {

    private static NotesCacheDataSource INSTANCE;

    private NotesCacheDataSource() {
    }

    public static NotesCacheDataSource getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new NotesCacheDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void loadNotes(final ILoadNotesCallback callback) {
        // TODO: Method without implementation
    }
}