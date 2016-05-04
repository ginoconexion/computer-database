package com.formation.computerdatabase.persistence;

import com.formation.computerdatabase.core.model.User;

public interface UserDao {
	User findByUserName(String username);
}
