//
//  File: NotesRepository.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.data.notes;

import ru.nsk.nsu.playground.noteme.data.notes.source.cache.NotesCacheDataSource;
import ru.nsk.nsu.playground.noteme.data.notes.source.mock.NotesMockDataSource;
import ru.nsk.nsu.playground.noteme.data.notes.source.remote.NotesRemoteDataSource;

public final class NotesRepository implements INotesDataSource {

    private static NotesRepository INSTANCE = null;

    private final INotesDataSource cacheDataSource;
    private final INotesDataSource remoteDataSource;
    private final INotesDataSource mockDataSource;

    public static NotesRepository getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new NotesRepository();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Данный репозиторий конкретно закреплён за методам в INotesDataSource,
     * поэтому не будет ошибкой прямая инициализация в private-конструкторе.
     *
     * Но, если удовлетворять букве D из SOLID, то конкретные реализации должны приходить из-вне,
     * чтобы не создавать сущности внутри текущей сущности.
     */
    private NotesRepository() {
        cacheDataSource = NotesCacheDataSource.getInstance();
        remoteDataSource = NotesRemoteDataSource.getInstance();
        mockDataSource = NotesMockDataSource.getInstance();
    }

    @Override
    public void loadNotes(final ILoadNotesCallback callback) {
        mockDataSource.loadNotes(callback);
    }
}
