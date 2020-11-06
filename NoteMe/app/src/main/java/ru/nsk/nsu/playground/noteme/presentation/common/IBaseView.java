//
//  File: IBaseView.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.presentation.common;

public interface IBaseView<T extends IBasePresenter> {

    /**
     * В Vanilla MVP связь "View к Presenter" 1 к 1 — View узнаёт о Presenter через данный метод.
     * Presenter может узнать на View, к примеру, на уровне инициализации самого класса.
     */
    void linkPresenter(final T presenter);
}
