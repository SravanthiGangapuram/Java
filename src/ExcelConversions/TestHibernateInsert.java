package ExcelConversions;

import java.math.BigDecimal;
import java.util.Date;

//import hibernate.test.dto.EmployeeEntity;

import org.hibernate.Session;

import model.Employee;

public class TestHibernateInsert {
	
	public static void main(String[] args) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
       
		//Add new Employee object
		Employee emp = new Employee();
		emp.setBirthDate(new Date("1/1/1"));
		emp.setName("lokesh");
		emp.setPayment(new BigDecimal(1.11));
		
		session.save(emp);

		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}

}
