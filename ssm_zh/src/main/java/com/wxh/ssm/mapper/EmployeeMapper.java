package com.wxh.ssm.mapper;

import com.wxh.ssm.pojo.Employee;

import java.util.List;

/**
 * @Auther: WXH
 * @Date: 2022/9/5 - 09 - 05 - 17:35
 */
public interface EmployeeMapper {
    List<Employee> getEmployeeList();

    void addEmployee(Employee employee);

    Employee selectById(Integer empId);

    void updateById(Employee employee );

    void deleteById(Integer empId);
}
