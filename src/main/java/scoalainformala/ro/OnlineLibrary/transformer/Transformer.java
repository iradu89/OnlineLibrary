package scoalainformala.ro.OnlineLibrary.transformer;

public interface Transformer<T, X> {

    X convertEntity(T t);

    T convertDto(X x);
}
