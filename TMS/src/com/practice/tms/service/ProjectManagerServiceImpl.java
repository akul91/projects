package com.practice.tms.service;

import java.util.ArrayList;
import java.util.List;

import com.practice.tms.dao.IProjectManagerDao;
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
    public class ProjectManagerServiceImpl implements IProjectManagerService {
    /**
   * declaring projectManagerDao variable.
   */
    private IProjectManagerDao projectManagerDao;
    /**.
     * getter method for projectManagerDao
     * @return projectManagerDao
     */
    /**
   * setter method for projectManagerDao.
   * @param projManageDao { @link IProjectManagerDao}
   */
    public final void setProjectManagerDao(final IProjectManagerDao
    projManageDao) {
    this.projectManagerDao = projManageDao;
    }
  /**.
   * method to retrieve enrollments requested by..
   * all employees under the project manager
   * @param employeeId { @link Employee}
   * @return list of enrollments requested by..
   * all employees under the project manager
   * @throws TMSException { @link TMSException}
   */
  public final List<Enrollment> retrieveRequestedEnrol(final long employeeId)
         throws TMSException {
      return projectManagerDao.retrieveRequestedEnrol(employeeId);
  }
/**.
   * method to retrieve all  the enrollments
   * @param dummy { @link Enrollments}
   * @return list of enrollments
   * @throws TMSException { @link TMSException}
   */
  public final List<Enrollment> retrieveEnrol(final long dummy)
         throws TMSException {
      return projectManagerDao.retrieveEnrol(dummy);
  }
  /**.
   * method to accept enrollment request by an employee
   * @param dummy { @link Enrollments}
   * @throws TMSException { @link TMSException}
   */
  public final void acceptRequest(final long dummy) throws TMSException {
         projectManagerDao.acceptRequest(dummy);
  }
  /**.
   * method to reject enrollment request by an employee
   * @param dummy { @link Enrollments}
   * @throws TMSException { @link TMSException}
   */
  public final void rejectRequest(final long dummy) throws TMSException {
       projectManagerDao.rejectRequest(dummy);
  }
  /**.
   * method to retrieve trainings to be approved/rejected
   * @param employeeId { @link Employee}
   * @return list of trainings
   * @throws TMSException { @link TMSException}
   */
  public final List<Trainings> requestedTraining(final long employeeId)
        throws TMSException {
      return projectManagerDao.requestedTraining(employeeId);
  }
  /**.
   * method to retrieve details of a particular training
   * @param requestId { @link TrainingsRequested}
   * @return details of a requested training
   * @throws TMSException { @link TMSException}
   */
  public final Trainings specificTraining(final long requestId)
      throws TMSException {
     return projectManagerDao.specificTraining(requestId);
  }
  /**.
   * method to retrieve all the trainings' details conducted by an employee
   * @param trainerId { @link TrainingScheduled}
   * @return list of trainings
   * @throws TMSException { @link TMSException}
   */
  public final List<Trainings> history(final long trainerId)
       throws TMSException {
      return projectManagerDao.history(trainerId);
  }
  /**.
   * method to accept posted training by the project manager
   * @param requestId { @link TrainingsRequested}
   * @return integer
   * @throws TMSException { @link TMSException}
   */
  public final int acceptPosting(final long requestId) throws TMSException {
       return projectManagerDao.acceptPosting(requestId);
  }
  /**.
   * method to reject posted training by the project manager
   * @param requestId { @link TrainingsRequested}
   * @return integer
   * @throws TMSException { @link TMSException}
   */
  public final int rejectPosting(final long requestId) throws TMSException {
      return projectManagerDao.rejectPosting(requestId);
  }
  /**.
   * method to view all the feedbacks to a particular training
   * @param trainingId { @link TrainingScheduled}
   * @return list of feedbacks
   * @throws TMSException { @link TMSException}
   */
  public  final List<Feedback> viewFeedbackServicePM(final long trainingId)
        throws TMSException {
      return projectManagerDao.viewFeedback(trainingId);
  }
  /**.
   * method to retrieve all the training ids posted by employees under
   * logged in project manager
   * @param employeeId { @link Employee}
   * @return list of training ids posted by an employee
   * @throws TMSException { @link TMSException}
   */
  public final List<Trainings> getTrainingId(final long employeeId)
        throws TMSException {
      List<Trainings> tlist = new ArrayList<Trainings>();
      tlist = projectManagerDao.getTrainingId(employeeId);
      return tlist;
  }
  /**.
   * method to retrieve locations to give training
   * @return list of locations
   * @throws TMSException { @link TMSException}
   */
  public final List<Location> retrieveLocations()  throws TMSException {
      return projectManagerDao.retrieveLocations();
  }
  /**.
   * method to retrieve all the employees' details
   * @return all the employee details
   * @throws TMSException { @link TMSException}
   */
  public final List<Employee> retrieveEmployees() throws TMSException {
      return projectManagerDao.retrieveEmployees();
  }
  /**.
   * method to retrieve the details of an employee
   * @param employeeId { @link Employee}
   * @return employee object
   * @throws TMSException { @link TMSException}
   */
  public final Employee retrieveEmployeeDetails(final long employeeId)
        throws TMSException {
     return projectManagerDao.retrieveEmployeeDetails(employeeId);
  }
  /**.
   * method to retrieve the trainings posted by an employee
   * @param employeeId { @link Employee}
   * @return trainings posted by an employee
   * @throws TMSException { @link TMSException}
   */
  public final List<Enrollment> retrieveTrainingsInfo(final long employeeId)
         throws TMSException {
      return projectManagerDao.retrieveTrainingsInfo(employeeId);
  }
  /**.
   * method to retrieve employee names under particular project manager
   * @param employeeId { @link Employee}
   * @return list of employee names
   * @throws TMSException { @link TMSException}
   */
  public final List<Employee> retrieveEmployeeNames(final long employeeId)
        throws TMSException {
      return projectManagerDao.retrieveEmployeeNames(employeeId);
      }
  /**.
   * method to know the details of a particular employee
   * @param trainingId { @link TrainingScheduled}
   * @return list of employees enrolled for a training
   * @throws TMSException { @link TMSException}
   */
  public final List<Enrollment> getEmployeeDetails(final long trainingId)
      throws TMSException {
     return projectManagerDao.getEmployeeDetails(trainingId);
     }
  /**.
   * method to view all the trainings scheduled
   * @return list of trainings scheduled
   * @throws TMSException { @link TMSException}
   */
  public final List<Trainings> viewTrainingScheduled()
        throws TMSException {
     return projectManagerDao.viewTrainingScheduled();
     }
  /**.
   * method to get the training details of training details
   * @param trainingId { @link TrainingScheduled}
   * @return training details
   * @throws TMSException { @link TMSException}
    */
  public final Trainings getTrainingDetails(final long trainingId)
      throws TMSException {
    return projectManagerDao.getTrainingDetails(trainingId);
   }
  /**.
   * method to retrieve trainings posted by an employee which are in progress
   * @param employeeId { @link Employee}
   * @return list of trainings posted by an employee
   * @throws TMSException { @link TMSException}
   */
  public final List<Trainings> requestTrainings(final long employeeId)
       throws TMSException {
     return projectManagerDao.requestTrainings(employeeId);
  }
  /**.
   * employee details
   * method to retrieve all the trainings
   * @param requestId { @link TrainingsRequested}
   * @return training details
   * @throws TMSException { @link TMSException}
   */
  public final Trainings requestTrainings1(final long requestId)
         throws TMSException {
      return projectManagerDao.requestTrainings1(requestId);
      }
  /**.
   * method to cancel training by the project manager
   * @param requestId { @link TrainingsRequested}
   * @return integer
   * @throws TMSException { @link TMSException}
   */
  public final int cancelTraining(final long requestId) throws TMSException {
      return projectManagerDao.cancelTraining(requestId);
  }
}