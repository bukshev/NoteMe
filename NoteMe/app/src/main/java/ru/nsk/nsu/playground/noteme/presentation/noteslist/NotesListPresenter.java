//
//  File: NotesListPresenter.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.presentation.noteslist;

import android.util.Log;

import java.util.List;

import ru.nsk.nsu.playground.noteme.data.notes.NotesRepository;
import ru.nsk.nsu.playground.noteme.domain.notes.LoadNotesUseCase;
import ru.nsk.nsu.playground.noteme.domain.common.DomainLayerError;
import ru.nsk.nsu.playground.noteme.domain.common.IUseCaseExecutor;
import ru.nsk.nsu.playground.noteme.domain.common.UseCase;
import ru.nsk.nsu.playground.noteme.model.Note;
import ru.nsk.nsu.playground.noteme.presentation.common.PresentationLayerError;

public final class NotesListPresenter implements NotesListModuleContract.Presenter {

    private static final String LOG_TAG = NotesListPresenter.class.getCanonicalName();

    private final NotesListModuleContract.View view;
    private final IUseCaseExecutor useCaseExecutor;

    private LoadNotesUseCase loadNotesUseCase;

    public NotesListPresenter(final NotesListModuleContract.View view,
                              final IUseCaseExecutor useCaseExecutor,
                              final LoadNotesUseCase loadNotesUseCase) {

        this.view = view;
        this.useCaseExecutor = useCaseExecutor;
        this.loadNotesUseCase = loadNotesUseCase;

        view.linkPresenter(this);
    }

    @Override
    public void onDestroy() {
        Log.v(LOG_TAG, "Destroying NotesRepository and nullify all use cases.");
        // Можем прокидывать команду в UseCase, можем делать и в Presenter'е.
        NotesRepository.destroyInstance();
        loadNotesUseCase = null;
    }

    @Override
    public void loadNotes() {
        Log.i(LOG_TAG, "Loading notes with.");
        final LoadNotesUseCase.RequestValues requestValues = new LoadNotesUseCase.RequestValues();

        useCaseExecutor.execute(loadNotesUseCase, requestValues, new UseCase.IUseCaseCallback<LoadNotesUseCase.ResponseValues, DomainLayerError>() {
            @Override
            public void onSuccess(final LoadNotesUseCase.ResponseValues successResponse) {
                final List<Note> notes = successResponse.getNotes();
                Log.d(LOG_TAG, "Successfully obtained " + notes.size() + " notes.");
                view.showNotes(notes);
            }

            @Override
            public void onError(final DomainLayerError error) {
                Log.e(LOG_TAG, "Error occurred while loading notes. Details: " + error.toString());
                final PresentationLayerError presentationLayerError = getPresentationLayerError(error);
                view.showError(presentationLayerError);
            }
        });
    }

    private PresentationLayerError getPresentationLayerError(final DomainLayerError domainLayerError) {
        // TODO: Method without implementation
        return null;
    }
}
