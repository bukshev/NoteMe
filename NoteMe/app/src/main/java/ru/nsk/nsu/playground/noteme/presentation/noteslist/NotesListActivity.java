//
//  File: NotesListActivity.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.presentation.noteslist;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
    
    private static final String LOG_TAG = NotesListActivity.class.getSimpleName(); 

    private NotesListModuleContract.Presenter presenter;

    private RecyclerView recyclerView;
    private NotesListAdapter listAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        Log.d(LOG_TAG, "onCreate: ");

        // Временно (!) инициализируем здесь весь стек в Activity.

        final IUseCaseExecutor useCaseExecutor = UseCaseExecutor.getInstance();
        final INotesDataSource notesDataSource = NotesRepository.getInstance();
        final LoadNotesUseCase loadNotesUseCase = new LoadNotesUseCase(notesDataSource);

        // Таким образом Presenter узнаёт о View, с которым она будет работать.
        // View узнает о Presenter через метод linkPresenter.
        // Сейчас это выглядит как оверхед и вообще костыль (так как презентер можно выставить напрямую),
        // но когда появится Fragment, то всё встанет на свои местаи двойная инициализация уйдёт.
        final NotesListPresenter presenter = new NotesListPresenter(this, useCaseExecutor, loadNotesUseCase);
        this.presenter = presenter;

        presenter.setActivity(this);

        recyclerView = findViewById(R.id.recycler_view_notes);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listAdapter = new NotesListAdapter(presenter, new ArrayList<>());
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart: ");
        // Code...
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume: ");
        // Show progress HUD
        presenter.loadNotes();
    }

    @Override
    protected void onPause() {
        // Code...
        Log.d(LOG_TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        // Code...
        Log.d(LOG_TAG, "onStop: ");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        Log.d(LOG_TAG, "onDestroy: ");
        presenter = null;
        super.onDestroy();
    }

    @Override
    public void linkPresenter(final NotesListModuleContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showNotes(final List<Note> items) {
        // Hide progress hud
        listAdapter.replaceData(items);
    }

    @Override
    public void showNoNotesView() {
        // Hide progress hud
        // TODO: Method without implementation
    }

    @Override
    public void showError(final PresentationLayerError error) {
        // Hide progress hud
        final Toast toast = new Toast(this);
        toast.setText(error.getTitle());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
