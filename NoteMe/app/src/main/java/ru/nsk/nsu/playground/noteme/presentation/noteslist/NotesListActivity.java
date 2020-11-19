//
//  File: NotesListActivity.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.presentation.noteslist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.nsk.nsu.playground.noteme.R;
import ru.nsk.nsu.playground.noteme.data.notes.INotesDataSource;
import ru.nsk.nsu.playground.noteme.data.notes.NotesRepository;
import ru.nsk.nsu.playground.noteme.domain.notes.LoadNotesUseCase;
import ru.nsk.nsu.playground.noteme.domain.common.IUseCaseExecutor;
import ru.nsk.nsu.playground.noteme.domain.common.UseCaseExecutor;
import ru.nsk.nsu.playground.noteme.model.Note;
import ru.nsk.nsu.playground.noteme.presentation.common.PresentationLayerError;

public final class NotesListActivity extends AppCompatActivity implements NotesListModuleContract.View {

    private NotesListModuleContract.Presenter presenter;

    private RecyclerView recyclerView;
    private NotesListAdapter listAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        // Временно (!) инициализируем здесь весь стек в Activity.

        final IUseCaseExecutor useCaseExecutor = UseCaseExecutor.getInstance();
        final INotesDataSource notesDataSource = NotesRepository.getInstance();
        final LoadNotesUseCase loadNotesUseCase = new LoadNotesUseCase(notesDataSource);

        // Таким образом Presenter узнаёт о View, с которым она будет работать.
        // View узнает о Presenter через метод linkPresenter.
        // Сейчас это выглядит как оверхед и вообще костыль (так как презентер можно выставить напрямую),
        // но когда появится Fragment, то всё встанет на свои местаи двойная инициализация уйдёт.
        presenter = new NotesListPresenter(this, useCaseExecutor, loadNotesUseCase);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadNotes();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        presenter = null;
        super.onDestroy();
    }

    @Override
    public void linkPresenter(final NotesListModuleContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showNotes(final List<Note> items) {
        // TODO: Method without implementation
    }

    @Override
    public void showNoNotesView() {
        // TODO: Method without implementation
    }

    @Override
    public void showError(final PresentationLayerError error) {
        // TODO: Method without implementation
    }
}
