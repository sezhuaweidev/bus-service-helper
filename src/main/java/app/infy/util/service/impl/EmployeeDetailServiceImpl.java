package app.infy.util.service.impl;

import org.springframework.stereotype.Service;

import app.infy.util.entity.EmployeeDetail;
import app.infy.util.repository.EmployeeDetailRepository;
import app.infy.util.service.EmployeeService;

@Service
public class EmployeeDetailServiceImpl implements EmployeeService {

	private EmployeeDetailRepository employeeDetailRepository;
	
	public EmployeeDetailServiceImpl(EmployeeDetailRepository employeeDetailRepository) {
		this.employeeDetailRepository = employeeDetailRepository;
	}
	
	@Override
	public EmployeeDetail getEmployeeDetailById(Integer id) {
		return employeeDetailRepository.findById(id).orElse(new EmployeeDetail());
	}

	@Override
	public String getEmployeeNameAndId(String empId) {
		Integer id = Integer.parseInt(empId);
		EmployeeDetail employeeDetail = employeeDetailRepository.findById(id).orElse(new EmployeeDetail());
		String empName = "";
		if(employeeDetail!=null){
			empName = employeeDetail.getEmpName()+"("+employeeDetail.getEmpId()+")";
		}
		return empName;
	}

}
