package com.practice.tms.service;
import java.util.List;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Feedback;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
   /**
    *
    * @author bgundavarapu
    *
    */
    public interface IProjectManagerService {
    /**
     * method to retrieve enrollments requested.
     * by all employees under the project manager
     * @param employeeId { @link TrainingsRequested}
     * @return list of enrollments requested by
     * all employees under the project manager
     * @throws TMSException { @link TMSException}
     */
    List<Enrollment> retrieveRequestedEnrol(long employeeId)
       throws TMSException;
    /**
     * method to retrieve all  the enrollments.
     * @param dummy { @link Enrollment}
     * @return { @link Enrollment}
     * @throws TMSException { @link TMSException}
     */
    List<Enrollment> retrieveEnrol(long dummy) throws TMSException;
    /**
     * method to accept enrollment request by an employee.
     * @param dummy { @link Enrollment}
     * @throws TMSException { @link TMSException}
     */
    void acceptRequest(long dummy) throws TMSException;
    /**
     * method to reject enrollment request by an employee.
     * @param dummy { @link Enrollment}
     * @throws TMSException { @link TMSException}
     */
    void rejectRequest(long dummy) throws TMSException;
    /**
     * method to retrieve trainings to be approved/rejected.
   * @param employeeId { @link TrainingsRequested}
   * @return list of trainings
   * @throws TMSException { @link TMSException}
   */
    List<Trainings> requestedTraining(long employeeId)
        throws TMSException;
    /**
     * method to retrieve details of a particular training.
     * @param requestId { @link TrainingsRequested}
     * @return details of a requested training
     * @throws TMSException { @link TMSException}
     */
    Trainings specificTraining(long requestId) throws TMSException;
    /**
     * method to retrieve all the trainings' details conducted by an employee.
     * @param trainerId { @link TrainingScheduled}
     * @return list of trainings
     * @throws TMSException { @link TMSException}
     */
    List<Trainings> history(long trainerId) throws TMSException;
    /**
     * method to accept posted training by the project manager
     * @param requestId { @link TrainingsRequested}
     * @return integer
     * @throws TMSException { @link TMSException}
     */
    int acceptPosting(long requestId) throws TMSException;
    /**
     * method to reject posted training by the project manager
     * @param requestId { @link TrainingsRequested}
     * @return integer
     * @throws TMSException { @link TMSException}
     */
    int rejectPosting(long requestId) throws TMSException;
    /**
     * method to view all the feedbacks to a particular training
     * @param trainingId { @link TrainingScheduled}
     * @return list of feedbacks
     * @throws TMSException { @link TMSException}
     */
    List<Feedback> viewFeedbackServicePM(long trainingId) throws TMSException;
    /**
     * method to retrieve all the training ids posted
     * by employees under logged in project manager
     * @param employeeId { @link Employee}
     * @return list of training ids posted by an employee
     * @throws TMSException { @link TMSException}
     */
    List<Trainings> getTrainingId(long employeeId) throws TMSException;
    /**. { @link Employee}
     * method to retrieve locations to give training
     * @return list of locations
     * @throws TMSException { @link TMSException}
     */
    List<Location> retrieveLocations() throws TMSException;
    /**
     * method to retrieve all the employees' details
     * @return all the employee details
     * @throws TMSException { @link TMSException}
     */
    List<Employee> retrieveEmployees() throws TMSException;
    /**
     * method to retrieve trainings posted by an employee which are in progress
     * @param employeeId { @link Employee}
     * @return list of trainings posted by an employee
     * @throws TMSException { @link TMSException}
     */
    List<Trainings> requestTrainings(long employeeId) throws TMSException;
    /**
     * method to retrieve all the trainings
     * @param requestId { @link TrainingsRequested}
     * @return training details
     * @throws TMSException { @link TMSException}
     */
    Trainings requestTrainings1(long requestId) throws TMSException;
    /**
     * method to cancel training by the project manager
     * @param requestId { @link TrainingsRequested}
     * @return integer
     * @throws TMSException { @link TMSException}
     */
    int cancelTraining(long requestId) throws TMSException;
    /**
     * method to retrieve the details of an employee
     * @param employeeId { @link Employee}
     * @return employee object
     * @throws TMSException { @link TMSException}
     */
    Employee retrieveEmployeeDetails(long employeeId) throws TMSException;
    /**
     * method to retrieve the trainings posted by an employee
     * @param employeeId { @link Employee}
     * @return trainings posted by an employee
     * @throws TMSException { @link TMSException}
     */
    List<Enrollment> retrieveTrainingsInfo(long employeeId) throws TMSException;
    /**
     * method to retrieve employee names under particular project manager.
     * @param employeeId { @link Employee}
     * @return list of employee names
     * @throws TMSException { @link TMSException}
     */
    List<Employee> retrieveEmployeeNames(long employeeId) throws TMSException;
    /**
     * method to know the details of a particular employee.
     * @param trainingId { @link TrainingScheduled}
     * @return list of employees enrolled for a training
     * @throws TMSException { @link TMSException}
     */
    List<Enrollment> getEmployeeDetails(long trainingId) throws TMSException;
    /**
     * method to view all the trainings scheduled.
     * @return list of trainings scheduled
     * @throws TMSException { @link TMSException}
     */
    List<Trainings> viewTrainingScheduled() throws TMSException;
    /**
     * method to get the training details of training details
     * @param trainingId { @link TrainingScheduled}
     * @return training details
     * @throws TMSException { @link TMSException}
     */
    Trainings getTrainingDetails(long trainingId) throws TMSException;
    }