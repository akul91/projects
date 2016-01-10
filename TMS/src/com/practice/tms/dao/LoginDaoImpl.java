package com.practice.tms.dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.practice.tms.domain.Employee;
import com.practice.tms.exception.TMSException;
/**
 *
 * @author jyothitamma
 *
 */
public class LoginDaoImpl implements ILoginDao {
/**.
 * declaration of hibernate template
 */
private HibernateTemplate hibernateTemplate;
/**.
 * setter injection of session factory
 * @param sessionFactory { @link SessionFactory}
 */
public final void setSessionFactory(final SessionFactory sessionFactory) {
this.hibernateTemplate = new HibernateTemplate(sessionFactory);
}

/**
 * method for an employee to login.
 * @param userId { @link long}
 * @param password { @link String}
 * @return String array { @link String}
 * @throws TMSException {@link TMSException}
 */
@SuppressWarnings("unchecked")
public final String[] login(final long userId,
    final String password) throws TMSException {
        String[] check = new String[2];
            try {
                    final List<Employee> employeeList =
         hibernateTemplate.find("from Employee");
    for (Employee employee: employeeList) {
            if ((employee.getEmployeeId()) == userId) {
               if ((employee.getPassword()).equals(password)) {
                   if ((employee.getEmployeeCategory()).equals("e")) {
                       check[0] = "1";
                        check[1] = employee.getEmployeeName();
                        return check;
               } else if ((employee.getEmployeeCategory()).equals("p")) {
                check[0] = "2";
                   check[1] = employee.getEmployeeName();
                   return check;
                   } else if ((employee.getEmployeeCategory()).equals("t")) {
                   check[0] = "3";
                   check[1] = employee.getEmployeeName();
                   return check;
                   }
                   }
               }
            }
    check[0] = "0";
    } catch (Exception e) {
            throw new TMSException(e.toString());
            }
            return check;
            }
/**
 *method to retrieveQuestion.
 * @param employeeId { @link long}
 * @return { @link Employee}
 * @throws TMSException {@link TMSException}
 */
    @SuppressWarnings("unchecked")
    public final Employee retrieveQuestion(
    final long employeeId) throws TMSException {
    List<Employee> question = new ArrayList<Employee>();
    try {
    List<Long> listcount = hibernateTemplate.find("select count(*)"
    + " from Employee e where e.employeeId = " + employeeId);
    Iterator<Long> iteratorcount = listcount.iterator();
    long count = iteratorcount.next();
        if (count > 0) {
    question = hibernateTemplate.find("from Employee e"
        + " where e.employeeId = " + employeeId);
         Iterator<Employee> iterator = question.iterator();
         Employee employee = new Employee();
         employee = iterator.next();
         return employee;
        } else {
        Employee employee = new Employee();
        employee.setSecurityQuestion(0);
        return employee;
        }
        } catch (Exception e) {
        throw new TMSException(e.toString());
    }
}
     /**
     *method to reset password.
     *@param employeeId { @link long}
     *@param password { @link String}
     *@return { @link Employee}
     *@throws TMSException {@link TMSException}
     */
    @SuppressWarnings("unchecked")
    public final Employee resetPassword(final long employeeId,
    final String password)
    throws TMSException {
    List<Employee> list = hibernateTemplate.find("from Employee"
    + " where employeeId = " + employeeId);
    Iterator<Employee> iterator = list.iterator();
    Employee employee = iterator.next();
    try {
    employee.setPassword(password);
    hibernateTemplate.saveOrUpdate(employee);
    return employee;
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
}
}
