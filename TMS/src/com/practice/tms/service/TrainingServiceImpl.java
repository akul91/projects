package com.practice.tms.service;
import java.util.List;
import java.util.Map;

import com.practice.tms.dao.ITrainingDao;
import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Feedback;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
    /**
     * @author bgundavarapu
     *
     */
    public class TrainingServiceImpl implements ITrainingService {
    /**
     *
     */
    private ITrainingDao trainingDao;
    /**
     * @param trainingDaoObj { @link TrainingDao}
     */
    public final void setTrainingDao(final ITrainingDao trainingDaoObj) {
    this.trainingDao = trainingDaoObj;
    }
    /**
     * This method is used to retrieve all the employees.
     * @param employeeId { @link long}
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
    public final List<Trainings> retrieveAll(final long employeeId)
    throws TMSException {
    try {
    return trainingDao.retrieveAll(employeeId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * This method is used for enrolling for a training and retrieving
     * all the training information.
     * @param trainingId { @link long}
     * @param userId { @link long}
     * @return list
     * @throws TMSException { @link TMSException}
     */
    public final Trainings retireveTrainingInfo(final long trainingId
    , final long userId)
    throws TMSException {
    try {
    return trainingDao.retireveTrainingInfo(trainingId , userId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * This method is used for giving a conformation
     *  message that he is enrolled for a training.
     *  @param trainingId { @link long}
     *  @param userId { @link long}
     *  @return list
     * @throws TMSException { @link TMSException} */
    public final int insertIntoMapping(final long trainingId
    , final long userId)
    throws TMSException {
    try {
    return trainingDao.insertIntoMapping(trainingId, userId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * This method is used to view history of trainings he completed.
     * @param employeeId { @link long}
     * @return List<Enrollment>
     * @throws TMSException { @link TMSException}
     */
    public final List<Trainings> employeeHistory(final long employeeId)
    throws TMSException {
    try {
    return trainingDao.employeeHistory(employeeId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * This method is used to get values from database to jsp page
     * for canceling an enrollment for training.
     * @param employeeId { @link long}
     * @return List<Enrollment>
     * @throws TMSException { @link TMSException}
     */
    public final List<Enrollment> enrolmentCancelation(final long employeeId)
    throws TMSException {
    try {
    return trainingDao.enrolmentCancelation(employeeId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**.
     * This method is used to change the status in the database from
     * jsp page for canceling an enrollment for training
     * @param dummy { @link int}
     * @return list
     * @throws TMSException { @link TMSException}
     */
    public final int enrolmentCancelation1(final long dummy) throws TMSException
    {
    try {
    return trainingDao.enrolmentCancelation1(dummy);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * This method is used employee cancelling a training that he enrolled.
     * @param employeeId.
     * @param employeeId { @link long}
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
    public final List<Trainings> requestTrainings(final long employeeId)
    throws TMSException {
    try {
    return trainingDao.requestTrainings(employeeId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**.
     * @param requestId { @link long}
     * @return list
     * @throws TMSException { @link TMSException}
     */
    public final Trainings requestTrainings1(final long requestId)
    throws TMSException {
    try {
    return trainingDao.requestTrainings1(requestId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
 * This method is used for cancelling a training request.
 * @param requestId { @link long}
 * @return list
 * @throws TMSException { @link TMSException} */
    public final int cancelTraining(final long requestId) throws TMSException {
    try {
    return trainingDao.cancelTraining(requestId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * fetch list of trainings ids.
     * @param employeeId { @link long}
     * @return List<Enrollment>
     * @throws TMSException { @link TMSException} */
    public final List<Enrollment> appliedTrainings(final long employeeId)
    throws TMSException {
    try {
    return trainingDao.appliedTrainingList(employeeId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @param employeeId { @link long}
     * @return List<TrainingsRequested>
     * @throws TMSException { @link TMSException} */
    public final List<Trainings> postedTrainings(final long employeeId)
    throws TMSException {
    try {
    return trainingDao.postedTrainingList(employeeId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * This method gives the current status.
     * @param trainingId { @link long}
     * @return List<Enrollment>
     * @throws TMSException { @link TMSException}
     */
    public final List<Enrollment> appliedStatus(final long trainingId)
    throws TMSException {
    try {
    return trainingDao.appliedStatus(trainingId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @param requestId { @link long}
     * @return List<TrainingsRequested>
     * @throws TMSException { @link TMSException}
     */
    public final List<Trainings> postedStatus(final long requestId)
    throws TMSException {
    try {
    return trainingDao.postedStatus(requestId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @return List<Location>
     * @throws TMSException {@link TMSException}
     */
    public final List<Location> retrieveLocations() throws TMSException {
    try {
    return trainingDao.retrieveLocations();
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @param employeeId { @link long}
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
    public final List<Trainings>
    retrieveTrainingId(final long employeeId)
    throws TMSException {
    try {
    return trainingDao.retrieveTrainingId(employeeId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @param trainingId { @link long}
     * @return List<Enrollment>
     * @throws TMSException { @link TMSException}
     */
    public final List<Enrollment> retrieveFeedback(final long trainingId)
    throws TMSException {
    try {
    return trainingDao.retrieveFeedback(trainingId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @return List<Employee>
     * @throws TMSException { @link TMSException}
     */
    public final List<Employee> retrieveEmployees() throws TMSException {
    try {
    return trainingDao.retrieveEmployees();
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
 * @param employeeId { @link long}
 * @return List<TrainingScheduled>
 * @throws TMSException { @link TMSException}
 */
    public final List<Trainings> retrieveTrainingIdForFeedback(
    final long employeeId)
    throws TMSException {
    try {
    return trainingDao.retrieveTrainingIdForFeedback(employeeId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @param employeeId { @link long}
     * @param trainingId { @link long}
     * @param feedback { @link Feedback}
     * @return list
     * @throws TMSException { @link TMSException}
     */
    public final int submitFeedback(final long employeeId
    , final long trainingId , final Feedback feedback)
    throws TMSException {
    try {
    return trainingDao.submitFeedback(employeeId, trainingId , feedback);
    } catch (TMSException e) {
        throw new TMSException(e.toString());
    }
    }
    /**
     * @return list
     * @throws TMSException {@link TMSException}
     */
    public final Map<Long, Integer> retrieveAllTrainingCount()
    throws TMSException {
    try {
    return trainingDao.retrieveAllTrainingCount();
    } catch (TMSException e) {
        throw new TMSException(e.toString());
    }
    }
    /**
     * method to get employee details.
     * @param userId { @link Employee}
     * @return employee object { @link Employee}
     * @throws TMSException { @link TMSException}
     */
    public final Employee getEmployeeDetails(final long userId)
    throws TMSException {
    try {
    return trainingDao.getEmployee(userId);
    } catch (TMSException e) {
    throw new TMSException(e.toString());
    }
    }
    }