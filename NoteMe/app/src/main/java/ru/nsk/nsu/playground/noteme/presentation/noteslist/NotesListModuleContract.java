//
//  File: NotesListModuleContract.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.presentation.noteslist;

import java.util.List;

import ru.nsk.nsu.playground.noteme.model.Note;
import ru.nsk.nsu.playground.noteme.presentation.common.IBasePresenter;
import ru.nsk.nsu.playground.noteme.presentation.common.IBaseView;
import ru.nsk.nsu.playground.noteme.presentation.common.PresentationLayerError;

public interface NotesListModuleContract {

    interface View extends IBaseView<Presenter> {
        /**
         * Метод, который вызывается Presenter'ом, когда заметки были успешно загружены.
         */
        void showNotes(final List<Note> items);

        /**
         * Метод, который вызывается Presenter'ом, когда загрузка заметок была успешна, но результат оказался пустым.
         */
        void showNoNotesView();

        /**
         * Метод, который вызывается Presenter'ом, когда загрузка заметок завершилась с ошибкой.
         */
        void showError(final PresentationLayerError error);
    }

    interface Presenter extends IBasePresenter {
        void loadNotes();
        void onDestroy();
    }
}
