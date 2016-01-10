package com.practice.tms.dao;
import com.practice.tms.domain.Employee;
import com.practice.tms.exception.TMSException;
/**
 *
 * @author jyothitamma.
 *
 */
public interface ILoginDao {

    /**
     * method for an employee to login.
     * @param userName { @link long}
     * @param password { @link String}
     * @return String array { @link String}
     * @throws TMSException {@link TMSException}
     */
    String[] login(long userName, String password) throws TMSException;
/**
 * Declaration of retrieveQuestion method.
 * @param employeeId {@link long}
 * @return employee {@link Employee}
 * @throws TMSException {@link TMSException}
 */
    Employee retrieveQuestion(long employeeId) throws TMSException;
/**
 * DEclaration of reset password.
 * @param employeeId {@link long}
 * @param password {@link String}
 * @return employee {@link Employee}
 * @throws TMSException {@link TMSException}
 */
    Employee resetPassword(long employeeId,
    String password) throws TMSException;
}
