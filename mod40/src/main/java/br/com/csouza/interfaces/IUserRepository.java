package br.com.csouza.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.csouza.domain.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

}
