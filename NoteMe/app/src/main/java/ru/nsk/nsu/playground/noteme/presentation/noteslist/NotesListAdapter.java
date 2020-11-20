//
//  File: NotesListAdapter.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.presentation.noteslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.nsk.nsu.playground.noteme.R;
import ru.nsk.nsu.playground.noteme.model.Note;

public final class NotesListAdapter extends RecyclerView.Adapter<NotesListItemViewHolder> {

    private final NotesListModuleContract.Router router;
    private List<Note> items;

    public NotesListAdapter(final NotesListModuleContract.Router router, final List<Note> items) {
        this.router = router;
        this.items = items;
    }

    void replaceData(final List<Note> items) {
        if (null != items) {
            this.items = items;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesListItemViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final Context context = parent.getContext();

        final View view = LayoutInflater
                .from(context)
                .inflate(R.layout.item_list_note, parent, false);

        return new NotesListItemViewHolder(view, router);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesListItemViewHolder holder, final int position) {
        final Note item = items.get(position);
        holder.onBind(item);
    }

    @Override
    public int getItemCount() {
        return (null != items) ? items.size() : 0;
    }
}
