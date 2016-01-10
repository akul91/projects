package com.practice.tms.service;

import java.util.List;
import java.util.Map;

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
    public interface ITrainingService {
    /**.
     * This method is used to retrieve all the employees
     * @param employeeId { @link long}
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
     List<Trainings> retrieveAll(long employeeId) throws TMSException;
     /**.
      * This method is used to retrieve the training information
      * @param trainingId { @link long}
      * @param userId { @link long}
      * @return TrainingScheduled<TrainingScheduled>
      * @throws TMSException { @link TMSException}
      */
     Trainings retireveTrainingInfo(long trainingId,
    long userId) throws TMSException;
     /**.
      * This method is used to insert the data into database.
      * @param trainingId { @link trainingId}
      * @param userId { @link long}
      * @return Integer< @ link int>
      * @throws TMSException { @link TMSException}
      */
     int insertIntoMapping(long trainingId,
    long userId) throws TMSException;
     /**
      * This method is used to get the employee history.
      * @param employeeId { @link long}
      * @return List<Enrollment>
      * @throws TMSException { @link TMSException}
      */
     List<Trainings> employeeHistory(long employeeId) throws TMSException;
     /**.
      * This method is used to get the list of employees.
      * @param employeeId { @link long}
      * @return List<Enrollment>
      * @throws TMSException { @link TMSException}
      */
     List<Enrollment> enrolmentCancelation(long employeeId) throws TMSException;
     /**.
      * This method is used to cancel the enrollment of employees.
      * @param dummy { @link int}
      * @return Integer< @ link int>
      * @throws TMSException { @link TMSException}
      */
     int enrolmentCancelation1(long dummy) throws TMSException;
     /**.
      * This method is used to retrieve all the training requested.
      * @param employeeId { @link long}
      * @return List<TrainingScheduled>
      * @throws TMSException { @link TMSException}
      */
     List<Trainings> requestTrainings(long employeeId)
    throws TMSException;
     /**.
      * This method is used to retrieve all the training requested.
      * @param requestId { @link long}
      * @return TrainingScheduled{ @link TrainingScheduled}
      * @throws TMSException { @link TMSException}
      */
     Trainings requestTrainings1(long requestId) throws TMSException;
     /**.
      * This method is used to cancel the training of employees.
      * @param requestId { @link long}
      * @return Integer< @ link int>
      * @throws TMSException { @link TMSException}
      */
     int cancelTraining(long requestId) throws TMSException;
     /**.
      * This method is used to retrieve applied training of employees
      * @param employeeId { @link long}
      * @return List<Enrollment>
      * @throws TMSException { @link TMSException}
      */
     List<Enrollment> appliedTrainings(long employeeId) throws TMSException;
     /**.
      * This method is used to retrieve posted training of employees
      * @param employeeId { @link long}
      * @return List<TrainingsRequested>
      * @throws TMSException { @link TMSException}
      */
     List<Trainings> postedTrainings(long employeeId)
    throws TMSException;
     /**.
      * This method is used to retrieve the applies status of employees
      * @param trainingId { @link long}
      * @return List<Enrollment>
      * @throws TMSException { @link TMSException}
      */
     List<Enrollment> appliedStatus(long trainingId) throws TMSException;
     /**.
      * This method is used to retrieve posted status employees
      * @param requestId { @link long}
      * @return List<TrainingsRequested>
      * @throws TMSException { @link TMSException}
      */
     List<Trainings> postedStatus(long requestId) throws TMSException;
     /**.
      * This method is used to retrieve location of employees
      * @return List<Location>
      * @throws TMSException { @link TMSException}
      */
     List<Location> retrieveLocations() throws TMSException;
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
     List<Enrollment> retrieveFeedback(long trainingId) throws TMSException;
     /**.
      * This method is used to retrieve all the employees
      * @return List<Employee>
      * @throws TMSException { @link TMSException}
      */
     List<Employee> retrieveEmployees() throws TMSException;
     /**.
      * This method is used to retrieve training id for feedback employees
      * @param employeeId { @link long}
      * @return List<TrainingScheduled>
      * @throws TMSException { @link TMSException}
      */
     List<Trainings> retrieveTrainingIdForFeedback(long employeeId)
    throws TMSException;
     /**.
      * This method is used submit feedback.
      * @param employeeId { @link long}
      * @param trainingId { @link long}
      * @param feedback { @link Feedback}
      * @return Integer < @link int>
      * @throws TMSException { @link TMSException}
      */
     int submitFeedback(long employeeId, long trainingId,
    Feedback feedback)
    throws TMSException;
     /**.
      * This method is used to retrieve the count of training.
      * @return Map<Long, Integers>
      * @throws TMSException { @link TMSException}
      */
     Map<Long, Integer> retrieveAllTrainingCount() throws TMSException;
     /**
     * method to get employee details.
     * @param userId { @link Employee}
     * @return employee object { @link Employee}
     * @throws TMSException { @link TMSException}
     */
      Employee getEmployeeDetails(long userId) throws TMSException;
}