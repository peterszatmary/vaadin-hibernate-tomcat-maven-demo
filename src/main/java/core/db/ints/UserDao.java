package core.db.ints;

import core.db.entity.User;

import java.util.List;

public interface UserDao {
	
	void addUser(User user);
	void deleteUser(User user);
	List<User> getAll();
	User getById(int id);
	void updateUser(User user);
}
