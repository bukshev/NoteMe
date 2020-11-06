//
//  File: IUseCaseExecutor.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.domain.common;

/**
 * За данным интерфейсом мы будем скрывать наш синглтон UseCaseExecutor.
 */
public interface IUseCaseExecutor {

    <R extends UseCase.IRequestValues, S extends UseCase.IResponseValues> void execute(
            final UseCase<R, S> useCase, final R requestValues, final UseCase.IUseCaseCallback<S, DomainLayerError> callback);
}
