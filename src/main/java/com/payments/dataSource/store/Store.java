package com.payments.dataSource.store;

import java.util.List;
import java.util.Optional;

public interface Store<T> {

    T create(T transferOrder);

    Optional<T> findById(long id);

    List<T> getAll();
}
