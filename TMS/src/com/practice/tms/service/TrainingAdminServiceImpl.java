package com.practice.tms.service;

import java.util.List;

import com.practice.tms.dao.ITrainingAdminDao;
import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
    /**.
     * @author bgundavarapu
     *
     */
    public class TrainingAdminServiceImpl implements ITrainingAdminService {
    /**
     *
     */
    private ITrainingAdminDao trainingAdminDao;
    /**.
     * @return trainingAdminDao
     */
    public final ITrainingAdminDao getTrainingAdminDao() {
    return trainingAdminDao;
    }
    /**.
     * @param AdminDaoObj { @link TrainingAdminDao}
     */
    public final void setTrainingAdminDao(
    final ITrainingAdminDao AdminDaoObj) {
    this.trainingAdminDao = AdminDaoObj;
    }
    /**
     * used to view the history of completed trainings.
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
    public final List<Trainings> viewHistory() throws TMSException {
    try {
    return trainingAdminDao.viewHistory();
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**.
     * used to view the history of a particular training
     * @param trainingId { @link long}
     * @return List<Enrollment>
     * @throws TMSException { @link TMSException}
     */
    public final List<Enrollment> viewHistoryDetails(final long trainingId)
    throws TMSException {
    try {
    return trainingAdminDao.viewHistoryDetails(trainingId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
    .* used to view history of current trainings.
     @throws TMSException { @link TMSException}
     @return List<TrainingScheduled>
    .*/
    public final List<Trainings> viewCurrentTrainings()
    throws TMSException {
    try {
    return trainingAdminDao.viewCurrentTrainings();
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * used to see  all the locations.
     * @return List<Location>
     * @throws TMSException { @link TMSException}
     */
    public final List<Location> retrieveLocations() throws TMSException {
    try {
    return trainingAdminDao.retrieveLocations();
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * used to see all the employees.
     * @return List<Employee>
     * @throws TMSException { @link TMSException}
     */
    public final List<Employee> retrieveEmployees() throws TMSException {
    try {
    return trainingAdminDao.retrieveEmployees();
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @return List<Employee>
     * @throws TMSException { @link TMSException}
     */
    public final List<Employee> getAllUser() throws TMSException {
    try {
    return trainingAdminDao.getAllUser();
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @return { @link int}
     * @param employeeId { @link long}
     * @throws TMSException { @link TMSException}
     */
    public final int deleteUser(final long employeeId) throws TMSException {
    return trainingAdminDao.deleteUser(employeeId);
    }
    /**.
     * method to get employee details
     * @param userId { @link Employee}
     * @return employee object { @link Employee}
     * @throws TMSException { @link TMSException}
     */
    public final Employee getEmployeeDetails(final long userId)
    throws TMSException {
    try {
    return trainingAdminDao.getEmployee(userId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    }