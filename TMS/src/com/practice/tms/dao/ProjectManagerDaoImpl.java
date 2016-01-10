package com.practice.tms.dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Feedback;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.Schedule;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
/**.
 * @author test
 *
 */
  public class ProjectManagerDaoImpl implements IProjectManagerDao {
/**.
 *  declaring object for HibernateTemplate class
 */
  private HibernateTemplate hibernateTemplate;
/**.
 *
 * declaring setter injection method for session factory object
 * @param sessionFactory {@link SessionFactory}
 */
  public final void setSessionFactory(final SessionFactory sessionFactory) {
      this.hibernateTemplate = new HibernateTemplate(sessionFactory);
}
/**.
* method to retrieve all  the enrollments
* @param dummy { @link Enrollment}
* @return list of enrollments
* @throws TMSException { @link TMSException}
*/
  @SuppressWarnings("unchecked")
    public final List<Enrollment> retrieveEnrol(final long dummy)
     throws TMSException {
    List<Enrollment> enrollmentList = new ArrayList<Enrollment>();
  try {
       enrollmentList = hibernateTemplate.find(
            "from Enrollment where enrollmentId=?",
                new Object[] {dummy});
       } catch (Exception e) {
      throw new TMSException(e.toString());
      }
    return  enrollmentList;
    }
    /*
     * 0 Waiting for Approval 1 Approved 2 Rejection
     */
/**.
 * method to accept enrollment request by an employee
 * @param dummy { @link Enrollment}
 * @throws TMSException { @link TMSException}
*/
    @SuppressWarnings("unchecked")
  public final void acceptRequest(final long dummy) throws TMSException {
  final List<Enrollment> enrollList = new ArrayList<Enrollment>();
  try {
        enrollList.addAll(hibernateTemplate.find(
         "from Enrollment where enrollmentId=?",
                 new Object[] {dummy}));
        final Iterator<Enrollment> iterator = enrollList.iterator();
        Enrollment enrollment = new Enrollment();
        while (iterator.hasNext()) {
        enrollment = iterator.next();
        enrollment.setStatusId(2);
       }
       hibernateTemplate.update(enrollment);
     } catch (Exception e) {
   throw new TMSException(e.toString());
   }
 }
/**.
* method to reject enrollment request by an employee
* @param dummy { @link Enrollment}
* @throws TMSException { @link TMSException}
*/
  @SuppressWarnings("unchecked")
   public final void rejectRequest(final long dummy)
       throws TMSException {
       final List<Enrollment> enrollList = new ArrayList<Enrollment>();
       try {
       enrollList.addAll(hibernateTemplate.find(
       "from Enrollment where enrollmentId=?",
               new Object[] {dummy}));
       final Iterator<Enrollment> iterator = enrollList.iterator();
       Enrollment enrollment = new Enrollment();
       while (iterator.hasNext()) {
       enrollment = iterator.next();
       enrollment.setStatusId(3);
       }
       hibernateTemplate.update(enrollment);
    } catch (Exception e) {
       throw new TMSException(e.toString());
     }
   }
/**.
* method to retrieve trainings to be approved/rejected
* @param employeeId { @link TrainingsRequested}
* @return list of trainings requested
* @throws TMSException { @link TMSException}
*/
   @SuppressWarnings("unchecked")
    public final List<Trainings> requestedTraining(
               final long employeeId)
           throws TMSException {
        final List<Employee> empList = new ArrayList<Employee>();
        final List<Trainings> trainingList = new
        ArrayList<Trainings>();
        try {
        empList.addAll(hibernateTemplate.find(
           "from Employee where projectManagerId=?",
                 new Object[] {employeeId}));
        final Iterator<Employee> iterator = empList.iterator();
        while (iterator.hasNext()) {
        final Employee employee = iterator.next();
        final List<Trainings> list = hibernateTemplate
          .find("from Trainings where trainerId=? "
            + " and status in('Waiting For Approval')",
               new Object[] {employee.getEmployeeId()});
        final Iterator<Trainings> trIterator = list.iterator();
        while (trIterator.hasNext()) {
        final Trainings trngsRequested = trIterator.next();
        trainingList.add(trngsRequested);
      }
    }
  } catch (Exception e) {
      throw new TMSException(e.toString());
      }
   return trainingList;
  }
   /**.
   * method to retrieve details of a particular training
   * @param requestId { @link TrainingsRequested}
   * @return list of training requested
   * @throws TMSException { @link TMSException}
  */
    @SuppressWarnings("unchecked")
  public final Trainings specificTraining(final long requestId)
        throws TMSException {
      final List<Trainings> trainingList = new
          ArrayList<Trainings>();
       Trainings trngsRequested = new
          Trainings();
      try {
         trainingList.addAll(hibernateTemplate.find(
            "from Trainings where trainingId=?",
                new Object[] {requestId}));
      final Iterator<Trainings> iterator = trainingList.iterator();
      iterator.hasNext();
      trngsRequested = iterator.next();
    } catch (Exception e) {
        throw new TMSException(e.toString());
      }
       return trngsRequested;
  }

   /* and status not in('cancelled','rejected')" */
  /**.
  * method to retrieve all the trainings' details conducted by an employee
  * @param trainerId { @link TrainingScheduled}
  * @return list of trainings scheduled
  * @throws TMSException { @link TMSException}
  */
  @SuppressWarnings("unchecked")
   public final List<Trainings> history(final long trainerId)
        throws TMSException {
      List<Trainings> tSList = new
              ArrayList<Trainings>();
      final List<Employee> empList = new ArrayList<Employee>();
      try {
           tSList.addAll(hibernateTemplate.find(
                  "from Trainings where trainerId="+trainerId+
                  		" and status in('Completed','Canceled')"));
          empList.addAll(hibernateTemplate.find(
         "from Employee where projectManagerId=?",
               new Object[] {trainerId}));
          final Iterator<Employee> iterator = empList.iterator();
          while (iterator.hasNext()) {
          Employee employee=iterator.next();
          long employeeId = employee.getEmployeeId();
          tSList.addAll(hibernateTemplate.find(
                   "from Trainings where trainerId="+employeeId+
                   " and status in('Completed','Canceled','Rejected')"));
    	  }
        } catch (Exception e) {
      throw new TMSException(e.toString());
     }
    return tSList;
   }
  /**.
  * method to accept posted training by the project manager
  * @param requestId { @link TrainingsRequested}
  * @return an integer
  * @throws TMSException { @link TMSException}
  */
   @SuppressWarnings("unchecked")
  public final int acceptPosting(final long requestId)
           throws TMSException {
        List<Trainings> reqList = new
        ArrayList<Trainings>();
        try {
        reqList.addAll(hibernateTemplate.find(
        "from Trainings where trainingId=?",
              new Object[] {requestId}));
        Iterator<Trainings> iterator = reqList.iterator();
        iterator.hasNext();
        Trainings trainingScheduled = iterator.next();
        trainingScheduled.setStatus("InProgress");
        hibernateTemplate.saveOrUpdate(trainingScheduled);
        } catch (Exception e) {
           throw new TMSException(e.toString());
        }
     return 1;
    }
  /**.
  * method to reject posted training by the project manager
  * @param requestId { @link TrainingsRequested}
  * @return an integer
  * @throws TMSException { @link TMSException}
  */
   @SuppressWarnings("unchecked")
  public final int rejectPosting(final long requestId)
      throws TMSException {
     List<Trainings> reqList = new
     ArrayList<Trainings>();
     try {
          reqList.addAll(hibernateTemplate.find(
              "from Trainings where trainingId=?",
                  new Object[] {requestId}));
          Iterator<Trainings> iterator = reqList.iterator();
        iterator.hasNext();
        Trainings trngsRequested = iterator.next();
        trngsRequested.setStatus("Rejected");
        Schedule schedule =trngsRequested.getSchedule();
        trngsRequested.setSchedule(null);
        hibernateTemplate.update(trngsRequested);
        hibernateTemplate.delete(schedule);
        } catch (Exception e) {
                 throw new TMSException(e.toString());
                 }
     return 1;
     }
  /**.
  * method to retrieve enrollments requested by..
  * all employees under the project manager
  * @param employeeId { @link Enrollment}
  * @return list of enrollments
  * @throws TMSException { @link TMSException}
  */
  @SuppressWarnings("unchecked")
  public final List<Enrollment> retrieveRequestedEnrol(final long employeeId)
         throws TMSException {
     List<Employee> empList = new ArrayList<Employee>();
     List<Enrollment> enrollList = new ArrayList<Enrollment>();
     try {
          empList.addAll(hibernateTemplate.find(
                  "from Employee where projectManagerId=? ",
                  new Object[] {employeeId}));
          Iterator<Employee> iterator = empList.iterator();
          while (iterator.hasNext()) {
               Employee employee = iterator.next();
               List<Enrollment> list = hibernateTemplate.find(
                   "from Enrollment where employeeId=? and statusId in('1')",
                   new Object[] {employee.getEmployeeId()});
               Iterator<Enrollment> enrollIterator2 = list.iterator();
               while (enrollIterator2.hasNext()) {
                   Enrollment enrollment = enrollIterator2.next();
                   enrollList.add(enrollment);
                   }
               }
          } catch (Exception e) {
           throw new TMSException(e.toString());
           }
     return enrollList;
     }
  /**.
  * method to view all the feedbacks to a particular training
  * @param trainingId {@link Enrollment}
  * @return list of feedback
  * @throws TMSException {@link TMSException}
  */
  @SuppressWarnings("unchecked")
  public final List<Feedback> viewFeedback(final long trainingId)
      throws TMSException {
        List<Feedback> fblist = new
            ArrayList<Feedback>();
        List<Long> list = new ArrayList<Long>();
        try {
            list.addAll(hibernateTemplate
                 .find("select enrollmentId from Enrollment "
            + "where trainingId="
                    + trainingId));
            for (long l : list) {
                 fblist.addAll(hibernateTemplate
                    .find(
                     "from Feedback where enrollmentId=" + l));
                 }
            } catch (Exception e) {
           throw new TMSException(e.toString());
        }
        return fblist;
        }
  /**.
  * method to retrieve all the training ids posted by employees
  * under logged in project manager
  * @param employeeId {@link TrainingScheduled}
  * @return training ids
  * @throws TMSException {@link TMSException}
  */
  @SuppressWarnings("unchecked")
  public final List<Trainings> getTrainingId(final long employeeId)
            throws TMSException {
        List<Long> empIdList = new
                ArrayList<Long>();
        List<Trainings> tsList = new
              ArrayList<Trainings>();
        List<Trainings> trngIdList = new
             ArrayList<Trainings>();
        try {
            empIdList.add(employeeId);
            List<Employee> elist = hibernateTemplate
               .find("from Employee e where e.projectManagerId=" + employeeId);
            Iterator<Employee> iterator = elist.iterator();
            while (iterator.hasNext()) {
                Employee employee = iterator.next();
                empIdList.add(employee.getEmployeeId());
                }
            for (long e : empIdList) {
                List<Trainings> list = hibernateTemplate
                   .find("from TrainingScheduled t where t.trainerId=" + e);
                Iterator<Trainings> iterators = list.iterator();
                while (iterators.hasNext()) {
                  Trainings trngsch = iterators.next();
                  tsList.add(trngsch);
                  }
                }
            for (Trainings s : tsList) {
                trngIdList.addAll(hibernateTemplate
                  .find("from TrainingScheduled t where t.status='completed' "
                + "and t.trainingID=" + s.getTrainingId()));
                }
            } catch (Exception e) {
            throw new TMSException(e.toString());
            }
        return trngIdList;
        }
  /**.
  * method to retrieve locations to give training
  * @return list of locations
  * @throws TMSException { @link TMSException}
  */
  @SuppressWarnings("unchecked")
   public final List<Location> retrieveLocations() throws TMSException {
        List<Location> locationList = new
               ArrayList<Location>();
        try {
           locationList.addAll(
                  hibernateTemplate.find("from Location"));
           } catch (Exception e) {
            throw new TMSException(e.toString());
            }
        return locationList;
        }
  /**.
  * method to retrieve all the employees' details
  * @return list of employee details
  * @throws TMSException { @link TMSException}
  */
   @SuppressWarnings("unchecked")
   public final List<Employee> retrieveEmployees()
       throws TMSException {
        List<Employee> empList = new
            ArrayList<Employee>();
        try {
           empList.addAll(
               hibernateTemplate.find("from Employee"));
           } catch (Exception e) {
           throw new TMSException(e.toString());
           }
        return empList;
        }
  /**.
  * method to retrieve the details of an employee
  * @param employeeId { @link Employee}
  * @return an object of Employee class
  * @throws TMSException { @link TMSException}
  */
   @SuppressWarnings("unchecked")
   public final Employee retrieveEmployeeDetails(final long employeeId)
        throws TMSException {
       Employee employee = new Employee();
       List<Employee> empList = new ArrayList<Employee>();
       try {
         empList.addAll(hibernateTemplate
              .find("from Employee E where E.employeeId=" + employeeId));
         Iterator<Employee> iterators = empList.iterator();
         if (iterators.hasNext()) {
             employee = iterators.next();
              }
         } catch (Exception e) {
            throw new TMSException(e.toString());
            }
       return employee;
       }
  /**.
  * method to retrieve the trainings enrolled by an employee
  * @param employeeId { @link Enrollment}
  * @return list of trainings enrolled by an employee
  * @throws TMSException { @link TMSException}
  */
   @SuppressWarnings("unchecked")
   public final List<Enrollment> retrieveTrainingsInfo(final long employeeId)
         throws TMSException {
        List<Enrollment> enrollList = new ArrayList<Enrollment>();
        try {
           enrollList.addAll(
                hibernateTemplate.find("from Enrollment where employeeId="
           + employeeId));
           } catch (Exception e) {
        throw new TMSException(e.toString());
        }
        return enrollList;
        }
  /**.
  * method to retrieve employee names under particular project manager
  * @param employeeId { @link Employee}
  * @return list of employee details
  * @throws TMSException { @link TMSException}
  */
   @SuppressWarnings("unchecked")
  public final List<Employee> retrieveEmployeeNames(final long employeeId)
        throws TMSException {
       List<Employee> empList = new ArrayList<Employee>();
       try {
           empList.addAll(hibernateTemplate.find(
                "from Employee where projectManagerId="
           + employeeId));
           } catch (Exception e) {
            throw new TMSException(e.toString());
            }
       return empList;
       }
  /**.
  * method to know the details of all employees who
  *  attended a particular training
  * @param trainingId { @link Enrollment}
  * @return list of enrollments for a particular training
  * @throws TMSException { @link TMSException}
  */
  @SuppressWarnings("unchecked")
  public final List<Enrollment> getEmployeeDetails(final long trainingId)
        throws TMSException {
       List<Enrollment> enrollList = new
              ArrayList<Enrollment>();
       try {
           enrollList.addAll(
                 hibernateTemplate.find("from Enrollment where trainingId="
           + trainingId));
           } catch (Exception e) {
            throw new TMSException(e.toString());
            }
       return enrollList;
       }
  /**.
  * method to view all the trainings scheduled
  * @return list of all trainings scheduled
  * @throws TMSException { @link TMSException}
  */
  @SuppressWarnings("unchecked")
  public final List<Trainings> viewTrainingScheduled()
         throws TMSException {
       List<Trainings> tsList = new
            ArrayList<Trainings>();
       try {
          tsList.addAll(hibernateTemplate.find("from Trainings"));
          } catch (Exception e) {
          throw new TMSException(e.toString());
          }
       return tsList;
       }
  /**.
  * method to get the training details of training details
  * @param trainingId { @link TrainingScheduled}
  * @return details of a training
  * @throws TMSException { @link TMSException}
  */
  @SuppressWarnings("unchecked")
  public final Trainings getTrainingDetails(final long trainingId)
       throws TMSException {
     List<Trainings> tsList = new
           ArrayList<Trainings>();
     Trainings trngscheduled = new Trainings();
     try {
         tsList.addAll(hibernateTemplate
               .find("from Trainings TS where TS.trainingId="
         + trainingId));
         Iterator<Trainings> iterators = tsList.iterator();
         if (iterators.hasNext()) {
        	 trngscheduled = iterators.next();
             }
         } catch (Exception e) {
           throw new TMSException(e.toString());
           }
     return trngscheduled;
     }
  /**.
  * method to retrieve trainings posted by an employee which are in progress
  * @param employeeId { @link TrainingScheduled}
  * @return list of trainings
  * @throws TMSException { @link TMSException}
  */
  @SuppressWarnings("unchecked")
  public final List<Trainings> requestTrainings(final long employeeId)
        throws TMSException {
         List<Trainings> tsList = new
             ArrayList<Trainings>();
         try {
            tsList = hibernateTemplate
            .find("from TrainingScheduled  where trainerId=?"
            + " and status not in('cancelled','completed')",
            new Object[] {employeeId});
            } catch (Exception e) {
             throw new TMSException(e.toString());
           }
         return tsList;
         }
  /**.
  * method to retrieve all the trainings
  * @param requestId { @link TrainingScheduled}
  * @return list of trainings
  * @throws TMSException { @link TMSException}
  */
  @SuppressWarnings("unchecked")
  public final Trainings requestTrainings1(final long requestId)
             throws TMSException {
      List<Trainings> list = new
           ArrayList<Trainings>();
      Trainings trngsRequested = new
           Trainings();
      try {
          list.addAll(hibernateTemplate.find(
              "from TrainingScheduled where trainingID=?",
              new Object[] {requestId}));
          Iterator<Trainings> iterator = list.iterator();
          iterator.hasNext();
          trngsRequested = iterator.next();
          } catch (Exception e) {
          throw new TMSException(e.toString());
          }
      return trngsRequested;
      }
  /**.
  * method to cancel training by the project manager
  * @param requestId {@link TrainingsRequested}
  * @return an integer
  * @throws TMSException {@link TMSException}
  */
  @SuppressWarnings("unchecked")
  public final int cancelTraining(final long requestId)
        throws TMSException {
   List<Trainings> list = new
          ArrayList<Trainings>();
   try {
       list.addAll(hibernateTemplate.find(
           "from TrainingScheduled where trainingID=?",
           new Object[] {requestId}));
       Iterator<Trainings> iterator = list.iterator();
       iterator.hasNext();
       Trainings trngsRequested = iterator.next();
       trngsRequested.setStatus("cancelled");
       trngsRequested.setSchedule(null);
       hibernateTemplate.update(trngsRequested);
       } catch (Exception e) {
       throw new TMSException(e.toString());
       }
   return 1;
   }
  }