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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.nsk.nsu.playground.noteme.R;
import ru.nsk.nsu.playground.noteme.model.Note;

public final class NotesListItemViewHolder extends RecyclerView.ViewHolder {

    private static final String LOG_TAG = NotesListItemViewHolder.class.getCanonicalName();

    private TextView titleTextView;
    private TextView contentTextView;
    private TextView dateCreatedTextView;

    private final NotesListModuleContract.Router router;
    private Note item;

    public NotesListItemViewHolder(@NonNull final View itemView, final NotesListModuleContract.Router router) {
        super(itemView);

        this.router = router;

        titleTextView = itemView.findViewById(R.id.title_text_view);
        contentTextView = itemView.findViewById(R.id.content_text_view);
        dateCreatedTextView = itemView.findViewById(R.id.date_created_text_view);

        itemView.setOnClickListener(onItemClickListener());
    }

    void onBind(final Note item) {
        this.item = item;

        titleTextView.setText(item.getTitle());
        contentTextView.setText(item.getContent());

        final String dateString = getDateString(item.getCreatedDate());
        dateCreatedTextView.setText(dateString);
    }

    private View.OnClickListener onItemClickListener() {
        return view -> {
            if (null == item) {
                Log.e(LOG_TAG, "Note item is null in ViewHolder.");
                return;
            }
            router.goToTheDetailsScreen(item);
        };
    }

    private String getDateString(final Date date) {
        return SimpleDateFormat.getInstance().format(date);
    }
}