package br.com.lca.api.domain.services;

import java.util.List;

public interface ServiceStrategy<T, C, U> {

    T findById(Long id);

    T create(C createDTO);

    List<T> findAll();

    T update(Long id, U updateDTO);

    void delete(Long id);
}
