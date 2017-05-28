package com.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.dto.Customer;
import com.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	
	public int saveCustomer(Customer customer){
		//sverCustomer는 커스터머를 받아서 DB에 저장하는 역할을 함.
		
		return customerRepository.insert(customer);
	}
	
	public List<Customer> getCustomers(){
		
		
		return customerRepository.selectAll();
	}
	
	public int removeCustomers(String pk){
		
		
		return customerRepository.delete(pk);
	}
	
	public int modifyCustomers(Customer customer){
		System.out.println("uptCustomers id : "+customer.getId());
		
		return customerRepository.update(customer);
	}
	
	public Customer getCustomersById(String pk){
		
		
		return customerRepository.selectById(pk);
	}
	
}
