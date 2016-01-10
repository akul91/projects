package com.practice.tms.web;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Feedback;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
import com.practice.tms.service.ITrainingService;
import com.practice.tms.util.Mail;
/**
 *
 * @author jyothitamma.
 *
 */
public class EmployeeController extends MultiActionController {
    /**
     * declaration of trainingService.
     */
    private ITrainingService trainingService;
    /**
     * setter injection trainingService.
     * @param trainingServObj { @ link TrainingServiceImpl}
     */
    public final void setTrainingService(
    final ITrainingService trainingServObj) {
        this.trainingService = trainingServObj;
        }
    /**
     * method for an employee to enroll for a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView enrollTraining(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        final ModelMap modelMap = new ModelMap();
        try
        {
        	final HttpSession httpSession = request.getSession();
        	final long employeeId = (Long) httpSession.getAttribute("userId");
       
	        modelMap.addAttribute("trainingList",
	        trainingService.retrieveAll(employeeId));
	        modelMap.addAttribute("trainingScheduled", new Trainings());
	        modelMap.addAttribute("location", trainingService.retrieveLocations());
	        modelMap.addAttribute("employees", trainingService.retrieveEmployees());
	        modelMap.addAttribute("employeeDetails",
	        		trainingService.getEmployeeDetails(employeeId));
	        modelMap.addAttribute("employeeCount",
	        trainingService.retrieveAllTrainingCount());
	        return new ModelAndView("employeeEnrollTraining", modelMap);
        }
        catch (Exception e) {
        	return new ModelAndView("error", "msg",e.toString());
		}  
    }
    /**
     * method to get the training details for an employee to enroll.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView etrainingForEnrollment(
    final HttpServletRequest request,
    final HttpServletResponse response) {
        ModelMap modelMap = new ModelMap();
        try
        {
        	final String value = request.getParameter("enroll");
        	final long trainingId = Long.parseLong(value);
        	final HttpSession httpSession = request.getSession();
        	final long userId = (Long) httpSession.getAttribute("userId");
        	final Trainings trainingScheduled =
        	trainingService.retireveTrainingInfo(
        			trainingId, userId);
        	if (null == trainingScheduled) {
        		return new ModelAndView("employeeEnrollmentFailure");
        	} else {
        		modelMap.addAttribute("trainingInfo", trainingScheduled);
        		modelMap.addAttribute("location",
        				trainingService.retrieveLocations());
        		modelMap.addAttribute("employeeDetails",
                		trainingService.getEmployeeDetails(userId));
        		modelMap.addAttribute("employees",
        				trainingService.retrieveEmployees());
        		return new ModelAndView("employeeEnrollment", modelMap);
        	}
        }
        catch (Exception e) {
        	return new ModelAndView("error", "msg",e.toString());
		}
        
    }
    /**
     * method to insert an enrollment request.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @param employee { @link {@link Employee}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView einsertRequest(final HttpServletRequest request,
    final HttpServletResponse response,
    final Employee employee)throws TMSException {
        try
        {
    	final long trainingId = Long.parseLong(request.getParameter("enroll"));
        final HttpSession httpSession = request.getSession();
        final long userId = (Long) httpSession.getAttribute("userId");
        final ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("employeeDetails",
        		trainingService.getEmployeeDetails(userId));
        final int check = trainingService.insertIntoMapping(trainingId, userId);
        modelMap.addAttribute("check", check);
        Mail.mail();
        return new ModelAndView("employeeEnrollmentConfirmation", modelMap);
        }
        catch (Exception e) {
        	return new ModelAndView("error", "msg",e.toString());
        }
    
    }
    /**
     * method for an employee to post a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView epostTraining(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        return new ModelAndView("employeePostTraining");
    }
    /**
     * method for an employee to view the list of training available.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView estatusTraining(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        try
        {

	    	final ModelMap modelMap = new ModelMap();
	        final HttpSession httpSession = request.getSession();
	        final long userId = (Long) httpSession.getAttribute("userId");
	        modelMap.addAttribute("employeeDetails",
	        		trainingService.getEmployeeDetails(userId));
	        modelMap.addAttribute("postedList",
	        trainingService.postedTrainings(userId));
	        modelMap.addAttribute("location",
	                trainingService.retrieveLocations());
	        return new ModelAndView("employeeStatusTraining", modelMap);
        }
	    catch (Exception e) {
	    	return new ModelAndView("error", "msg",e.toString());
		}
    }
    /**
     * method for an employee to view the list of training he posted.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView epostedTraining(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        final ModelMap modelMap = new ModelMap();
        try
        {
	        final HttpSession httpSession = request.getSession();
	        final long userId = (Long) httpSession.getAttribute("userId");
	        modelMap.addAttribute("postedList",
	        trainingService.postedTrainings(userId));
	        final long requestId = Long.parseLong(request.getParameter("requestId"));
	        modelMap.addAttribute("employeeDetails",
	        		trainingService.getEmployeeDetails(userId));
	        modelMap.addAttribute("postedStatusList",
	        trainingService.postedStatus(requestId));
	        modelMap.addAttribute("location",
	        trainingService.retrieveLocations());
	        modelMap.addAttribute("employees",
	        trainingService.retrieveEmployees());
	        return new ModelAndView("employeeStatusTraining", modelMap);
        }
        catch (Exception e) {
        	return new ModelAndView("error", "msg",e.toString());
        }
    }
    /**
     * method to view the status of employees who enrolled for a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView estatusEnrollment(
    final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        final ModelMap modelMap = new ModelMap();
        try
        {
        final HttpSession httpSession = request.getSession();
        final long userId = (Long) httpSession.getAttribute("userId");
        modelMap.addAttribute("appliedList",
        trainingService.appliedTrainings(userId));
        modelMap.addAttribute("location",
        trainingService.retrieveLocations());
        modelMap.addAttribute("employeeDetails",
        		trainingService.getEmployeeDetails(userId));
        modelMap.addAttribute("employees",
        trainingService.retrieveEmployees());
        return new ModelAndView("employeeStatusEnrollment", modelMap);
        }
        catch (Exception e) {
        	return new ModelAndView("error", "msg",e.toString());
        }
    }
    /**
     * method to view all the employees who enrolled.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView enrolledTraining(final HttpServletRequest request,
        final HttpServletResponse response) throws TMSException {
        final ModelMap modelMap = new ModelMap();
        try
        {
        	
        final long trainingId = Long.parseLong(request.getParameter("trainingId"));
        final HttpSession httpSession = request.getSession();
        final long userId = (Long) httpSession.getAttribute("userId");
        modelMap.addAttribute("appliedStatusList",
        trainingService.appliedStatus(trainingId));
        modelMap.addAttribute("employeeDetails",
        		trainingService.getEmployeeDetails(userId));
        modelMap.addAttribute("appliedList",
        trainingService.appliedTrainings(userId));
        modelMap.addAttribute("location",
        trainingService.retrieveLocations());
        modelMap.addAttribute("employees",
        trainingService.retrieveEmployees());
        return new ModelAndView("employeeStatusEnrollment", modelMap);
        }
        catch (Exception e) {
        	return new ModelAndView("error", "msg",e.toString());
        }
    }

    /**
     * method for an employee to get the list of training available.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView ecancelEnrollment(
    final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        final HttpSession httpSession = request.getSession();
        try
        {
        	
        final long userId = (Long) httpSession.getAttribute("userId");
        final ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("employeeDetails",
        		trainingService.getEmployeeDetails(userId));
        modelMap.addAttribute("trainingList",
        trainingService.enrolmentCancelation(userId));
        modelMap.addAttribute("location",
        trainingService.retrieveLocations());
        modelMap.addAttribute("employees",
        trainingService.retrieveEmployees());
        return new ModelAndView("employeeCancelEnrollment", modelMap);
        }
        catch (Exception e) {
        	return new ModelAndView("error", "msg",e.toString());
        }
    }
    /**
     * method for an employee to cancel enrollment for a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView eCancelEnrolTraining1(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    	try
        {
    	final String value = request.getParameter("enroll");
        final long dummy = Long.parseLong(value);
        final ModelMap modelMap = new ModelMap();
        final HttpSession httpSession = request.getSession();
        final long userId = (Long) httpSession.getAttribute("userId");
       
    	   trainingService.enrolmentCancelation1(dummy);
    	   modelMap.addAttribute("employeeDetails",
           		trainingService.getEmployeeDetails(userId));
    	   return new ModelAndView("employeeCancelEnrollmentConfirmation", modelMap);
       }
       catch (Exception e) {
    	   return new ModelAndView("error","msg",e.toString());
       }
    }
    /**
     * method for an employee view feedback for his training.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView eviewFeedback(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        final HttpSession httpSession = request.getSession();
        try
        {
        final long userId = (Long) httpSession.getAttribute("userId");
        final ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("employeeDetails",
        		trainingService.getEmployeeDetails(userId));
        modelMap.addAttribute("employeeList",
        trainingService.retrieveTrainingId(userId));
        modelMap.addAttribute("trainingScheduled",
        new Trainings());
        return new ModelAndView("employeeViewFeedback", modelMap);
        }
        catch (Exception e) {
     	   return new ModelAndView("error","msg",e.toString());
        }
    }
    /**
     * method for retrieving feedback for a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView eEmployeeFeedback1(
    final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        final ModelMap modelMap = new ModelMap();
        try
        {
        final long trainingId = Long.parseLong(request.getParameter("trainingId"));
        final List<Enrollment> list = trainingService.retrieveFeedback(trainingId);
        final HttpSession httpSession = request.getSession();
        final long userId = (Long) httpSession.getAttribute("userId");
        modelMap.addAttribute("employeeDetails",
        		trainingService.getEmployeeDetails(userId));
        modelMap.addAttribute("feedbackList", list);
        return new ModelAndView("employeeViewFeedback", modelMap);
        }
        catch (Exception e) {
     	   return new ModelAndView("error","msg",e.toString());
        }
    }
    /**
     * method for retrieving the feedback
     * for a training for a particular employee.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView egiveFeedback(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        final HttpSession httpSession = request.getSession();
        try
        {
        final long userId = (Long) httpSession.getAttribute("userId");
        final ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("trainingList",
        trainingService.retrieveTrainingIdForFeedback(userId));
        modelMap.addAttribute("employeeDetails",
        		trainingService.getEmployeeDetails(userId));
        modelMap.addAttribute("location",
                trainingService.retrieveLocations());
        modelMap.addAttribute("employees",
                trainingService.retrieveEmployees());
        return new ModelAndView("employeeGiveFeedback", modelMap);
        }
        catch (Exception e) {
     	   return new ModelAndView("error","msg",e.toString());
        }
    }
    /**
     * method for employee feedback form for a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView eFeedbackForm(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        try
        {
    	final Long trainingId = Long.parseLong(request.getParameter("enroll"));
        final ModelMap modelMap = new ModelMap();
        final HttpSession httpSession = request.getSession();
        final long userId = (Long) httpSession.getAttribute("userId");
        modelMap.addAttribute("employeeDetails",
        		trainingService.getEmployeeDetails(userId));
        modelMap.addAttribute("trainingInfo",
        trainingService.requestTrainings1(trainingId));
        modelMap.addAttribute("feedback", new Feedback());
        return new ModelAndView("employeeFeedbackForm", modelMap);
        }
        catch (Exception e) {
     	   return new ModelAndView("error","msg",e.toString());
        }
    }
    /**
     * method for an employee to give feedback for a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @param feedback { @link Feedback}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView egiveFeedbackForm(
    final HttpServletRequest request,
    final HttpServletResponse response,
    final Feedback feedback) throws TMSException {
        try
        {
    	final Long trainingId = Long.parseLong(request.getParameter("trainingId"));
        final HttpSession httpSession = request.getSession();
        final long employeeId = (Long) httpSession.getAttribute("userId");
        final ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("employeeDetails",
        		trainingService.getEmployeeDetails(employeeId));
        final int result = trainingService.submitFeedback(employeeId,
        trainingId, feedback);
        if (result == 1) {
        return new ModelAndView("employeeFeedbackConfirmation", modelMap);
        }
        }
        catch (Exception e) {
     	   return new ModelAndView("error","msg",e.toString());
        }
        return null;
    }
    /**
     * method for an employee to view history.
     * @param request { @link HttpServletRequest}
     * @param response { @link {@link HttpServletResponse}
     * @return {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView eviewHistory(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        final HttpSession httpSession = request.getSession();
        try
        {
        final long userId = (Long) httpSession.getAttribute("userId");
        final ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("history",
        trainingService.employeeHistory(userId));
        modelMap.addAttribute("location",
        trainingService.retrieveLocations());
        modelMap.addAttribute("employeeDetails",
        		trainingService.getEmployeeDetails(userId));
        modelMap.addAttribute("employees",
        trainingService.retrieveEmployees());
        return new ModelAndView("viewHistory", modelMap);
        }
        catch (Exception e) {
     	   return new ModelAndView("error","msg",e.toString());
        }
    }
}