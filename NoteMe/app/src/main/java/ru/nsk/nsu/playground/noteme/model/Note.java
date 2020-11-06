//
//  File: Note.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.model;

import java.util.Date;
import java.util.UUID;

public final class Note {

    private final UUID noteId;
    private final NotePriority notePriority;
    private final String title;
    private final Date createdDate;
    private final String content;
    private final byte[] imageBytes;

    public Note(final UUID noteId,
                final NotePriority notePriority,
                final String title,
                final Date createdDate,
                final String content,
                final byte[] imageBytes) {

        this.noteId = noteId;
        this.notePriority = notePriority;
        this.title = title;
        this.createdDate = createdDate;
        this.content = content;
        this.imageBytes = imageBytes;
    }

    public UUID getNoteId() {
        return noteId;
    }

    public NotePriority getNotePriority() {
        return notePriority;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getContent() {
        return content;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }
}
