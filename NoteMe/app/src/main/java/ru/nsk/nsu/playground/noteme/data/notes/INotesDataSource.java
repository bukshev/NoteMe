//
//  File: INotesDataSource.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.data.notes;

import java.util.List;

import ru.nsk.nsu.playground.noteme.data.common.DataLayerError;
import ru.nsk.nsu.playground.noteme.model.Note;

/**
 * Единый интерфейс для всех источников данных типа <Note>.
 * На уровне UseCase'а мы можем решить откуда нам необходимо взять данные:
 * локальная БД на устройстве, подгрузить с сервера, mock-данные для тестирования.
 *
 * Более того, сам Repository должен реализовывать данный интерфейс —
 * он является неким промежуточным уровнем между UseCase и конкретными DataSource.
 */
public interface INotesDataSource {

    /**
     * Так как запросы являются ассинхронными задачами, то обычным возвращаемым значением мы не сможем обойтись —
     * запрос можем завершиться как успешно, так и с ошибкой. В Vanilla-Java мы можем это решить с помощью callback'a.
     */
    void loadNotes(final ILoadNotesCallback callback);

    /**
     * А в самом callback уже расположены два метода — если хотите, можете расположить больше, но
     * для универсальности решения этого достаточно.
     *
     * Успех — к нам придёт список List<Note>
     * Ошибка — Error типа DataLayerError.
     */
    interface ILoadNotesCallback {
        void didLoad(final List<Note> notes);
        void didFailLoad(final DataLayerError error);
    }
}
