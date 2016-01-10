package com.practice.tms.service;
import java.util.ArrayList;
import java.util.List;

import com.practice.tms.dao.IRegistrationDao;
import com.practice.tms.dao.RegistrationDaoImpl;
import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Feedback;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.TrainingInformation;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
    /**
     *
     * @author bgundavarapu
     *
     */
    public class RegistrationServiceImpl implements IRegistrationService {
    /**
     *
     * variable of RegistrationDaoImpl type.
     */
    private IRegistrationDao regDAOImpl;
    /**
     *
     * @param regDAOImp { @link IRegistrationDao}
     */
    public final void setRegDAOImpl(
         final IRegistrationDao regDAOImp) {
    this.regDAOImpl = regDAOImp; }
    /**.
     * method to update the password
     * @param employee { @link Employee}
     * @throws TMSException { @link TMSException}
     *
     */
    public final void update(final Employee employee) throws TMSException {
    try {
    regDAOImpl.updatePassword(employee);
      } catch (TMSException e) {
       throw new TMSException(e.toString());
       }
    }
    /**.
     * method to register new employee details
     * @param employee { @link Employee}
     * @return employeeId { @link Employee}
     * @throws TMSException { @link TMSException}
     */
    public final long register(final Employee employee) throws TMSException {
     try {
        return regDAOImpl.register(employee);
        } catch (TMSException e) {
         throw new TMSException(e.toString());
         }
     }
    /**
     * method to retrieve all the project manager ids.
     * @return pmList { @link Employee}
     * @throws TMSException { @link TMSException}
     */
    public final ArrayList<Long> projectManagerId() throws TMSException {
        final ArrayList<Long> pmList = new ArrayList<Long>();
        try {
            pmList.addAll(regDAOImpl.projectManagerId());
        } catch (TMSException e) {
           throw new TMSException(e.toString());
        }
        return pmList;
    }
    /**.
     * method to get employee details
     * @param userId { @link Employee}
     * @return employee object { @link Employee}
     * @throws TMSException { @link TMSException}
     */
    public final Employee getEmployeeDetails(final long userId)
          throws TMSException {
   try {
      return regDAOImpl.getEmployee(userId);
   } catch (TMSException e) {
      throw new TMSException(e.toString());
      }
  }
    @Override
    @SuppressWarnings("unchecked")
    public final String checkPassword(final long userId) throws TMSException {
    return regDAOImpl.checkPassword(userId);
    }
    /**
     * @return List<Employee>
     * @throws TMSException { @link TMSException}
     */
    public final List<Employee> retrieveEmployees() throws TMSException {
    try {
    return regDAOImpl.retrieveEmployees();
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
  return regDAOImpl.retrieveLocations();
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
  return regDAOImpl.requestTrainings(employeeId);
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
  return regDAOImpl.cancelTraining(requestId);

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
  return regDAOImpl.retrieveTrainingId(employeeId);
  } catch (TMSException e) {
  throw new TMSException(e.toString());
  }
  }
  /**
   * @param trainingId { @link long}
   * @return List<Enrollment>
   * @throws TMSException { @link TMSException}
   */
  public final List<Feedback> retrieveFeedback(final long trainingId)
  throws TMSException {
  try {
  return regDAOImpl.retrieveFeedback(trainingId);
  } catch (TMSException e) {
  throw new TMSException(e.toString());
  }
  }
  @Override
  public final List<TrainingInformation> getAllPastTrainings()
  throws TMSException {
  try {
  return regDAOImpl.retrieveAllPastTrainings();
} catch (TMSException e) {
  throw new TMSException(e.toString());
  }
  }
  @Override
  public final TrainingInformation getTrainingsInfo(final long trainingInfoId)
  throws TMSException {
  try {
  return regDAOImpl.getTrainingsInfo(trainingInfoId);
  } catch (TMSException e) {
  throw new TMSException(e.toString());
  }
  }
  }