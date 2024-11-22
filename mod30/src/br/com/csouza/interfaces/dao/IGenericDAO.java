package br.com.csouza.interfaces.dao;

import br.com.csouza.entities.DatabaseEntity;
import br.com.csouza.interfaces.database.*;

import java.util.Collection;

public interface IGenericDAO <T extends DatabaseEntity> extends IStore<T>, IShow<T>, IIndex<T>, IUpdate<T>, IDestroy<T> {
}
