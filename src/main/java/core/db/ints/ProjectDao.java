package core.db.ints;

import core.db.entity.Project;

import java.util.List;

public interface ProjectDao {

	void addProject(Project project);
	void deleteProject(Project project);
	List<Project> getAll();
	Project getById(int id);
	void updateProject(Project project);
}
