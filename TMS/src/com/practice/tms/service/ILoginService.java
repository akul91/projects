package com.practice.tms.service;
import com.practice.tms.domain.Employee;
import com.practice.tms.exception.TMSException;
    /**
     *
     * @author bgundavarapu
     *
     */
    public interface ILoginService {
    /**
     * method for an employee to login.
     * @param userName { @link long}
     * @param password { @link String}
     * @return String array { @link String}
     * @throws TMSException { @link TMSException}
     */
    String[] login(long userName, String password) throws TMSException;
    /**
     *
     * @param employeeId { @link long}
     * @return Employee
     * @throws TMSException { @link TMSException}
     */
    Employee retrieveQuestion(long employeeId) throws TMSException;
    /**
     *
     * @param employeeId { @link long}
     * @param password { @link String}
     * @return Employee
     * @throws TMSException { @link TMSException}
     */
    Employee resetPassword(long employeeId , String password)
    throws TMSException;
    }