package com.practice.tms.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Feedback;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.Schedule;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;

/**
 *
 * @author bgundavarapu
 *
 */
public class TrainingDaoImpl implements ITrainingDao {
    /**
     * template provided by hibernate.
     */
    private HibernateTemplate hibernateTemplate;

    /**
    * This is a setter injection for SessionFactory.
    * @param sessionFactory
    *            {@link SessionFactory}
    */
    public final void setSessionFactory(final SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

     /**
      * (non-Javadoc) This is method is used to retrieve all the list.
      *
      * @see com.practice.tms.dao.TrainingDao#retrieveAll(long)
      * @param employeeId
      *            { @link long}
      * @return List<Trainings>
      * @throws TMSException
      *             {@link TMSException}
      */
      @SuppressWarnings("unchecked")
    public final List<Trainings> retrieveAll(final long employeeId)
    throws TMSException {
    List<Trainings> scheduleList = new
    ArrayList<Trainings>();
    final List<Trainings> finalList = new ArrayList<Trainings>();

    try {
    scheduleList = hibernateTemplate
    .find(" from Trainings where trainingID not "
        + "in(select trainingId from Enrollment "
        + "where employeeId=? and statusId !=4 ) and trainerId!=? and "
        + "status like 'InProgress'",
        new Object[] {employeeId, employeeId });

    final List<String> tierList = hibernateTemplate
    .find("select employeeTier from Employee where employeeId="
    + employeeId);
    final String tier = tierList.iterator().next();

    final Iterator<Trainings> scheduleiterator = scheduleList
    .iterator();
    while (scheduleiterator.hasNext()) {

    final Trainings trainingScheduled = scheduleiterator.next();
    if (trainingScheduled.getTrainingInformation().getTier().contains(tier)) {
    finalList.add(trainingScheduled);
    }
    }
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return finalList;
    }

      /**
       * This method is used to retrieve all the training information of an
       * employee.
       **
       * @see com.practice.tms.dao.TrainingDao#retireveTrainingInfo(long)
       * @param trainingId
       *            { @link long}
       * @param employeeId
       *            { @link long}
       * @return List<Trainings>
       * @throws TMSException
       *             {@link TMSException}
       */
      @SuppressWarnings("unchecked")
      public final Trainings retireveTrainingInfo(final long trainingId,
      final long employeeId) throws TMSException {
    List<Trainings> scheduleList = new ArrayList<Trainings>();
    Trainings trainingScheduled = null;
    try {
    scheduleList = hibernateTemplate.find(
    "from Trainings where trainingId=?",
    new Object[] {trainingId });
    final Iterator<Trainings> trainSchedIt = scheduleList
    .iterator();
    trainSchedIt.hasNext();
    trainingScheduled = trainSchedIt.next();
    final Schedule schedulesTwo = trainingScheduled.getSchedule();
    int[] arr = new int[3];
    int count = 0;
    Date date = new Date();
    date = schedulesTwo.getDate();
    arr[count++] = schedulesTwo.getTimings();
    final List<Trainings> scheduleListOne = hibernateTemplate.find(
    "from Trainings where trainingID in"
    + "(select trainingId from "
    + "Enrollment where employeeId=?)",
    new Object[] {employeeId });
    final Iterator<Trainings> trainSchedItOne = scheduleListOne
    .iterator();
    while (trainSchedItOne.hasNext()) {
    final Trainings trainSchedTwo = trainSchedItOne
    .next();
    final Schedule schedules = trainSchedTwo.getSchedule();
    if (schedulesTwo.getDate().equals(date)) {
    return null;
    }
    }
    /* ===================================== */
    final List<Trainings> scheduleListTwo = hibernateTemplate.find(
    "from Trainings where trainingId=?",
    new Object[] {trainingId });
    final Iterator<Trainings> trainSchedItTwo = scheduleListTwo
    .iterator();
    trainSchedItTwo.hasNext();
    final Trainings trainingScheduledThree = trainSchedItTwo.next();
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return trainingScheduled;
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#insertIntoMapping
       * (long, Feedback,long)
       * This method is used for giving a confirmation message that he
       *      is enrolled for a training.
       * @param trainingId
       *            { @link long}
       * @param employeeId
       *            { @link long}
       * @throws TMSException
       *             {@link TMSException}
       * @return List<Trainings>
       */
      @SuppressWarnings("unchecked")
      public final int insertIntoMapping(final long trainingId,
    final long employeeId) throws TMSException {
    try {
    final Enrollment enrollment = new Enrollment();
    enrollment.setStatusId(1);
    enrollment.setEmployeeId(employeeId);
    enrollment.setTrainingId(trainingId);
    final List<Trainings> scheduleList = hibernateTemplate.find(
    "from Trainings where trainingId=?",
    new Object[] {trainingId });
    final Iterator<Trainings> trainingScheduledIterator = scheduleList
    .iterator();
    Trainings trainingScheduled = null;
    if (trainingScheduledIterator.hasNext()) {
    trainingScheduled = trainingScheduledIterator.next();
    }
    final List<Employee> employeeList = hibernateTemplate.find(
    "from Employee where employeeId=?",
    new Object[] {employeeId });
    final Iterator<Employee> employeeIterator = employeeList.iterator();
    Employee employee = null;
    if (employeeIterator.hasNext()) {
    employee = employeeIterator.next();
    }
    enrollment.setEmployee(employee);
    enrollment.setTrainings(trainingScheduled);
    hibernateTemplate.saveOrUpdate(enrollment);
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return 1;
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#employeeHistory(long)
       * This method is
       *      used to view history of trainings he completed.
       * @param employeeId
       *            { @link long}
       * @throws TMSException
       *             {@link TMSException}
       * @return List<Enrollment>
       */
      @SuppressWarnings("unchecked")
      public final List<Trainings> employeeHistory(final long employeeId)
    throws TMSException {
     final List<Trainings> traininglist = new ArrayList<Trainings>();
     try {
    traininglist.addAll(hibernateTemplate
    .find("from Trainings where trainingId in (Select trainingId"
    + " from Enrollment "
    + "where employeeId = " + employeeId + ") and status"
    + " in('Completed','Canceled','Rejected')"));

    traininglist.addAll(hibernateTemplate
    .find("from Trainings where trainerId=" + employeeId
        + " and status in('Completed','Canceled','Rejected')"));
     } catch (Exception e) {
    throw new TMSException(e.toString());
}
return traininglist;
}

    /**
     * .
     *
     * @see com.practice.tms.dao.TrainingDao#enrolmentCancelation(long) This
     *      method is used to get values from
     *      database to jsp page for canceling
     *      an enrollment for training
     * @param employeeId
     *            { @link long}
     * @return List<Enrollment>
     * @throws TMSException
     *             {@link TMSException}
     */
      @Override
      @SuppressWarnings("unchecked")
      public final List<Enrollment> enrolmentCancelation(final long employeeId)
    throws TMSException {
    List<Enrollment> employeelist = new ArrayList<Enrollment>();
    try {
    employeelist = hibernateTemplate.find(
    "from Enrollment where employeeId=? and statusId in(1,2)",
    new Object[] {employeeId });
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return employeelist;
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#enrolmentCancelation1(int) This
       *      method is used to change the status in the database from jsp page
       *      for canceling an enrollment for training.
       * @param dummy
       *            { @link int}
       * @return List<Enrollment>
       * @throws TMSException
       *             {@link TMSException}
       */
      @SuppressWarnings("unchecked")
      public final int enrolmentCancelation1(final long dummy)
    throws TMSException {
    try {
    final List<Enrollment> trainingList = hibernateTemplate.find(
    "from Enrollment where enrollmentId=?", new Object[] {dummy });
    final Iterator<Enrollment> enrollmentIterator = trainingList.iterator();
    enrollmentIterator.hasNext();
    final Enrollment enrollment = enrollmentIterator.next();
    enrollment.setStatusId(4);
    hibernateTemplate.update(enrollment);
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return 1;
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#retrieveForId(long)
       * This method is
       *      used for retrieving the training list.
       * @param trainingId
       *            { @link long}
       * @throws TMSException
       *             {@link TMSException}
       * @return list
       */
      @SuppressWarnings("unchecked")
      public final Schedule retrieveForId(final long trainingId)
    throws TMSException {
    Schedule scheduleTwo;
    try {
    final List<Trainings> scheduleList = hibernateTemplate.find(
    "from Trainings where trainingId=?",
    new Object[] {trainingId });
    final Iterator<Trainings> trainingScheduledIterator = scheduleList
    .iterator();
    trainingScheduledIterator.hasNext();
    final Trainings trainingScheduled = trainingScheduledIterator.next();
    scheduleTwo = trainingScheduled.getSchedule();
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return scheduleTwo;
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
       * @see com.practice.tms.dao.TrainingDao#requestTrainings1(long)
       * This method
       *      is used to Load the feedback form.
       * @param requestId
       *            { @link long}
       * @return List<Trainings>
       * @throws TMSException
       *             {@link TMSException}
       */
      @SuppressWarnings("unchecked")
      public final Trainings requestTrainings1(final long requestId)
    throws TMSException {
    Trainings trainingsRequested;
    try {
    final List<Trainings> scheduleList = hibernateTemplate.find(
    "from Trainings where trainingId=?",
    new Object[] {requestId });
    final Iterator<Trainings> trainingScheduledIterator = scheduleList
    .iterator();
    trainingScheduledIterator.hasNext();
    trainingsRequested = trainingScheduledIterator.next();
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return trainingsRequested;
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#cancelTraining(long)
       * This method is used for canceling a training request.
       * @param requestId
       *            {@link long}
       * @return List<Trainings>
       * @throws TMSException
       *             {@link TMSException}
       */
      @SuppressWarnings("unchecked")
      public final int cancelTraining(final long requestId)
    throws TMSException {
    try {
    final List<Trainings> scheduleList = hibernateTemplate.find(
    "from Trainings where trainingID=?",
    new Object[] {requestId });
    final Iterator<Trainings> trainingScheduledIterator = scheduleList
    .iterator();
    trainingScheduledIterator.hasNext();
    final Trainings trainingsRequested = trainingScheduledIterator.next();
    trainingsRequested.setStatus("Cancelled");
    final Schedule schedule = trainingsRequested.getSchedule();
    hibernateTemplate.delete(schedule);
    hibernateTemplate.update(trainingsRequested);
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return 1;
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#appliedTrainingList(long) This
       *      method is used to give the list of all the applied trainings.s
       * @param employeeId
       *            { @link long}
       * @throws TMSException
       *             {@link TMSException}
       * @return List<Enrollment>
       */
      @SuppressWarnings("unchecked")
      public final List<Enrollment> appliedTrainingList(final long employeeId)
    throws TMSException {
    try {
    return hibernateTemplate.find("from Enrollment e"
    + " where e.employeeId=" + employeeId);
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#postedTrainingList(long)
       * This method is used for getting the training list that he posted.
       * @param employeeId
       *            { @link long}
       * @throws TMSException
       *             {@link TMSException}
       * @return List<TrainingsRequested>
       */
      @SuppressWarnings("unchecked")
      @Override
      public final List<Trainings> postedTrainingList(final long employeeId)
    throws TMSException {
    try {
    return hibernateTemplate.find("from Trainings t"
    + " where t.trainerId=? "
    + "and status not in('Cancelled','Completed')",
    new Object[] {employeeId });
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#appliedStatus(long) This method
       *      gives the current status
       * @param trainingId
       *            { @link long}
       * @return List<Enrollment>
       * @throws TMSException
       *             {@link TMSException}
       */
      @SuppressWarnings("unchecked")
      public final List<Enrollment> appliedStatus(final long trainingId)
    throws TMSException {
    try {
    return hibernateTemplate.find("from Enrollment e "
    + "where e.trainingId=" + trainingId);
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#postedStatus(long)
       * This method is used to see the status of posting a training
       * @param requestId
       *            { @link long}
       * @return List<TrainingsRequested>
       * @throws TMSException
       *             {@link TMSException}
       */
      @SuppressWarnings("unchecked")
      @Override
      public final List<Trainings> postedStatus(final long requestId)
      throws TMSException {
    try {
    return hibernateTemplate.find("from Trainings t "
    + "where t.trainingId=" + requestId);
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#retrieveLocations()
       * This method is for retrieve the available locations.
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
       * @see com.practice.tms.dao.TrainingDao#retrieveTrainingId(long)
       * This method is used for feedback for particular training
       * in pending status.
       * @return List<Trainings>
       * @param employeeId
       *            { @link long}
       * @throws TMSException
       *             {@link TMSException}
       */
      @Override
      @SuppressWarnings("unchecked")
      public final List<Trainings> retrieveTrainingId(final long employeeId)
    throws TMSException {
     List<Trainings> scheduleList = new ArrayList<Trainings>();
     try {
    scheduleList = hibernateTemplate
    .find(" from Trainings TS where TS.trainerId=" + employeeId
    + " and status in('completed')");
     } catch (Exception e) {
    throw new TMSException(e.toString());
     }
     return scheduleList;
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#retrieveFeedback(long)
       * This method is used for feedback for particular training
       * in completed status.
       * @param trainingId
       *            { @link long}
       * @throws TMSException
       *             {@link TMSException}
       * @return List<Enrollment>
       */
      @SuppressWarnings("unchecked")
      public final List<Enrollment> retrieveFeedback(final long trainingId)
    throws TMSException {
    try {
    return hibernateTemplate

    .find("from Enrollment E" + " where E.trainingId="
    + trainingId + " and E.statusId=6");
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
      }

      /**
       * @see com.practice.tms.dao.TrainingDao#retrieveEmployees()
       * This method is for retrieving employees from employee table.
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
    * @see com.practice.tms.dao.TrainingDao#retrieveTrainingIdForFeedback(long)
       * @return List<Trainings>
       * @param employeeId
       *            { @link long}
       * @throws TMSException
       *             {@link TMSException}
       */
      @SuppressWarnings("unchecked")
      public final List<Trainings> retrieveTrainingIdForFeedback(
    final long employeeId) throws TMSException {
    List<Trainings> scheduleListOne = new ArrayList<Trainings>();
    try {
    final List<Trainings> scheduleList = hibernateTemplate
    .find(" from Trainings ts where ts.status in ('Completed')");
    scheduleListOne = new ArrayList<Trainings>();
    final Iterator<Trainings> trainingScheduledIterator = scheduleList
    .iterator();
    while (trainingScheduledIterator.hasNext()) {
    final Trainings trainingScheduled = trainingScheduledIterator.next();
    final long trainingId = trainingScheduled.getTrainingId();
    final List<Enrollment> empList = hibernateTemplate
    .find(" from Enrollment where employeeId=" + employeeId
    + " and trainingId=" + trainingId
    + " and statusId=5");
    if (empList.size() != 0) {
scheduleListOne.add(trainingScheduled);
    }
    }
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return scheduleListOne;
      }

      /**
       * A method for employee giving a feed back.
       *
       * @param employeeId
       *            { @link long}
       * @param trainingId
       *            { @link long}
       * @param feedback
       *            { @link Feedback}
       * @return List<Enrollment>
       * @throws TMSException
       *             {@link TMSException}
       */
      @Override
      @SuppressWarnings("unchecked")
      public final int submitFeedback(final long employeeId,
    final long trainingId, final Feedback feedback) throws TMSException {
    try {
    final List<Enrollment> employeeList = hibernateTemplate
    .find(" from Enrollment where employeeId=" + employeeId
    + " and trainingId=" + trainingId);
    final Iterator<Enrollment> enrollmentIterator = employeeList.iterator();
    enrollmentIterator.hasNext();
    final Enrollment enrollment = enrollmentIterator.next();
    enrollment.setStatusId(6);
    feedback.setEnrollmentId(enrollment.getEnrollmentId());
    hibernateTemplate.saveOrUpdate(feedback);
    hibernateTemplate.update(enrollment);
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return 1;
      }

      /**
       * This method has the count of number of employees enrolled for a
       * particular trainingId and status is Inprogress and Approval.
       *
       * @see com.practice.tms.dao.TrainingDao#retrieveAllTrainingCount()
       * @return Map<Long, Integer>
       * @throws TMSException
       *             {@link TMSException}
       */
      @Override
      @SuppressWarnings("unchecked")
      public final Map<Long, Integer> retrieveAllTrainingCount()
    throws TMSException {
    Map<Long, Integer> countTrainings = new HashMap<Long, Integer>();
    try {
    final List<Trainings> scheduledList = hibernateTemplate
    .find("from Trainings");
    final Iterator<Trainings> trainingScheduledIterator = scheduledList
    .iterator();
    countTrainings = new HashMap<Long, Integer>();
    while (trainingScheduledIterator.hasNext()) {
    int countTraining = 0;
    final Trainings trainingScheduled = trainingScheduledIterator.next();
    final long trainingId = trainingScheduled.getTrainingId();
    final List<Enrollment> scheduledListOne = hibernateTemplate
    .find("from Enrollment where trainingId=" + trainingId
    + " and statusId in(1,2)");
    final Iterator<Enrollment> enrollmentIterator = scheduledListOne
    .iterator();
    while (enrollmentIterator.hasNext()) {
    final Enrollment enrollment = enrollmentIterator.next();
    countTraining = countTraining + 1;
    }
    countTrainings.put(trainingId, countTraining);
    }
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return countTrainings;
      }
      /**.
       * method to get employee details
       * @param userId { @link Employee}
       * @return employee object
       * @throws TMSException { @link TMSException}
       */
      @SuppressWarnings("unchecked")
      public final Employee getEmployee(final long userId)
    throws TMSException {
    List<Employee> empList = new ArrayList<Employee>();
      try {
     empList = hibernateTemplate.find(
          "from Employee where employeeId=" + userId);
      final Iterator<Employee> iterator = empList.iterator();
      while (iterator.hasNext()) {
          final Employee employee = iterator.next();
          return employee;
      }
    } catch (Exception e) {
      throw new TMSException(e.toString());
    }
      return null;
      }
}