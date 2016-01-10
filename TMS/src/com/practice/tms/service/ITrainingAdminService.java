package com.practice.tms.service;

import java.util.List;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
    /**
     * @author bgundavarapu
     *
     */
    public interface ITrainingAdminService {
    /**
     * used to see  all the locations.
     * @return List<Location>
     * @throws TMSException { @link TMSException}
     */
    List<Location> retrieveLocations() throws TMSException;
    /**
     * used to view the history of completed trainings.
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
    List<Trainings> viewHistory() throws TMSException;
    /**.
     * used to view the history of trainings for a particular trainingId
     * @param trainingId { @link long}
     * @return List<Enrollment>
     * @throws TMSException { @link TMSException}
     */
    List<Enrollment> viewHistoryDetails(long trainingId)
    throws TMSException;
    /**
     * used to view history of current trainings.
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
    List<Trainings> viewCurrentTrainings() throws TMSException;
    /**
     * used to see all the employees.
     * @return List<Employee>
     * @throws TMSException { @link TMSException}
     */
    List<Employee> retrieveEmployees() throws TMSException;
    /**
     *
     * @param employeeId { @link long}
     * @return id
     * @throws TMSException { @link TMSException}
     */
    int deleteUser(long employeeId) throws TMSException;
    /**
     *
     * @return List<Employee>
     * @throws TMSException { @link TMSException}
     */
    List<Employee> getAllUser() throws TMSException;
    /**.
     * method to get employee details
     * @param userId { @link Employee}
     * @return employee object { @link Employee}
     * @throws TMSException { @link TMSException}
     */
    Employee getEmployeeDetails(long userId) throws TMSException;
    }