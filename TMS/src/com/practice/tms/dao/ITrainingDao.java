package com.practice.tms.dao;

import java.util.List;
import java.util.Map;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Feedback;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.Schedule;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
    /**.
     * @author bgundavarapu
     *
     */
    public interface ITrainingDao {
    /**.
     * This method is used to retrieve all the employees
     * @param employeeId { @link long}
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
    List<Trainings> retrieveAll(long employeeId)
    throws TMSException;
    /**
     * This method is used for retriveing the training list.
     * @param trainingId { @link long}
     * @return trainingList
     * @throws TMSException { @link TMSException}
     */
    Schedule retrieveForId(long trainingId) throws TMSException;
    /**.
     * This method is used for enrolling for a training and retrieving
     * all the training information
     * @param trainingId { @link trainingId}
     * @param employeeId {@ link employeeId}
     * @return trainingInformationList
     * @throws TMSException {@link TMSException}
     */
    Trainings retireveTrainingInfo(long trainingId , long employeeId)
    throws TMSException;
    /**
     * This method is used for giving a conformation
     *  message that he is enrolled for a training.
     * @param trainingId { @link long}
     * @param employeeId { @link long}
     * @return trainingList
     * @throws TMSException {@link TMSException}
     */
    int insertIntoMapping(long trainingId , long employeeId)
    throws TMSException;
    /**
     * This method is used to view history of trainings he completed.
     * @param employeeId { @link long}
     * @return List<Enrollment>
     * @throws TMSException {@link TMSException}
     */
    List<Trainings> employeeHistory(long employeeId)
    throws TMSException;
    /**
     * This method is used to get values from database to jsp page
     * for canceling an enrollment for training.
     * @param employeeId { @link long}
     * @return List<Enrollment>
     * @throws TMSException {@link TMSException}
     */
    List<Enrollment> enrolmentCancelation(long employeeId)
    throws TMSException;
    /**
     * This method is used to change the status in the database from
     * jsp page for canceling an enrollment for training.
     * @param dummy { @link int}
     * @return trainingList
     * @throws TMSException {@link TMSException}
     */
    int  enrolmentCancelation1(long dummy) throws TMSException;
    /**
     * This method is used employee cancelling a training that he enrolled.
     * @param employeeId { @link long}
     * @return List<TrainingScheduled>
     * @throws TMSException {@link TMSException}
     */
    List<Trainings> requestTrainings(long employeeId)
    throws TMSException;
    /**
     * This method is used to Load the feedback form.
     * @param requestId { @link long}
     * @return feedback form
     * @throws TMSException {@link TMSException}
     */
    Trainings requestTrainings1(long requestId)
    throws TMSException;
    /**
     * This method is used for cancelling a training request.
     * @param requestId { @link long}
     * @return trainingList
     * @throws TMSException {@link TMSException}
     */
    int cancelTraining(long requestId) throws TMSException;
    /**
     * fetch list of trainings ids.
     * @param employeeId { @link long}
     * @return List<Enrollment>
     * @throws TMSException {@link TMSException}
     */
    List<Enrollment> appliedTrainingList(long employeeId)
    throws TMSException;
    /**
     * fetch request id.
     * @param employeeId { @link long}
     * @return List<TrainingsRequested>
     * @throws TMSException {@link TMSException}
     */
    List<Trainings> postedTrainingList(long employeeId)
    throws TMSException;
    /**
     * This method gives the current status.
     * @param trainingId { @link trainingId}
     * @return List<Enrollment>
     * @throws TMSException {@link TMSException}
     */
    List<Enrollment> appliedStatus(long trainingId)
    throws TMSException;
    /**
     * This method is used to see the status of posting a training.
     * @param requestId { @link long}
     * @return List<TrainingsRequested>
     * @throws TMSException {@link TMSException}
     */
    List<Trainings> postedStatus(long requestId)
    throws TMSException;
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
    /**
     * This method is used for feedback for fa particular training.
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
    List<Enrollment> retrieveFeedback(long trainingId)
    throws TMSException;
    /**.
     * @param employeeId { @link long}
     * @return List<TrainingScheduled>
     * @throws TMSException {@link TMSException}
     */
    List<Trainings>
    retrieveTrainingIdForFeedback(long employeeId) throws TMSException;
    /**.
     * @param employeeId { @link long}
     * @param trainingId { @link long}
     * @param feedback { @link Feedback}
     * @return feedback form
     * @throws TMSException {@link TMSException}
     */
    int submitFeedback(long employeeId , long trainingId , Feedback feedback)
    throws TMSException;
    /**.
     * @return Map<Long, Integer>
     * @throws TMSException {@link TMSException}
     */
    Map<Long, Integer> retrieveAllTrainingCount() throws TMSException;
    /**
     * method to get employee details.
     * @param userId { @link Employee}
     * @return employee object
     * @throws TMSException { @link TMSException}
     */
    Employee getEmployee(long userId) throws TMSException;
    }