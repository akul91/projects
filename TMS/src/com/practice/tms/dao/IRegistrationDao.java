package com.practice.tms.dao;
import java.util.ArrayList;
import java.util.List;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Feedback;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.TrainingInformation;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
  /**.
  *
  * @author test
  *
  */
  public interface IRegistrationDao {
  /**.
  * method to register new employee details
  * @param employee { @link Employee}
  * @return employeeId
  * @throws TMSException { @link TMSException}
  */
  long register(Employee employee) throws TMSException;
  /**.
  * method to retrieve all the project manager ids
  * @return list of project manager ids
  * @throws TMSException { @link TMSException}
  */
  ArrayList<Long> projectManagerId() throws TMSException;
  /**.
  * method to get employee details
  * @param userId { @link Employee}
  * @return employee object
  * @throws TMSException { @link TMSException}
  */
  Employee getEmployee(long userId) throws TMSException;
  /**.
  * method to update the password
  * @param employee { @link Employee}
  * @throws TMSException { @link TMSException}
  */
  void updatePassword(Employee employee) throws TMSException;
  /**
   * This method is used employee cancelling a training that he enrolled.
   * @param employeeId { @link long}
   * @return List<TrainingScheduled>
   * @throws TMSException {@link TMSException}
   */
  List<Trainings> requestTrainings(long employeeId)
  throws TMSException;
  /**
   * Method to check the password.
   * @param userId {@link long}
   * @return String.
   */
  String checkPassword(long userId) throws TMSException;
  /**
   * This method is for retrive the available locations.
   * @return List<Location>
   * @throws TMSException {@link TMSException}
   */
  List<Location> retrieveLocations() throws TMSException;
  /**
   * This method is for retrieving employees from employee table.
   * @return List<Employee>
   * @throws TMSException {@link TMSException}
   */
  List<Employee> retrieveEmployees() throws TMSException;
/**.
      * This method is used to cancel the training of employees.
      * @param requestId { @link long}
      * @return Integer< @ link int>
      * @throws TMSException { @link TMSException}
      */
     int cancelTraining(long requestId) throws TMSException;
     /**
      * This method is used for feedback for a particular training.
      * in pending status
      * @param employeeId { @link long}
      * @return List<TrainingScheduled>
      * @throws TMSException {@link TMSException}
      */
     List<Trainings> retrieveTrainingId(long employeeId)
     throws TMSException;
     /**.
      * This method is used for feedback for fa particular
      *  training in completed status
      * @param trainingId { @link long}
      * @return List<Enrollment>
      * @throws TMSException {@link TMSException}
      */
     List<Feedback> retrieveFeedback(long trainingId)
     throws TMSException;
/**
 * Method to retrieve all past training.
 * @return list {@link TrainingInformation}
 * @throws TMSException {@link TMSException}
 */
     List<TrainingInformation> retrieveAllPastTrainings()
throws TMSException;
     /**
      * Method to get the training info.
      * @return {@link TrainingInformation}
      * @param trainingInfoId { @link long}
      * @throws TMSException {@link TMSException}
      */
     TrainingInformation getTrainingsInfo(long trainingInfoId)
throws TMSException;
}