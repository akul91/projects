package com.practice.tms.service;
import com.practice.tms.dao.LoginDaoImpl;
import com.practice.tms.domain.Employee;
import com.practice.tms.exception.TMSException;
    /**
     *
     * @author bgundavarapu
     *
     */
    public class LoginServiceImpl implements ILoginService {
    /**.
     * declaration of loginDAOImpl
     */
    private LoginDaoImpl loginDAOImpl;
    /**.
     *setter injection of loginDAOImpl
     *@param loginDAOImplObj { @link LoginDaoImpl}
     */
    public final void setLoginDAOImpl(final LoginDaoImpl loginDAOImplObj) {
    this.loginDAOImpl = loginDAOImplObj;
    }
    /**.
     * method for an employee to login.
     * @param userId { @link long}
     * @param password { @link String}
     * @return String array { @link String}
     * @throws TMSException { @link TMSException}
     */
    public final String[] login(final long userId,
    final String password)throws TMSException {
    try {
    return loginDAOImpl.login(userId, password);
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @param employeeId { @link long}
     * @return Employee
     * @throws TMSException { @link TMSException}
     */
    public final Employee retrieveQuestion(final long employeeId)
    throws TMSException {
    return loginDAOImpl.retrieveQuestion(employeeId);
    }
    /**
     * @param employeeId { @link long}
     * @param password { @link password}
     * @return Employee
     * @throws TMSException { @link TMSException}
     */
    public final Employee resetPassword(final long employeeId
    , final String password)
    throws TMSException {
    return loginDAOImpl.resetPassword(employeeId, password);
    }
    }