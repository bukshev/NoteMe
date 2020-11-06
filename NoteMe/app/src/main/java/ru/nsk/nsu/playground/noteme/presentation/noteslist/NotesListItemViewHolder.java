//
//  File: NotesListItemViewHolder.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.presentation.noteslist;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.nsk.nsu.playground.noteme.model.Note;

public final class NotesListItemViewHolder extends RecyclerView.ViewHolder {

    private static final String LOG_TAG = NotesListItemViewHolder.class.getCanonicalName();

    private Note item;

    public NotesListItemViewHolder(@NonNull final View itemView) {
        super(itemView);
        // TODO: Method without implementation
    }

    void onBind(final Note item) {
        // TODO: Method without implementation
    }

    private View.OnClickListener onItemClickListener() {
        return view -> {
            if (null == item) {
                Log.e(LOG_TAG, "Note item is null in ViewHolder.");
                return;
            }
            // TODO: Method without implementation
        };
    }
}