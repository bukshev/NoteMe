//
//  File: Note.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

public final class Note implements Parcelable {

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

    protected Note(Parcel in) {
        final String[] data = new String[3];
        in.readStringArray(data);

        this.noteId = UUID.fromString(data[0]);
        this.notePriority = NotePriority.valueOf(data[1]);
        this.title = data[2];

        this.createdDate = (Date) in.readSerializable();
        this.content = in.readString();

        final int bytesCount = in.readInt();
        final byte[] bytes = new byte[bytesCount];
        in.readByteArray(bytes);
        this.imageBytes = bytes;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(final Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {
               this.noteId.toString(),
               this.notePriority.name(),
               this.title
        });

        parcel.writeSerializable(this.createdDate);
        parcel.writeString(this.content);
        parcel.writeInt(this.imageBytes.length);
        parcel.writeByteArray(this.imageBytes);
    }
}
