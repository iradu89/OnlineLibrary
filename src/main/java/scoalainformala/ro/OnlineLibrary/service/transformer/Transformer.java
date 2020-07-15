package scoalainformala.ro.OnlineLibrary.service.transformer;

public interface Transformer <E, D> {

    E toEntity(D d);
    D toDto(E e);
}
