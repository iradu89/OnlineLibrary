package scoalainformala.ro.OnlineLibrary.transformer;

import scoalainformala.ro.OnlineLibrary.exceptions.InvalidUserException;

public interface Transformer<T, X> {

    X convertEntity(T t);

    T convertDto(X x) throws InvalidUserException;
}
