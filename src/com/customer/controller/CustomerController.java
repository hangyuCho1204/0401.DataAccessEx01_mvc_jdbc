package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer.dto.Customer;
import com.customer.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String createMember(@ModelAttribute("customer") Customer customer){
		
		System.out.println("get!¹æ½Ä");
		return "insertCustomer";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String add(@ModelAttribute("customer") Customer customer){
		
		int cnt = service.saveCustomer(customer);
		
		System.out.println(cnt);
		
		return "redirect:/views/customer/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	
	public String list( Model model ){
		
		List<Customer> lists = service.getCustomers();
		
		model.addAttribute("lists", lists);
		
		return "listCustomer";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@ModelAttribute("customer") Customer customer){
		
		int cnt = service.removeCustomers(customer.getId());
		
		System.out.println(cnt);
		
		return "redirect:/views/customer/list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@RequestParam("id") String pk, Model model){
		
		
		
		Customer customer = service.getCustomersById(pk);
		
		System.out.println("customer id : " + customer.getId());
		System.out.println("customer name : " + customer.getName());
		
		model.addAttribute("customer", customer);
		
		return "updateCustomer";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute Customer customer){
		
		System.out.println("cont id : "+customer.getId());
		
		String[] idArr = customer.getId().split(",");
		String id = idArr[idArr.length-1];
		customer.setId(id);
		
		/*String[] nameArr = customer.getName().split(",");
		String[] addArr = customer.getAddress().split(",");
		String[] emailArr = customer.getEmail().split(",");
		
		
		String name = nameArr[nameArr.length-1];
		String add = addArr[addArr.length-1];
		String email = emailArr[emailArr.length-1];
		
		
		customer.setName(name);
		customer.setAddress(add);
		customer.setEmail(email);*/
		
		int cnt = service.modifyCustomers(customer);
		
		System.out.println(cnt);
		
		return "redirect:/views/customer/list";
	}
	
}
