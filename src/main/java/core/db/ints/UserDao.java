package core.db.ints;

import core.db.entity.User;

public interface UserDao extends DAO<User> {
	User getByEmailAndPassword(String email, String password);
}
