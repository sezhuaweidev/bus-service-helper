package app.infy.util.repository;

import java.util.Set;

public abstract class AbstractEmployeeRepository<T, ID> {
	public abstract Set<T> searchEmployee(String searchBy, String searchText);
	public abstract T getEmployeeById(ID id);
	public abstract Set<T> getAllEmployeeBy();
	public abstract Set<T> getEmployeeManagers(ID id);
}
