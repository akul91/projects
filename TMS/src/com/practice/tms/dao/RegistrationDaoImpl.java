package com.practice.tms.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Feedback;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.Schedule;
import com.practice.tms.domain.TrainingInformation;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
/**
*
* @author test
*
*/
  public class RegistrationDaoImpl implements IRegistrationDao {
  /**.
   * declaring a variable of hibernate template type
   */
  private HibernateTemplate hibernateTemplate;
  /**.
   * setter injection for sessionFactory
   * @param sessionFactory { @link SessionFactory}
   */
  public final void setSessionFactory(final SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
  }
  /**.
   * method to update the password
   * @param employee { @link Employee}
   * @throws TMSException { @link TMSException}
   */
  public final void updatePassword(final Employee employee)
            throws TMSException {
   try {
      hibernateTemplate.saveOrUpdate(employee);
   } catch (Exception e) {
       throw new TMSException(e.toString());
   }
  }
  /**.
   * method to register new employee details
   * @param employee { @link Employee}
   * @return employeeId
   * @throws TMSException { @link TMSException}
   */
  @SuppressWarnings("unchecked")
  public final long register(final Employee employee) throws TMSException {
    try {
    List<Long> idList = hibernateTemplate.find("select count(*)"
    + " from Employee where employeeId="
    + employee.getEmployeeId());
    Iterator<Long> iterator = idList.iterator();
    long count = iterator.next();
    if (count > 0) {
    System.out.println("returning 0");
    return 0;
    } else {
    hibernateTemplate.save(employee);
    System.out.println("returning 1");
    return 1;
    }
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
  }
    /**.
   * method to retrieve all the project manager ids
   * @return list of project manager ids
   * @throws TMSException { @link TMSException}
   */
  @SuppressWarnings("unchecked")
  public final ArrayList<Long> projectManagerId() throws TMSException {
      final ArrayList<Long> pmid = new ArrayList<Long>();
      try {
          final List<Long> pmlist = hibernateTemplate.find(
          " select e.employeeId from Employee e where e.employeeCategory='p'");
         final Iterator<Long> iterator = pmlist.iterator();
         while (iterator.hasNext()) {
             pmid.add((Long) iterator.next());
         }
      } catch (Exception e) {
          throw new TMSException(e.toString());
      }
      return pmid;
  }
  /**.
   * method to get employee details
   * @param userId { @link long}
   * @return employee object {@link Employee}
   * @throws TMSException { @link TMSException}
   */
  @SuppressWarnings("unchecked")
public final Employee getEmployee(final long userId) throws TMSException {
      List<Employee> list = new ArrayList<Employee>();
      try {
      list = hibernateTemplate.find(
          "from Employee where employeeId=" + userId);
      final Iterator<Employee> iterator = list.iterator();
      while (iterator.hasNext()) {
          final Employee employee = iterator.next();
          return employee;
      }
    } catch (Exception e) {
      throw new TMSException(e.toString());
    }
      return null;
      }
/**
 * method to check password.
 * @param userId { @link long}
 * @throws TMSException { @link TMSException}
 * @return String
 */
     public final String checkPassword(final long userId) throws TMSException {
try {
    List<String> list = hibernateTemplate.find("select e.password"
    + " from Employee e where e.employeeId = "
    + userId);
      Iterator<String> iterator = list.iterator();
      return iterator.next();
} catch (Exception e) {
    throw new TMSException(e.toString());
  }

  }
    /**
     * @see com.practice.tms.dao.TrainingDao#retrieveEmployees() This method is
     * for retrieving employees from employee table.
     * @return List<Employee>
     * @throws TMSException
     *             {@link TMSException}
     */
    @SuppressWarnings("unchecked")
    @Override
public final List<Employee> retrieveEmployees() throws TMSException {
    try {
    return hibernateTemplate.find("from Employee");
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
}
    /**
     * @see com.practice.tms.dao.TrainingDao#retrieveLocations() This method is
     * for retrieve the available locations.
     * @return List<Location>
     * @throws TMSException
     *             {@link TMSException}
     */
    @SuppressWarnings("unchecked")
    @Override
    public final List<Location> retrieveLocations() throws TMSException {
    try {
    return hibernateTemplate.find("from Location");
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
}
    /**
     * @see com.practice.tms.dao.TrainingDao#requestTrainings(long) This method
     *      is used employee cancelling a training that he enrolled.
     * @param employeeId
     *            { @link long}
     * @return List<Trainings>
     * @throws TMSException
     *             {@link TMSException}
     */
    @SuppressWarnings("unchecked")
    public final List<Trainings> requestTrainings(final long employeeId)
    throws TMSException {
    List<Trainings> scheduleList = new ArrayList<Trainings>();
    try {
    scheduleList = hibernateTemplate.find(
    "from Trainings  where trainerId=? "
    + "and status not in('Canceled','Completed','Rejected')",
    new Object[] {employeeId });
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return scheduleList;
    }
    /**
     * @see com.practice.tms.dao.TrainingDao#cancelTraining(long) This method is
     *      used for canceling a training request.
     * @param requestId
     *            {@link long}
     * @return List<Trainings>
     * @throws TMSException
     *             {@link TMSException}
     */
    @SuppressWarnings("unchecked")
    public final int cancelTraining(final long requestId) throws TMSException {
    try {
    List<Trainings> scheduleList = hibernateTemplate.find(
    "from Trainings where trainingID=?",
    new Object[] {requestId});
    Iterator<Trainings> trainingScheduledIterator = scheduleList
    .iterator();
    trainingScheduledIterator.hasNext();
    Trainings trainingsRequested = trainingScheduledIterator.next();
    trainingsRequested.setStatus("Canceled");
    Schedule schedule = trainingsRequested.getSchedule();
    trainingsRequested.setSchedule(null);
    hibernateTemplate.update(trainingsRequested);
    hibernateTemplate.delete(schedule);
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return 1;
}
    @Override
    @SuppressWarnings("unchecked")
    public final List<Trainings> retrieveTrainingId(final long employeeId)
    throws TMSException {
    List<Trainings> scheduleList = new ArrayList<Trainings>();
    try {
    scheduleList = hibernateTemplate
    .find(" from Trainings TS where TS.trainerId=" + employeeId
    + " and status in('Completed')");
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return scheduleList;
    }

    /**
     * @see com.practice.tms.dao.TrainingDao#retrieveFeedback(long) This method
     *      is used for feedback for fa particular training in completed status.
     * @param trainingId
     *            { @link long}
     * @throws TMSException
     *             {@link TMSException}
     * @return List<Enrollment>
     */
    @SuppressWarnings("unchecked")
    public final List<Feedback> retrieveFeedback(final long trainingId)
    throws TMSException {
    List<Feedback> feedbackList = new ArrayList<Feedback>();
    try {
    List<Enrollment> enrollmentList = hibernateTemplate
    .find("from Enrollment E where E.trainingId = "
    + trainingId + " and E.statusId = 6");
    final Iterator<Enrollment> iterator = enrollmentList.iterator();
    while (iterator.hasNext()) {
    final Enrollment enrollment = iterator.next();
    feedbackList.addAll(hibernateTemplate
    .find("from Feedback E where E.enrollmentId="
    + enrollment.getEnrollmentId()));
    }
    return feedbackList;
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
}

    @SuppressWarnings("unchecked")
    @Override
    public final List<TrainingInformation> retrieveAllPastTrainings()
    throws TMSException {
    return hibernateTemplate.find("from TrainingInformation");
}

    @SuppressWarnings("unchecked")
    @Override
    public final TrainingInformation getTrainingsInfo(final long trainingInfoId)
    throws TMSException {
    List<TrainingInformation> trainingInfoList = hibernateTemplate
    .find("from TrainingInformation where trainingInformationId="
    + trainingInfoId);
    TrainingInformation trainingInfo = trainingInfoList.get(0);
    return trainingInfo;
}
}