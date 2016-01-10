package com.practice.tms.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
import com.practice.tms.service.IProjectManagerService;
/**
 *
 * @author jyothitamma.
 *
 */
public class ProjectManagerController extends MultiActionController {
/**.
 * declaring IprojectManagerService
 */
private IProjectManagerService iProjMangServ;
/**.
 * getter method for iProjectManagerService
 * @return iProjectManagerService
 */
public final IProjectManagerService getiProjMangServ() {
    return iProjMangServ;
    }
/**.
 * setter method for iProjectManagerService
 * @param iProjMangService { @link IProjectManagerService}
 */
public final void setiProjMangServ(
final IProjectManagerService iProjMangService) {
this.iProjMangServ = iProjMangService;
    }
    /**.
     *  * method to view the training of the employees .
     *  * @param request {@link HttpServletRequest}
    *  @param response {@link HttpServletResponse}
    * @throws TMSException {@link TMSException}
    * @return {@link ModelAndView}
    */
    public final ModelAndView pViewTraining(final HttpServletRequest request,
        final HttpServletResponse response)throws TMSException {
        final ModelMap modelMap = new ModelMap();
        try {
        HttpSession httpSession = request.getSession();
        long employeeId = (Long) httpSession.getAttribute("userId");
        final String value = request.getParameter("enroll");
        final int dummy = Integer.parseInt(value);
        httpSession.setAttribute("dummy", dummy);
        modelMap.addAttribute("trainingList",
        iProjMangServ.retrieveEnrol(dummy));
        modelMap.addAttribute("location",
        iProjMangServ.retrieveLocations());
        modelMap.addAttribute("employees",
        iProjMangServ.retrieveEmployees());
        modelMap.addAttribute("employeeDetails",
        	    iProjMangServ.retrieveEmployeeDetails(employeeId));
        } catch (Exception e) {
       final String msg = "Sorry no enrollments.";
        return new ModelAndView("error", "msg", msg);
        }
        return new ModelAndView("projectManagerRequestEnrollmentDetails",
        modelMap);
    }
    /**.
     * method to approve the enrollment request of employees
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @param employee { @link Employee}
     * @throws TMSException {@link TMSException}
     * @return {@link ModelAndView}
     */
    public final ModelAndView pRequestApprov(final HttpServletRequest request,
    final HttpServletResponse response,
    final Employee employee)throws TMSException {
    final ModelMap modelMap = new ModelMap();
    try {
    final HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");
    final int dummy = (Integer) httpSession.getAttribute("dummy");
    iProjMangServ.acceptRequest(dummy);
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    modelMap.addAttribute("value","1");
    } catch (Exception e) {
    final String msg = "Operation failed";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerRequestDecision",
            modelMap);
    }
    /**.
     * method to reject the enrollment request of employees
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @param employee { @link Employee}
     * @throws TMSException {@link TMSException}
     * @return {@link ModelAndView}
     */
    public final ModelAndView pRequestReject(final HttpServletRequest request,
    final HttpServletResponse response,
    final Employee employee)throws TMSException {
    	 final ModelMap modelMap = new ModelMap();
    	try {
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");
    int dummy = (Integer) httpSession.getAttribute("dummy");
    iProjMangServ.rejectRequest(dummy);
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    modelMap.addAttribute("value","2");
    } catch (Exception e) {
    final String msg = "Operation failed";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerRequestDecision",modelMap);
    }
    /**.
     * method to view the training posted by employees
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @param employee { @link Employee}
     * @throws TMSException {@link TMSException}
     * @return {@link ModelAndView}
     */

    public final ModelAndView pViewPostTraining(
    final HttpServletRequest request,
    final HttpServletResponse response,
    final Employee employee)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    String value = request.getParameter("enroll");
    int requestId = Integer.parseInt(value);
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");
    httpSession.setAttribute("requestId", requestId);
    modelMap.addAttribute("trainingRequested",
    iProjMangServ.specificTraining(requestId));
    modelMap.addAttribute("location",
    iProjMangServ.retrieveLocations());
    modelMap.addAttribute("employees",
    iProjMangServ.retrieveEmployees());
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    final String msg = "Operation failed";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerRequestTrainingDetails",
    modelMap);
    }
    /**.
     * method to approve the training posted by employee .
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @param employee { @link Employee}
     * @return { @link ModelAndView}
     */
    public final ModelAndView pPostApprov(final HttpServletRequest request,
    final HttpServletResponse response,
    final Employee employee)throws TMSException {
    	final ModelMap modelMap = new ModelMap();
    try {
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");
    int requestId = (Integer) httpSession.getAttribute("requestId");
    iProjMangServ.acceptPosting(requestId);
    modelMap.addAttribute("value","1");
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    String msg = "Operation failed";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerRequestDecision",modelMap);
    }
    /**.
     * method to reject the training posted by an employee
     *@param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @param employee { @link Employee}
     * @throws TMSException {@link TMSException}
     * @return { @link ModelAndView}
     */
    public final  ModelAndView pPostReject(final HttpServletRequest request,
    final HttpServletResponse response,
    final Employee employee)throws TMSException {
    final ModelMap modelMap = new ModelMap();
    try {
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");
       
    
    int requestId = (Integer) httpSession.getAttribute("requestId");
    iProjMangServ.rejectPosting(requestId);
    modelMap.addAttribute("value","2");
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    String msg = "Operation failed";
    return new ModelAndView("error", "msg", e.toString());
    }
    return new ModelAndView("projectManagerRequestDecision",modelMap);
    }
    /**.
     * method for the project manager to post a training
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return {@link ModelAndView}
     */
    public final ModelAndView postTraining(final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    try {
    return new ModelAndView("projectManagerPostTraining");
    } catch (Exception e) {
    String msg = "Posting failed";
    return new ModelAndView("error", "msg", msg);
    }
    }
    /**.
     * project manager viewing the training requested by employee
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return {@link ModelAndView}
     */
    public final ModelAndView prequestTraining(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");
    modelMap.addAttribute("trainingList",
    iProjMangServ.requestedTraining(employeeId));
    modelMap.addAttribute("trainingRequested",
    new Trainings());
    modelMap.addAttribute("location",
    iProjMangServ.retrieveLocations());
    modelMap.addAttribute("employees",
    iProjMangServ.retrieveEmployees());
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    String msg = "Operation failed";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerRequestTraining",
    modelMap);
    }
    /**.
     * project manager viewing the training enrolled by
     * the employees under him
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return {@link ModelAndView}
     */
    public final ModelAndView prequestEnrollment(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");
    modelMap.addAttribute("trainingList",
    iProjMangServ.retrieveRequestedEnrol(employeeId));
    modelMap.addAttribute("location",
    iProjMangServ.retrieveLocations());
    modelMap.addAttribute("employees",
    iProjMangServ.retrieveEmployees());
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    String msg = "Operation failed";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerRequestEnrollment",
    modelMap);
    }
    /**.
     * project manager viewing the
     * details of all training scheduled.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return {@link ModelAndView}
     */
    public final ModelAndView pstatusTraining(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");    
    modelMap.addAttribute("trainingList",
    iProjMangServ.viewTrainingScheduled());
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    modelMap.addAttribute("location",
    	    iProjMangServ.retrieveLocations());
    } catch (Exception e) {
    String msg = "No records to display";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerStatusTraining",
    modelMap);
    }
    /**.
     * project manager viewing the details of all the
     * employees who posted a training
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return { @link ModelAndView}
     */
    public final ModelAndView pviewEmployees(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");   
    long trainingId = Long.parseLong(
    request.getParameter("TrainingNames"));
    modelMap.addAttribute("trainingList",
    iProjMangServ.viewTrainingScheduled());
    modelMap.addAttribute("trainingDetails",
    iProjMangServ.getTrainingDetails(trainingId));
    modelMap.addAttribute("employeeList",
    iProjMangServ.getEmployeeDetails(trainingId));
    modelMap.addAttribute("flag",1);
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    modelMap.addAttribute("location",
    	    iProjMangServ.retrieveLocations());
    } catch (Exception e) {
    String msg = "No records to display";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerStatusTraining",
    modelMap);
    }
    /**.
     * project manager getting the details of employees under him.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return {@link ModelAndView}
     */
    public final ModelAndView pstatusEmployee(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");
    modelMap.addAttribute("empList",
    iProjMangServ.retrieveEmployeeNames(employeeId));
    modelMap.addAttribute("employeeUser",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    modelMap.addAttribute("location",
    	    iProjMangServ.retrieveLocations());
    } catch (Exception e) {
    String msg = "No records to display";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerStatusEmployee",
    modelMap);
    }
    /**.
     * project manager viewing the details of employee and his training
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return { @link ModelAndView}
     */
    public final ModelAndView pviewEmployeeInfo(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    HttpSession httpSession = request.getSession();
    long userId = (Long) httpSession.getAttribute("userId");
    modelMap.addAttribute("empList",
    iProjMangServ.retrieveEmployeeNames(userId));
    long employeeId = Long.parseLong(
    request.getParameter("EmployeeNames"));
    modelMap.addAttribute("trainingList",
    iProjMangServ.retrieveTrainingsInfo(employeeId));
    modelMap.addAttribute("employee",
    iProjMangServ.retrieveEmployeeDetails(employeeId));
    modelMap.addAttribute("location",
    	    iProjMangServ.retrieveLocations());
    modelMap.addAttribute("flag",1);
    modelMap.addAttribute("employeeUser",
    	    iProjMangServ.retrieveEmployeeDetails(userId));
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    String msg = "No records to display ";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerStatusEmployee",
    modelMap);
    }
    /**.
     * project manager canceling a post
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return { @link ModelAndView}
     */
    public final ModelAndView pcancel(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    HttpSession httpSession = request.getSession();
    ModelMap modelMap = new ModelMap();
    try {
    long employeeId = (Long) httpSession.getAttribute("userId");   
    long userId = (Long) httpSession.getAttribute("userId");
    modelMap.addAttribute("trainingList",
    iProjMangServ.requestTrainings(userId));
    modelMap.addAttribute("location",
    iProjMangServ.retrieveLocations());
    modelMap.addAttribute("employees",
    iProjMangServ.retrieveEmployees());
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    String msg = "Operation failed";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerCancel",
    modelMap);
    }
    /**.
     * project manager canceling the training details.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return { @link ModelAndView}
     */
    public final ModelAndView pCancelTrainingDetails(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");
      
    String value = request.getParameter("enroll");
    long requestId = Long.parseLong(value);
    httpSession.setAttribute("requestId",
    requestId);
    modelMap.addAttribute("trainingRequested",
    iProjMangServ.requestTrainings1(requestId));
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    String msg = "Operation failed";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerCancelDetails",
    modelMap);
    }
    /**.
     * project manager canceling the training request
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return { @link ModelAndView}
     */
    public final ModelAndView pCancelTrainingRequest(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    	HttpSession httpSession = request.getSession();
        long employeeId = (Long) httpSession.getAttribute("userId");
       
    String value = request.getParameter("enroll");
    long requestId = Long.parseLong(value);
    modelMap.addAttribute("trainingRequested",
    iProjMangServ.cancelTraining(requestId));
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    String msg = "Operation failed";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerCancelConfirmation",
    modelMap);
    }
    /**.
     * project manager viewing the feedback
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return { @link ModelAndView}
     */
    public final ModelAndView pviewFeedback(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    	HttpSession httpSession = request.getSession();
        long employeeId = (Long) httpSession.getAttribute("userId");
       
    long trainingId = Long.parseLong(
    request.getParameter("TrainingId"));
    modelMap.addAttribute("feedback",
    iProjMangServ.viewFeedbackServicePM(trainingId));
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    String msg = "No records to display";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerFeedback",
    modelMap);
}
    /**.
     * project manager retrieving the employee id's to view feedback
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return { @link ModelAndView}
     */
    public final ModelAndView pfeedback(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");
    modelMap.addAttribute("trainingIdList",
    iProjMangServ.getTrainingId(employeeId));
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    String msg = "No records to diaplay";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("projectManagerFeedback",
    modelMap);
    }
    /**.
     * project manager viewing the training which
     * employees under him attended
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException {@link TMSException}
     * @return { @link ModelAndView}
     */
    public final ModelAndView phistory(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
    ModelMap modelMap = new ModelMap();
    try {
    HttpSession httpSession = request.getSession();
    long employeeId = (Long) httpSession.getAttribute("userId");
    modelMap.addAttribute("history",
    iProjMangServ.history(employeeId));
    modelMap.addAttribute("employees",
    	    iProjMangServ.retrieveEmployees());
    modelMap.addAttribute("employeeDetails",
    	    iProjMangServ.retrieveEmployeeDetails(employeeId));
    } catch (Exception e) {
    String msg = "Operation failed";
    return new ModelAndView("error", "msg", msg);
    }
    return new ModelAndView("viewHistory",
    modelMap);
    }
}