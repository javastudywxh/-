package com.wxh.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.wxh.ssm.pojo.Employee;
import com.wxh.ssm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Auther: WXH
 * @Date: 2022/9/5 - 09 - 05 - 17:45
 */
@Controller
public class EmpController {
    @Autowired
    private EmpService empService;
    @RequestMapping(value = "/employeeAll",method = RequestMethod.GET)
    public String getEmployeeAll(Model model){
        List<Employee> list=empService.getEmployeeALL();
        model.addAttribute("list",list);
        return "employee_list";
    }
    @RequestMapping(value = "/employee/page/{pageNum}",method = RequestMethod.GET)
    public String getEmployeePage(@PathVariable("pageNum") Integer pageNum, Model model){
        PageInfo<Employee> page=empService.getEmployeePage(pageNum);
        model.addAttribute("page",page);
        return "employee_page";
    }
    @RequestMapping(value = "/employee/page/add",method = RequestMethod.POST)
    public String getEmployeePage(Employee employee){
        empService.addEmployee(employee);
        return "redirect:/employee/page/1";
    }
    @RequestMapping(value = "/employee/{empId}",method = RequestMethod.GET)
    public String updateById(@PathVariable("empId") Integer empId ,Model model){
        Employee employee=empService.selectById(empId);
        model.addAttribute("employee" ,employee);
        return "employee_update";
    }
    @RequestMapping(value = "/employee/page/update",method = RequestMethod.PUT)
    public String update(Employee employee ){

        empService.updateById(employee);
        return "redirect:/employee/page/1";
    }
    @RequestMapping(value = "/employee/{empId}",method = RequestMethod.DELETE)
    public String update(@PathVariable("empId") Integer empId ){
        empService.deleteById(empId);
        return "redirect:/employee/page/1";
    }
}
