//
//  File: UseCase.java
//  Project: NoteMe
//
//  Created by Ivan Bukshev on 06/11/2020.
//  Copyright (c) 2020 Ivan Bukshev. All rights reserved.
//

package ru.nsk.nsu.playground.noteme.domain.common;

/**
 * Абстракция для всех UseCase'ов приложения.
 *
 * @param <RequestParametersType> Здесь будет конкретный тип для входных параметров в UseCase —
 *                               то, что необходимо для работы на уровне Repository и ниже.
 *
 * @param <ResponseType> Здесь будет конкретный тип для выходных данных из UseCase —
 *                      то, что будет приходить из Repository (и, с другой стороны то, что ожидается на уровне Presenter).
 */
public abstract class UseCase<RequestParametersType extends UseCase.IRequestValues,
                              ResponseType extends UseCase.IResponseValues> {

    //region Abstract section. Must be overwritten.
    protected abstract void execute(final RequestParametersType parameters);
    //endregion

    //region Private entities. UseCase state.
    private RequestParametersType requestParameters;
    private IUseCaseCallback<ResponseType, DomainLayerError> callback;
    //endregion

    //region Public interface.
    public void run() {
        execute(requestParameters);
    }
    //endregion

    //region Getters and Setters.
    public RequestParametersType getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(final RequestParametersType requestParameters) {
        this.requestParameters = requestParameters;
    }

    public IUseCaseCallback<ResponseType, DomainLayerError> getCallback() {
        return callback;
    }

    public void setCallback(final IUseCaseCallback<ResponseType, DomainLayerError> callback) {
        this.callback = callback;
    }
    //endregion

    //region Internal interfaces which describes Request and Response types.
    public interface IRequestValues {}
    public interface IResponseValues {}

    public interface IUseCaseCallback<DataType, ErrorType> {
        void onSuccess(final DataType successResponse);
        void onError(final ErrorType error);
    }
    //endregion
}
