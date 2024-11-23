package br.com.csouza.interfaces.dao;

import br.com.csouza.entities.Status;
import br.com.csouza.interfaces.database.IIndex;
import br.com.csouza.interfaces.database.IShow;

public interface IStatusDAO extends IShow<Status>, IIndex<Status> {
}
