package com.practice.tms.dao;

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
    public interface ITrainingAdminDao {
    /**
     * The training administrator can view the history of training by
     *.checking the status equal to completed.
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
     List<Trainings> viewHistory() throws TMSException;
     /**
      * The training administrator can view the history of all the
      *  employees who has enrolled in that particular training.
      *  @param trainingId { @link long }
      *  @return List<Enrollment>
      *.@throws TMSException { @link TMSException}*/
     List<Enrollment> viewHistoryDetails(long trainingId)
       throws TMSException;
     /**
      * The training administrator can view the history of current
      * training by checking the status not equal to completed.
      * @return List<TrainingScheduled>
      * @throws TMSException { @link TMSException}}*/
     List<Trainings> viewCurrentTrainings()
       throws TMSException;
     /**
      * The training administrator can view the Locations from location table.
      * @return List<Location>
      * @throws TMSException { @link TMSException}
      */
     List<Location> retrieveLocations() throws TMSException;
     /**
      * The training administrator can retrieve all the details of
      * employees from the employee table.
      * @return List<Employee>
      * @throws TMSException { @link TMSException}
      */
     List<Employee> retrieveEmployees() throws TMSException;
/**
 * method to delete user.
 * @param employeeId {@link long}
 * @return {@link int}
 * @throws TMSException {@link TMSException}
 */
     int deleteUser(long employeeId) throws TMSException;
/**
 * method to get all the users.
 * @return List {@link Employee}
 * @throws TMSException {@link TMSException}
 */
     List<Employee> getAllUser() throws TMSException;
    /**.
     * method to get employee details
     * @param userId { @link Employee}
     * @return employee object
     * @throws TMSException { @link TMSException}
     */
    Employee getEmployee(long userId) throws TMSException;
    }