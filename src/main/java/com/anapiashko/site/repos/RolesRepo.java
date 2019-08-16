package com.anapiashko.site.repos;

import com.anapiashko.site.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepo extends CrudRepository<User, Integer> {

    User findByPosition(String position);
}
