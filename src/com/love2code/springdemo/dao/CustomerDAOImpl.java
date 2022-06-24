package com.love2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.love2code.springdemo.entity.Customer;
import com.love2code.springdemo.util.SortUtils;

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
		
		// save/update the customer to the database
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int id) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve the data from the database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, id);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int id) {
		
		// get current hiberbate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete the customer with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", id);
		
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// search by name only if search name is not null
		Query<Customer> query = null;
		
		if(searchName != null && searchName.trim().length() > 0) {
			
			// search for first name or last name ...case insensitive			
			query = currentSession.createQuery("from Customer where lower(firstName) "
					+ "like : theName or lower(lastName) like : theName", Customer.class);
			
			query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
			
		} else {
			
			// search name is empty, so get all customers
			query = currentSession.createQuery("from Customer", Customer.class);
			
		}
		
		// execeute query and get result list
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

	@Override
	public List<Customer> getCustomers(int sortField) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// determine sort field
		String fieldName = null;
		
		switch(sortField) {
		
		case SortUtils.FIRST_NAME:
			
			fieldName = "firstName";
			
			break;
			
		case SortUtils.LAST_NAME:
			
			fieldName = "lastName";
			
			break;
			
		case SortUtils.EMAIL:
			
			fieldName = "email";
			
			break;
			
			default: 
				
				// if nothing matches default to sorting by last name
				
				fieldName = "lastName";
		}
		
		// create a query
		Query<Customer> query = currentSession.createQuery("from Customer order by " + fieldName, Customer.class);
		
		// execute query and get result
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

}
