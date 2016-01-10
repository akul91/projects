package com.practice.tms.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.Schedule;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
    /**
     *.
     * @author bgundavarapu
     *
     */
    public class TrainingAdminDaoImpl implements ITrainingAdminDao {
    /**
     * template provided by hibernate.
     */
    private HibernateTemplate hibernateTemplate;
    /**.
    * This is a setter injection for SessionFactory.
    * @param sessionFactory { @link SessionFactory}
    */
    public final void setSessionFactory(final SessionFactory sessionFactory) {
    this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    /**.
     * @see com.practice.tms.dao.TrainingAdminDao#viewCurrentTrainings()
     * The training administrator can view the history of current
     * training by checking the status not equal to completed.
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
    @SuppressWarnings("unchecked")
    public final List<Trainings> viewCurrentTrainings()
    throws TMSException {
    List<Trainings> scheduleList = new ArrayList<Trainings>();
    try {
    scheduleList = hibernateTemplate.find("from Trainings"
    + " where status not in ('Completed','Canceled','Rejected')");
    final Iterator<Trainings> scheduledIterator =
    scheduleList.iterator();
    while (scheduledIterator.hasNext()) {
    final Trainings trainingScheduled = scheduledIterator.next();
    }
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return scheduleList;
    }
    /**.
     * @see com.practice.tms.dao.TrainingAdminDao#viewHistory()
     * The training administrator can view the
     * history of training by checking the status equal to completed.
     * @return List<TrainingScheduled>
     * @throws TMSException { @link TMSException}
     */
    @SuppressWarnings("unchecked")
public final List<Trainings> viewHistory() throws TMSException {
    List<Trainings> scheduleList = new ArrayList<Trainings>();
    try {
    scheduleList = hibernateTemplate.find("from Trainings "
    + " where status in('Completed','Canceled')");
    final Iterator<Trainings> scheduledIterator =
    scheduleList.iterator();
    while (scheduledIterator.hasNext()) {
     final Trainings trainingScheduled = scheduledIterator.next();
    }
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return scheduleList;
    }
    /**
     *@see com.practice.tms.dao.TrainingAdminDao#viewHistoryDetails(long)
     *The training administrator can view the history
     * of all the employees who has enrolled in that particular training.
     * @return List<Enrollment>
     * @throws TMSException { @link TMSException}
     * @param trainingId { @link long}
     */
    @SuppressWarnings("unchecked")
    public final List<Enrollment> viewHistoryDetails(final long trainingId)
    throws TMSException {
    List<Enrollment> enrollments = new ArrayList<Enrollment>();
    try {
    enrollments = hibernateTemplate.find(
             "from Enrollment where trainingId=" + trainingId);
    } catch (Exception e) {
         throw new TMSException(e.toString());
    }
      return enrollments;
    }
    /**
     * @see com.practice.tms.dao.TrainingAdminDao#retrieveLocations()
     * The training administrator can view the Locations from location table.
     * @return List<Location>
     * @throws TMSException { @link TMSException}
     */
    @SuppressWarnings("unchecked")
    public final List<Location> retrieveLocations() throws TMSException {
    try {
    return hibernateTemplate.find("from Location");
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @see com.practice.tms.dao.TrainingAdminDao#retrieveEmployees()
     * The training administrator can retrieve all the
     * details of employees from the employee table.
     * @return List<Employee>
     * @throws TMSException { @link TMSException}
     */
    @SuppressWarnings("unchecked")
    public final List<Employee> retrieveEmployees() throws TMSException {
    try {
    return hibernateTemplate.find("from Employee");
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @return List<Employee>
     * @throws TMSException { @link TMSException}
     */
    @SuppressWarnings("unchecked")
    @Override
    public final List<Employee> getAllUser() throws TMSException {
    try {
    return hibernateTemplate
    .find("from Employee where employeeCategory in ('e','p')");
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    }
    /**
     * @param employeeId { @link long}
     * @return int
     * @throws TMSException { @link TMSException}
     */
    @SuppressWarnings("unchecked")
    @Override
    public final int deleteUser(final long employeeId) throws TMSException {
    try {
    List<Employee> employee = hibernateTemplate
    .find("from Employee where employeeId=" + employeeId);
    hibernateTemplate.delete(employee.get(0));
    @SuppressWarnings("unchecked")
    List<Enrollment> enrollmentList = hibernateTemplate
    .find("from Enrollment where employeeId=" + employeeId);
    for (int index = 0; index < enrollmentList.size(); index++) {
    hibernateTemplate.delete(enrollmentList.get(index));
    }
    List<Trainings> trainingScheduledList = hibernateTemplate
    .find("from Trainings where trainerId="
    + employeeId);
    for (int index = 0; index < trainingScheduledList.size(); index++) {
    Trainings trainingScheduled = trainingScheduledList
    .get(index);
    Schedule schedule = trainingScheduled.getSchedule();
    trainingScheduled.setSchedule(null);
    trainingScheduled.setTrainingInformation(null);
    hibernateTemplate.delete(trainingScheduled);
    hibernateTemplate.delete(schedule);
    }
    return 1;
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    }
    /**.
     * method to get employee details
     * @param userId { @link Employee}
     * @return employee object
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
    }