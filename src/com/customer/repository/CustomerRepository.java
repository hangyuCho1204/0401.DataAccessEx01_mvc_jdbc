package com.customer.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.dto.Customer;

@Repository
public class CustomerRepository {
	@Autowired
	private DataSource dataSource;
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private static final String CUSTOMER_INSERT= 
			" insert into customer( id, name, address, email ) values( 'abc'||id1.nextval, ?, ?, ? ) ";
	public int insert(Customer customer){
		int cnt = 0;
		try {
			
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(CUSTOMER_INSERT);
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getAddress());
			pstmt.setString(3, customer.getEmail());
			
			System.out.println("pstmt.getconn"+pstmt.getConnection());
			
			cnt = pstmt.executeUpdate();
			System.out.println("insert : "+cnt);
			connection.commit();
			
		} catch (Exception e) {
			SQLException err = (SQLException)e;
			cnt = -err.getErrorCode();
			e.printStackTrace();
			System.out.println("e : "+e);
		} finally{
			try {
				if(pstmt != null){
					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
			} catch (Exception e2) {
				System.out.println(e2);
				e2.printStackTrace();
			}
		}
		return cnt;
	}
	
	private static final String CUSTOMER_SELECT= 
			" select  * from customer ";
	public List<Customer> selectAll(){
		List<Customer> lists = new ArrayList<Customer>();
		int cnt= 0;
		try {
			
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(CUSTOMER_SELECT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getString("id"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
				lists.add(customer);
			}
			
			System.out.println("pstmt.getconn"+pstmt.getConnection());
			System.out.println("pstmt.exe"+pstmt.execute());
			
			cnt = pstmt.executeUpdate();
			System.out.println("select : "+cnt);
			connection.commit();
			
		} catch (Exception e) {
			/*SQLException err = (SQLException)e;
			cnt = -err.getErrorCode();*/
			e.printStackTrace();
			System.out.println("e : "+e);
		} finally{
			try {
				if(pstmt != null){
					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
			} catch (Exception e2) {
				System.out.println(e2);
				e2.printStackTrace();
			}
		}
		return lists;
	}
	
	private static final String CUSTOMER_DELETE = " delete customer  where id  = ? ";
	
	public int delete(String pk){
		int cnt = 0;
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(CUSTOMER_DELETE);
			pstmt.setString(1, pk);
			
			cnt = pstmt.executeUpdate();
			System.out.println("delete : "+cnt);
			connection.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(pstmt != null){
					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return cnt;
		
	}
	
	private static final String CUSTOMER_UPDATE = " update customer set address = ?, email = ? where id = ? ";
	
	public int update(Customer customer){
		int cnt = 0;
		
		System.out.println("update id : " + customer.getId());
		System.out.println("update name : " + customer.getName());
		System.out.println("update address : " + customer.getAddress());
		System.out.println("update email : " + customer.getEmail());
		
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(CUSTOMER_UPDATE);
			pstmt.setString(1, customer.getAddress());
			pstmt.setString(2, customer.getEmail());
			pstmt.setString(3, customer.getId());
			
			cnt = pstmt.executeUpdate();
			System.out.println("update : "+cnt);
			
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(connection!=null){
					connection.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
		
		
		return cnt;
	}
	
	private static final String CUSTOMER_SELECT_BY_PK = " select * from customer where id = ? ";
	
	public Customer selectById(String pk){
		Customer customer = new Customer();
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(CUSTOMER_SELECT_BY_PK);
			pstmt.setString(1, pk);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				customer.setId(rs.getString("id"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
			}
			System.out.println("selectPkcustomer.getId() : "+ customer.getId());
			System.out.println("selectPkcustomer.getName() : "+ customer.getName());
			connection.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(pstmt != null){
					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return customer;
		
	}
}
