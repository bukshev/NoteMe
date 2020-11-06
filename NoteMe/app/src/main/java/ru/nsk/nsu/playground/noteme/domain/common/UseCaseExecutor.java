//
//  File: UseCaseExecutor.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.domain.common;

import android.util.Log;

/**
 * В этом классе можно реализовать механизмы для управления асинхронными задачами и выполнения UseCase'ов.
 */
public final class UseCaseExecutor implements IUseCaseExecutor {

    private static final String LOG_TAG = UseCaseExecutor.class.getCanonicalName();

    private static UseCaseExecutor INSTANCE;

    public static UseCaseExecutor getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new UseCaseExecutor();
        }
        return INSTANCE;
    }

    private UseCaseExecutor() {
    }

    /**
     * @param useCase Инкапуслированная в UseCase бизнес-логика для запуска.
     * @param requestValues Данные, которые необходимы для запуска входящего UseCase'а.
     * @param callback То, каким образом будет обработан результат (как успех, так и неудача).
     * @param <R> Дженерик-тип для input-данных.
     * @param <S> Дженерик-тип для output-данных.
     */
    public <R extends UseCase.IRequestValues, S extends UseCase.IResponseValues> void execute(
            final UseCase<R, S> useCase, final R requestValues, final UseCase.IUseCaseCallback<S, DomainLayerError> callback) {

        Log.d(LOG_TAG, "Start executing useCase '" + useCase.getClass().getName() + "'.");

        useCase.setRequestParameters(requestValues);
        useCase.setCallback(callback);

        useCase.run();
    }
}