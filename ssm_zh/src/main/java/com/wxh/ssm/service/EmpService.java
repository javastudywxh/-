package com.wxh.ssm.service;

import com.github.pagehelper.PageInfo;
import com.wxh.ssm.pojo.Employee;

import java.util.List;

/**
 * @Auther: WXH
 * @Date: 2022/9/5 - 09 - 05 - 18:24
 */
public interface EmpService {
    List<Employee> getEmployeeALL();

    PageInfo<Employee> getEmployeePage(Integer pageNum);

    void addEmployee(Employee employee);

    Employee selectById(Integer empId);

    void updateById(Employee employee );

    void deleteById(Integer empId);
}
