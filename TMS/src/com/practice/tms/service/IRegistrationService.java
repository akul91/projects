package com.practice.tms.service;

import java.util.ArrayList;
import java.util.List;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Feedback;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.TrainingInformation;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
    /**
     *
     * @author bgundavarapu
     *
     */
    public interface IRegistrationService {
    /**
     * method to register new employee details.
     * @param employee { @link Employee}
     * @return employeeId { @link Employee}
     * @throws TMSException { @link TMSException}
     */
    long register(Employee employee) throws TMSException;
    /**
     * method to retrieve all the project manager ids.
     * @return list of project manager ids { @link Employee}
     * @throws TMSException { @link TMSException}
     */
    ArrayList<Long> projectManagerId() throws TMSException;
    /**.
     * method to get employee details
     * @param userId { @link Employee}
     * @return employee object { @link Employee}
     * @throws TMSException { @link TMSException}
     */
    Employee getEmployeeDetails(long userId) throws TMSException;
    /**.
     * method to update the password
     * @param employee { @link Employee}
     * @throws TMSException { @link TMSException}
     */
    void update(Employee employee) throws TMSException;
    /**
     *
     * @param userId { @link Employee}
     * @return employee object { @link Employee}
     * @throws TMSException { @link TMSException}
     */
    String checkPassword(long userId) throws TMSException;
    /**.
     * This method is used to retrieve location of employees
     * @return List<Location>
     * @throws TMSException { @link TMSException}
     */
    List<Location> retrieveLocations() throws TMSException;
    /**.
     * This method is used to retrieve all the employees
     * @return List<Employee>
     * @throws TMSException { @link TMSException}
     */
    List<Employee> retrieveEmployees() throws TMSException;
    /**.
     * This method is used to retrieve all the training requested.
     * @param employeeId { @link long}
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
    List<Trainings> requestTrainings(long employeeId)
    throws TMSException;
    /**.
      * This method is used to cancel the training of employees.
      * @param requestId { @link long}
      * @return Integer< @ link int>
      * @throws TMSException { @link TMSException}
      */
     int cancelTraining(long requestId) throws TMSException;
     /**.
      * This method is used to retrieve the training id of employees
      * @param employeeId { @link long}
      * @return List<TrainingScheduled>
      * @throws TMSException { @link TMSException}
      */
     List<Trainings> retrieveTrainingId(long employeeId)
    throws TMSException;
     /**.
      * This method is used to retrieve feedback.
      * @param trainingId { @link long}
      * @return List<Enrollment>
      * @throws TMSException { @link TMSException}
      */
    List<Feedback> retrieveFeedback(long trainingId) throws TMSException;
    /**
     *
     * @return List<TrainingInformation>
     * @throws TMSException { @link TMSException}
     */
    List<TrainingInformation> getAllPastTrainings() throws TMSException;
    /**
     *
     * @param trainingInfoId { @link long}
     * @return id
     * @throws TMSException { @link TMSException}
     */
    TrainingInformation getTrainingsInfo(long trainingInfoId)
    throws TMSException;
    }