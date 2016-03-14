package core.db.ints;

import core.db.entity.Office;

import java.util.List;

public interface OfficeDao {
	
	void addOffice(Office office);
	void deleteOffice(Office office);
	List<Office> getAll();
	Office getById(int id);
	void updateOffice(Office office);
}
