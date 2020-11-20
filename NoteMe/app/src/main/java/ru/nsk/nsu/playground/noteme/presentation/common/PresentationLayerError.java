//
//  File: PresentationLayerError.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.presentation.common;

public final class PresentationLayerError {

    private final String title;

    public PresentationLayerError(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
