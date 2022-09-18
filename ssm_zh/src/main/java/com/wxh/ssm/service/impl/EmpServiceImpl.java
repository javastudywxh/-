package com.wxh.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxh.ssm.mapper.EmployeeMapper;
import com.wxh.ssm.pojo.Employee;
import com.wxh.ssm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: WXH
 * @Date: 2022/9/5 - 09 - 05 - 18:24
 */
@Service
@Transactional
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmployeeMapper employeeMapper;
    public List<Employee> getEmployeeALL() {
        return employeeMapper.getEmployeeList();
    }

    @Override
    public PageInfo<Employee> getEmployeePage(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Employee> list=employeeMapper.getEmployeeList();
        PageInfo<Employee> page=new PageInfo<>(list,5);
        return page;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);
    }

    @Override
    public Employee selectById(Integer empId) {
        return employeeMapper.selectById(empId);
    }

    @Override
    public void updateById(Employee employee ) {
        employeeMapper.updateById(employee);
    }

    @Override
    public void deleteById(Integer empId) {
        employeeMapper.deleteById(empId);
    }
}
