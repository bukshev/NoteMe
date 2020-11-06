//
//  File: LoadNotesUseCase.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.domain;

import java.util.List;

import ru.nsk.nsu.playground.noteme.data.INotesDataSource;
import ru.nsk.nsu.playground.noteme.data.common.DataLayerError;
import ru.nsk.nsu.playground.noteme.domain.common.DomainLayerError;
import ru.nsk.nsu.playground.noteme.domain.common.UseCase;
import ru.nsk.nsu.playground.noteme.model.Note;

/**
 * Инкапсулированная бизнес-логика в рамках задачи "Загрузка всех заметок".
 */
public final class LoadNotesUseCase extends UseCase<LoadNotesUseCase.RequestValues, LoadNotesUseCase.ResponseValues> {

    private final INotesDataSource notesRepository;

    public LoadNotesUseCase(final INotesDataSource notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    protected void execute(final RequestValues parameters) {
        notesRepository.loadNotes(new INotesDataSource.ILoadNotesCallback() {
            // На уровне Repository нам удалось загрузить/получить notes.
            @Override
            public void didLoad(final List<Note> notes) {
                final ResponseValues responseValues = new ResponseValues(notes);
                // Этот callback мы передавали из Presenter в UseCaseExecutor.
                getCallback().onSuccess(responseValues);
            }

            // На уровне Repository произошла ошибка во время загрузки/получения notes.
            @Override
            public void didFailLoad(final DataLayerError error) {
                final DomainLayerError domainLayerError = getDomainLayerError(error);
                getCallback().onError(domainLayerError);
            }
        });
    }

    //region Request and Response types.
    public static final class RequestValues implements UseCase.IRequestValues {
        // Входные данные на данном этапе не нужны.
        // Но для реализации UseCase'а нужна хотя бы пустая реализация.
    }

    public static final class ResponseValues implements UseCase.IResponseValues {
        private final List<Note> notes;

        public ResponseValues(final List<Note> notes) {
            this.notes = notes;
        }

        public List<Note> getNotes() {
            return notes;
        }
    }
    //endregion

    private DomainLayerError getDomainLayerError(final DataLayerError dataLayerError) {
        // TODO: Method without implementation.
        return null;
    }
}
