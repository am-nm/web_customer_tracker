package com.love2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.love2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	// need to inject session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
	
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		// create query and sort customer by last name
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", 
															Customer.class);
		
		
		// execute query and get result list
		List<Customer> customers = query.getResultList();
		
		
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer to the database
		currentSession.save(theCustomer);
	}

}
