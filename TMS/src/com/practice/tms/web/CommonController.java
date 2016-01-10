package com.practice.tms.web;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Feedback;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.TrainingInformation;
import com.practice.tms.exception.TMSException;
import com.practice.tms.service.ILoginService;
import com.practice.tms.service.IRegistrationService;
import com.practice.tms.service.IScheduleService;
/**
 *
 * @author kbharathi
 *
 */
public class CommonController extends MultiActionController {
/**
 * declaration of scheduleDaoService.
 */
    private IScheduleService  scheduleDaoService;
    /**
     * declaration of loginDAOService.
     */
    private ILoginService loginDAOService;
    /**
     * declaration of registrationDAOService.
     */
    private IRegistrationService registrationDAOService;
    /**
     * setter injection for loginDAOServiceImpl.
     * @param loginDAOServ { @link ILoginDaoService}
     */
    public final void setLoginDAOService(final ILoginService loginDAOServ) {
         this.loginDAOService = loginDAOServ;
    }
    /**
     * setter injection for scheduleDaoService.
     * @param scheduleDaoServ { @link IScheduleDaoService}
     */
    public final void setScheduleDaoService(
    final IScheduleService scheduleDaoServ) {
    this.scheduleDaoService = scheduleDaoServ;
    }
    /**
     * setter injection for registrationDaoService.
     * @param registrationDAOServ { @link IRegistrationDaoService}
     */
    public final void setRegistrationDAOService(final
              IRegistrationService registrationDAOServ) {
         this.registrationDAOService = registrationDAOServ;
    }
    /**
     *
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletRequest}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView changeOldPassword(
    final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
    try {
    final String password = request.getParameter("password");
    final String oldPassword = request.getParameter("oldPassword");
    final HttpSession httpSession = request.getSession();
    final long userId = (Long) httpSession.getAttribute("userId");
    final PrintWriter out = response.getWriter();
    Employee employee = registrationDAOService.getEmployeeDetails(userId);
    if (oldPassword.equals(employee.getPassword())) {
    employee.setPassword(password);
    registrationDAOService.update(employee);
    out.println("Password Change Successfull");
    } else {
    out.println("Invalid Current Password");
    }
    } catch (Exception e) {
         return new ModelAndView("error" , "msg" ,
                "Could not change password. Try Again");
    }
    return null;
    }
    /**
    * method for changing password.
    * @param request { @link HttpServletRequest}
    * @param response { @link HttpServletRequest}
    * @return ModelAndView { @link ModelAndView}
    * @throws TMSException { @link TMSException}
    */
    public final ModelAndView changePassword(
    final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
      try {
        final ModelMap modelMap =  new ModelMap();
        final HttpSession session = request.getSession();
        final long userId = (Long) session.getAttribute("userId");
        modelMap.addAttribute("employeeDetails",
                registrationDAOService.getEmployeeDetails(userId));
        return new ModelAndView("changePassword", modelMap);
        } catch (Exception e) {
             return new ModelAndView("error" , "msg" ,
                    "Could not change password. Try Again");
        }
    }
    /**
    * method for logout.
    * @param request { @link HttpServletRequest}
    * @param response { @link HttpServletRequest}
    * @return ModelAndView { @link ModelAndView}
    * @throws TMSException { @link TMSException}
    */
    public final ModelAndView clogout(
    final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
    final HttpSession httpSession = request.getSession();
    httpSession.invalidate();
    return new ModelAndView("logout");
    }
    /**
    * method for login.
    * @param request { @link HttpServletRequest}
    * @param response { @link HttpServletRequest}
    * @return ModelAndView { @link ModelAndView}
    * @throws TMSException { @link TMSException}
    */
    public final ModelAndView clogin(
    final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        logger.info("control has reached method"
    + "for logging into the home page");
        try {
        final long userId = Long.parseLong(request.getParameter("userId"));
        final String password = request.getParameter("password");
        final PrintWriter out = response.getWriter();
        final HttpSession session = request.getSession();
        String[] check = loginDAOService.login(userId, password);
        if (check[0].equals("0")) {
             return new ModelAndView("error", "msg",
                "An error occurred while logging in. Try again");
        } else {
            session.setAttribute("userId", userId);
            if (check[0].equals("1")) {
                 out.println("loggedInEmployee");
            } else if (check[0].equals("2")) {
               out.println("loggedInPM");
            } else {
               out.println("loggedInTA");
            }
        }
        } catch (Exception e) {
           return new ModelAndView("error", "msg",
               "An error occurred while logging in. Try again");
        }
      return null;
    }
    /**
    * method for registration.
    * @param request { @link HttpServletRequest}
    * @param response { @link HttpServletRequest}
    * @return ModelAndView { @link ModelAndView}
    * @throws TMSException { @link TMSException}
    */
    public final ModelAndView cregistration(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
         return new ModelAndView("registration", "message", "message");
    }
    /**
    * method for displaying home page of employee.
    * @param request { @link HttpServletRequest}
    * @param response { @link HttpServletRequest}
    * @return ModelAndView { @link ModelAndView}
    * @throws TMSException { @link TMSException}
    */
    public final ModelAndView cloginCall(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
       final HttpSession session = request.getSession();
    if (session.getAttribute("userId") == null) {
         return new ModelAndView("login");
    } else {
       try {
        final long userId = (Long) session.getAttribute("userId");
        final ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("employeeDetails",
              registrationDAOService.getEmployeeDetails(userId));
        return new ModelAndView("login", modelMap);
       } catch (Exception e) {
            return new ModelAndView("error", "msg",
                "An error occurred while logging in. Try again");
         }
    }
    }
    /**
    * method to display employee details after he logged in.
    * @param request { @link HttpServletRequest}
    * @param response { @link HttpServletRequest}
    * @return ModelAndView { @link ModelAndView}
    * @throws TMSException { @link TMSException}
    */
    public final ModelAndView cloggedInEmployee(
       final HttpServletRequest request,
          final HttpServletResponse response) throws TMSException {
    final HttpSession session = request.getSession();
    try {
    final long userId = (Long) session.getAttribute("userId");
    final ModelAndView view = new ModelAndView();
    view.addObject("employeeDetails",
    registrationDAOService.getEmployeeDetails(userId));
    view.setViewName("employeeHome");
    return view;
    } catch (Exception e) {
        return new ModelAndView("error", "msg",
            "An error occurred while logging in. Try again");
     }
    }
    /**
    * method for displaying home page of Project Manager.
    * @param request { @link HttpServletRequest}
    * @param response { @link HttpServletRequest}
    * @return ModelAndView { @link ModelAndView}
    * @throws TMSException { @link TMSException}
    */
    public final ModelAndView cloggedInPM(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
      final HttpSession session = request.getSession();
    try {
    final long userId = (Long) session.getAttribute("userId");
    final ModelAndView view = new ModelAndView();
    view.addObject("employeeDetails",
    registrationDAOService.getEmployeeDetails(userId));
    view.setViewName("projectManagerHome");
    return view;
    } catch (Exception e) {
        return new ModelAndView("error", "msg",
            "An error occurred while logging in. Try again");
     }
    }
    /**
    * method for displaying home page of Training Administrator.
    * @param request { @link HttpServletRequest}
    * @param response { @link HttpServletRequest}
    * @return ModelAndView { @link ModelAndView}
    * @throws TMSException { @link TMSException}
    */
    public final ModelAndView cloggedInTA(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
    final HttpSession session = request.getSession();
    try {
    final long userId = (Long) session.getAttribute("userId");
    final ModelAndView view = new ModelAndView();
    view.addObject("employeeDetails",
    registrationDAOService.getEmployeeDetails(userId));
    view.setViewName("trainingAdminHome");
    return view;
    } catch (Exception e) {
        return new ModelAndView("error", "msg",
            "An error occurred while logging in. Try again");
     }
    }
    /**
    *
    * @param request { @link HttpServletRequest}
    * @param response { @link HttpServletRequest}
    * @return ModelAndView { @link ModelAndView}
    * @throws TMSException { @link TMSException}
    */
   public final ModelAndView cforgotPwdQuestion(
        final HttpServletRequest request,
           final HttpServletResponse response) throws TMSException {
       final ModelMap modelMap = new ModelMap();
        try {
        final long employeeId =
                   Long.parseLong(request.getParameter("employeeId"));
        final HttpSession session = request.getSession();
        session.setAttribute("forgotId", employeeId);
            modelMap.addAttribute("question" ,
             loginDAOService.retrieveQuestion(employeeId));
         return new ModelAndView("securityQuestion" , modelMap);
        } catch (Exception e) {
          return new ModelAndView("error", "msg" ,
          "An error occurred while processing the request. Please try again.");
         }
   }
   /**
   * method for checking password.
   * @param request { @link HttpServletRequest}
   * @param response { @link HttpServletRequest}
   * @return ModelAndView { @link ModelAndView}
   * @throws TMSException { @link TMSException}
   */
   public final ModelAndView checkPassword(final HttpServletRequest request,
        final HttpServletResponse response) throws TMSException {
     try {
      logger.info("control has reached method for changing old password ");
      final String password = request.getParameter("password");
      final HttpSession httpSession = request.getSession();
      final long userId = (Long) httpSession.getAttribute("userId");
      PrintWriter out;
      out = response.getWriter();
      String retrievedPassword = registrationDAOService.checkPassword(userId);
      if (!retrievedPassword.equals(password)) {
         out.println("Current Password is incorrect");
      }
     } catch (Exception e) {
         return new ModelAndView("error", "msg" ,
         "An error occurred while processing the request. Please try again.");
     }
     return null;
   }
   /**
   * method to retrieve details of trainer.
   * @param request { @link HttpServletRequest}
   * @param response { @link HttpServletRequest}
   * @return ModelAndView { @link ModelAndView}
   * @throws TMSException { @link TMSException}
   */
   public final ModelAndView cpostTraining(final HttpServletRequest request ,
        final HttpServletResponse response) throws TMSException {
     final HttpSession session = request.getSession();
        try {
        final long userId = (Long) session.getAttribute("userId");
        final ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("employeeDetails",
             registrationDAOService.getEmployeeDetails(userId));
        return new ModelAndView("postTraining", modelMap);
        } catch (Exception e) {
            return new ModelAndView("error", "msg" ,
        "An error occurred while processing the request. Please try again.");
           }
   }
   /**
     * method for an employee to get the list of training he posted.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletRequest}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cancelTraining(final HttpServletRequest request,
        final HttpServletResponse response) throws TMSException {
        final HttpSession httpSession = request.getSession();
        final ModelMap modelMap =  new ModelMap();
        try {
        final long userId = (Long) httpSession.getAttribute("userId");
        modelMap.addAttribute("trainingList",
               registrationDAOService.requestTrainings(userId));
        modelMap.addAttribute("employeeDetails",
                registrationDAOService.getEmployeeDetails(userId));
        modelMap.addAttribute("location",
                registrationDAOService.retrieveLocations());
        modelMap.addAttribute("employees",
                registrationDAOService.retrieveEmployees());
        return new ModelAndView("cancelTraining", modelMap);
        } catch (Exception e) {
            return new ModelAndView("error", "msg" ,
       "An error occurred while processing the request. Please try again.");
           }
    }
    /**
     * method for an employee to cancel the training he posted.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cancelTrainingRequest(
    final HttpServletRequest request,
    final HttpServletResponse response)throws TMSException {
        final ModelMap modelMap =  new ModelMap();
        try {
        final String value = request.getParameter("enroll");
        final long requestId = Long.parseLong(value);
            modelMap.addAttribute("trainingRequested",
                 registrationDAOService.cancelTraining(requestId));
            return new ModelAndView("employeeCancelTrainingConfirmation",
                  modelMap);
        } catch (TMSException e) {
         return new ModelAndView("error", "msg", e.toString());
        }
    }
    /**
     * method for an employee view feedback for his training.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cviewFeedback(final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        final HttpSession httpSession = request.getSession();
        try {
        final long userId = (Long) httpSession.getAttribute("userId");
        final ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("employeeDetails",
                registrationDAOService.getEmployeeDetails(userId));
        modelMap.addAttribute("employeeList",
                registrationDAOService.retrieveTrainingId(userId));
        modelMap.addAttribute("location",
                registrationDAOService.retrieveLocations());
        modelMap.addAttribute("employees",
                registrationDAOService.retrieveEmployees());
        return new ModelAndView("viewFeedback", modelMap);
        } catch (TMSException e) {
            return new ModelAndView("error", "msg", e.toString());
           }
    }
    /**
     * method for retrieving feedback for a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cshowFeedback(
    final HttpServletRequest request,
    final HttpServletResponse response) throws TMSException {
        final HttpSession httpSession = request.getSession();
        try {
        final long userId = (Long) httpSession.getAttribute("userId");
        final ModelMap modelMap = new ModelMap();
        final long trainingId = Long.parseLong(
                request.getParameter("trainingId"));
        final List<Feedback> list = registrationDAOService.retrieveFeedback(
                  trainingId);
        modelMap.addAttribute("feedbackList", list);
        modelMap.addAttribute("employeeDetails",
             registrationDAOService.getEmployeeDetails(userId));
        return new ModelAndView("showFeedback", modelMap);
        } catch (TMSException e) {
            return new ModelAndView("error", "msg", e.toString());
           }
    }
    /**
     * method for retrieving feedback for a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView {@link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView csendAnswer(final HttpServletRequest request,
          final HttpServletResponse response) throws TMSException {
       final HttpSession session = request.getSession();
       try {
       final long forgotEmployeeId =
              Long.parseLong(session.getAttribute("forgotId").toString());
       final String answer = request.getParameter("answer");
       Employee employee = loginDAOService.retrieveQuestion(forgotEmployeeId);
       if (employee.getSecurityAnswer().equals(answer)) {
           return new ModelAndView("resetPassword", "m", "m");
       } else {
       final ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("question",
                   loginDAOService.retrieveQuestion(forgotEmployeeId));
            return new ModelAndView("securityQuestionFail", modelMap);
       }
       } catch (TMSException e) {
           return new ModelAndView("error", "msg", e.toString());
          }
    }
    /**
     * method for retrieving feedback for a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cresetPassword(final HttpServletRequest request,
             final HttpServletResponse response) throws TMSException {
        final HttpSession session = request.getSession();
        try {
        final long forgotEmployeeId = Long.parseLong(
          session.getAttribute("forgotId").toString());
        session.setAttribute("userId", forgotEmployeeId);
        final String password = request.getParameter("password");
        Employee employee = loginDAOService.resetPassword(forgotEmployeeId,
                password);
        String category = employee.getEmployeeCategory();
        PrintWriter out;
        out = response.getWriter();
        if (category.equals("1")) {
              out.println("cloggedInEmployee");
        } else if (category.equals("2")) {
             out.println("cloggedInPM");
        } else {
             out.println("cloggedInTA");
        }
        } catch (Exception e) {
              return new ModelAndView("error", "msg", e.toString());
        }
        return null;
    }
    /**
     * method for redirecting to forgot password page.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView forgotPassword(final HttpServletRequest request,
           final HttpServletResponse response) throws TMSException {
        return new ModelAndView("ForgotPassword");
    }
    /**
     * method for registering an employee.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cregister(final HttpServletRequest request,
            final HttpServletResponse response) throws TMSException {
        logger.info("control has reached the register method");
        try {
        long employeeId = Long.parseLong(request.getParameter("employeeId")
             .toString());
        String password = request.getParameter("password");
        String employeeName = request.getParameter("employeeName");
        String employeeEmail = request.getParameter("employeeEmail");
        long employeeNumber = Long.parseLong(request
             .getParameter("employeeNumber"));
        String employeeDesignation = request
             .getParameter("employeeDesignation");
        String tier = request.getParameter("Tier");
        long projectManagerId = Long.parseLong(request
             .getParameter("projectManagerId"));
        String category = request.getParameter("category");
        int question = Integer.parseInt(request.getParameter("security"));
        String answer = request.getParameter("securityAnswer");
        Employee employee = new Employee();
        if (category.equals("e")) {
             employee.setProjectManagerId(projectManagerId);
        } else {
            employee.setProjectManagerId(0);
        }
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName(employeeName);
        employee.setPassword(password);
        employee.setEmployeeCategory(category);
        employee.setEmployeeDesignation(employeeDesignation);
        employee.setEmployeeEmail(employeeEmail);
        employee.setEmployeeTier(tier);
        employee.setEmployeeNumber(employeeNumber);
        employee.setSecurityQuestion(question);
        employee.setSecurityAnswer(answer);
        PrintWriter out;
        out = response.getWriter();
           long returnValue = registrationDAOService.register(employee);
           out.println(returnValue);
        } catch (Exception e) {
            return new ModelAndView("error", "msg", e.toString());
            }
        return null;
    }
    /**
     * method for redirecting to registration success page.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cRegistrationSuccess(
    final HttpServletRequest request,
          final HttpServletResponse response) throws TMSException {
        return new ModelAndView("registrationSuccess", "success", "success");
        }
    /**
     * method for retrieving project manager ids.
     * @param request { @link HttpServletRequest}
     * @param response { @link link HttpServletResponse}
     * @param employee { @link Employee}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cprojectManager(
       final HttpServletRequest request, final HttpServletResponse response,
         final Employee employee) throws TMSException {
      ArrayList<Long> pmid = registrationDAOService.projectManagerId();
      PrintWriter out;
      try {
          out = response.getWriter();
      out.println(
           "<td>Project Manager Id:</td><td><select id='projectManagerId'>");
      for (Long i : pmid) {
       out.println("<option value='" + i + "'>" + i + "</option>");
      }
        out.println("</select></td>");
      } catch (IOException e) {
    	  return new ModelAndView("error", "msg", e.toString());
      }
      return null;
    }
    /**
     * method for inserting employee details.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @throws TMSException { @link TMSException}
     */
    public final void cinsert(final HttpServletRequest request,
            final HttpServletResponse response) throws TMSException {
        scheduleDaoService.insert();
    }
    /**
     * method for booking a slot.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cslot(final HttpServletRequest request,
              final HttpServletResponse response) throws TMSException {
        return new ModelAndView("startSlot", "userName", "mes");
    }
    /**
     * method for booking a date to post training.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cdate(final HttpServletRequest request,
           final HttpServletResponse response) throws TMSException {
        ArrayList<Date> arrayList = scheduleDaoService.dateRetrieve();
        PrintWriter out;
        try {
           out = response.getWriter();
        for (Date i : arrayList) {
             SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");
             out.println(dformat.format(i));
        }
        } catch (IOException e) {
        	 return new ModelAndView("error", "msg", e.toString());
        }
        return null;
    }
    /**
     * method for selecting timings.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView ctime(
         final HttpServletRequest request, final HttpServletResponse response)
        throws TMSException {
       HttpSession session = request.getSession();
       try {
       String dateString = request.getParameter("date");
       SimpleDateFormat formatter;
       Date date;
       formatter = new SimpleDateFormat("dd-MM-yyyy");
       date = (Date) formatter.parse(dateString);
       SimpleDateFormat trueFormat = new SimpleDateFormat("yyyy-MM-dd");
       String actual = trueFormat.format(date);
       session.setAttribute("date", actual);
       int[] timeSelect = {9, 11, 2, 4 };
       ModelMap modelMap = new ModelMap();
       modelMap.addAttribute("flag", "time");
       modelMap.addAttribute("timeFrom", timeSelect);
       return new ModelAndView("model", modelMap);
       } catch (Exception e) {
            return new ModelAndView("error", "msg", e.toString());
        }
    }
    /**
     * method for posting an existing training program.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView ctimerepost(final HttpServletRequest request,
            final HttpServletResponse response) throws TMSException {
           try {
           HttpSession session = request.getSession();
           String dateString = request.getParameter("date");
           SimpleDateFormat formatter;
           Date date;
           formatter = new SimpleDateFormat("dd-MM-yyyy");
           date = (Date) formatter.parse(dateString);
           SimpleDateFormat trueFormat = new SimpleDateFormat("yyyy-MM-dd");
           String actual = trueFormat.format(date);
           session.setAttribute("date", actual);
           int[] timeSelect = {9, 11, 2, 4 };
           ModelMap modelMap = new ModelMap();
           modelMap.addAttribute("flag", "timerepost");
           modelMap.addAttribute("timeFrom", timeSelect);
           return new ModelAndView("model", modelMap);
           } catch (Exception e) {
               return new ModelAndView("error", "msg", e.toString());
           }
       }
    /**
     * method for selecting end timings.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cSelectTo(final HttpServletRequest request,
         final HttpServletResponse response) throws TMSException {
        try {
          int firstFrom = Integer.parseInt(request.getParameter("firstFrom"));
          HttpSession session = request.getSession();
          session.setAttribute("firstFrom", firstFrom);
          int[] timeSelect = { 11, 1, 4, 6 };
          ModelMap modelMap = new ModelMap();
          modelMap.addAttribute("flag", "timeTo");
          modelMap.addAttribute("from", firstFrom);
          modelMap.addAttribute("timeFrom", timeSelect);
          return new ModelAndView("model", modelMap);
      } catch (Exception e) {
         return new ModelAndView("error", "msg", e.toString());
      }
    }
    /**
     * method for retrieving end timings in case of reposting a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cSelectTorepost(final HttpServletRequest request,
           final HttpServletResponse response) throws TMSException {
          try {
          int firstFrom = Integer.parseInt(request.getParameter("firstFrom"));
          HttpSession session = request.getSession();
          session.setAttribute("firstFrom", firstFrom);
          int[] timeSelect = { 11, 1, 4, 6 };
          ModelMap modelMap = new ModelMap();
          modelMap.addAttribute("flag", "timeTolocation");
          modelMap.addAttribute("from", firstFrom);
          modelMap.addAttribute("timeFrom", timeSelect);
          return new ModelAndView("model", modelMap);
          } catch (Exception e) {
              return new ModelAndView("error", "msg", e.toString());
          }
        }
    /**
     * method for storing training details.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cfinaldatabase(final HttpServletRequest request,
        final HttpServletResponse response) throws TMSException {
    try {
       long returnValue = 0;
       PrintWriter out = response.getWriter();
       HttpSession session = request.getSession();
       String date = session.getAttribute("date").toString();
       String location = session.getAttribute("locationSlot").toString();
       String slot = request.getParameter("first");
       String trainName = request.getParameter("trainName");
       String tech = request.getParameter("tech");
       String txtArea = request.getParameter("txtArea");
       String tier = request.getParameter("Tier");
       TrainingInformation trainingInformationObj = new TrainingInformation();
       trainingInformationObj.setTrainingProgram(trainName);
       trainingInformationObj.setTier(tier);
       trainingInformationObj.setTrainingDescription(txtArea);
       trainingInformationObj.setTechnology(tech);
       int id = Integer
              .parseInt(session.getAttribute("userId").toString());
       if (scheduleDaoService.getCategory(Long.parseLong(session
              .getAttribute("userId").toString())) == 'e') {
          returnValue = scheduleDaoService.employeePost(
                trainingInformationObj, id, date, location, slot);
          if (returnValue != 0) {
            out.println("Slots are booked<br>");
          }
       } else {
          returnValue = scheduleDaoService.managerPost(
               trainingInformationObj, id, date, location, slot);
          if (returnValue != 0) {
               out.println("Slots are occupied<br>");
          }
       }
       } catch (Exception e) {
           return new ModelAndView("error", "msg", e);
       }
    return null;
    }
   /**
     * method for retrieving location.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView clocationUsingSingleSession(
       final HttpServletRequest request,
        final HttpServletResponse response) throws TMSException {
       try {
       //PrintWriter out = response.getWriter();
       HttpSession session = request.getSession();
       String date = session.getAttribute("date").toString();
       int firstFrom = Integer.parseInt(
           session.getAttribute("firstFrom").toString());
       int firstTo = Integer.parseInt(request.getParameter("firstTo"));
       session.setAttribute("firstTo", firstTo);
       ArrayList<Location> oneSlotlocations =
         scheduleDaoService.oneSlotLocationRetrieve(date, firstFrom, firstTo);
       ModelMap modelMap = new ModelMap();
       modelMap.addAttribute("flag", "location");
       modelMap.addAttribute("locationlist", oneSlotlocations);
       modelMap.addAttribute("object", new Location());
       return new ModelAndView("model", modelMap);
       } catch (Exception e) {
          return new ModelAndView("error", "msg", e.toString());
       }
    }
    /**
     * method for retrieving location.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView clocationUsingSingleSessionrepost(
         final HttpServletRequest request,
         final HttpServletResponse response) throws TMSException {
       try {
         //PrintWriter out = response.getWriter();
         HttpSession session = request.getSession();
         String date = session.getAttribute("date").toString();
         int firstFrom = Integer.parseInt(
             session.getAttribute("firstFrom").toString());
         int firstTo = Integer.parseInt(request.getParameter("firstTo"));
         session.setAttribute("firstTo", firstTo);
         ArrayList<Location> oneSlotlocations =
           scheduleDaoService.oneSlotLocationRetrieve(date, firstFrom, firstTo);
         ModelMap modelMap = new ModelMap();
         modelMap.addAttribute("flag", "locationrepost");
         modelMap.addAttribute("locationlist", oneSlotlocations);
         modelMap.addAttribute("object", new Location());
        return new ModelAndView("model", modelMap);
       } catch (Exception e) {
          return new ModelAndView("error", "msg", e.toString());
       }
    }
    /**
     * method for booking first slot to post a training.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cfirstSlotDatabase(
      final HttpServletRequest request,
        final HttpServletResponse response) throws TMSException {
       try {
       String trainName = request.getParameter("trainName");
       String tech = request.getParameter("tech");
       String txtArea = request.getParameter("txtArea");
       String tier = request.getParameter("Tier");
       String listOfLocation = request.getParameter("listOfLocation");
       //PrintWriter out = response.getWriter();
       HttpSession session = request.getSession();
       int firstFrom = Integer.parseInt(
            session.getAttribute("firstFrom").toString());
       int firstTo = Integer.parseInt(
           session.getAttribute("firstTo").toString());
       String date = session.getAttribute("date").toString();
       SimpleDateFormat formatter;
       Date dateCheck;
       formatter = new SimpleDateFormat("yyyy-MM-dd");
       dateCheck = (Date) formatter.parse(date);
       long userId = Long.parseLong(session.getAttribute("userId").toString());
       int returnValue = scheduleDaoService.oneSlotDatabaseStore(trainName,
            tech, txtArea, tier, listOfLocation, dateCheck,
                firstFrom, firstTo, userId);
       if(returnValue== 1)
       {
           ModelMap modelMap=new ModelMap();
           modelMap.addAttribute("flag","success");
           modelMap.addAttribute("message","ctrainingsuccess.htm");
           return new ModelAndView("model",modelMap);
       }
       } catch (Exception e) {
          return new ModelAndView("error", "msg", e.toString());
       }
       return null;
    }
    /**
     * method for storing reposted training to database.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView cfirstSlotDatabaserepost(
      final HttpServletRequest request,
       final HttpServletResponse response) throws TMSException {
      try {
        HttpSession session = request.getSession();
        int trainingInfoId = Integer.parseInt(
             session.getAttribute("trainingInfoId").toString());
        String listOfLocation = request.getParameter("listOfLocationrepost");
        int firstFrom = Integer.parseInt(session.getAttribute("firstFrom")
             .toString());
        int firstTo = Integer.parseInt(session.getAttribute("firstTo")
           .toString());
        String date = session.getAttribute("date").toString();
        SimpleDateFormat formatter;
        Date dateCheck;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        dateCheck = (Date) formatter.parse(date);
        long userId = Long.parseLong(session.getAttribute("userId")
             .toString());
        int returnValue = scheduleDaoService.oneSlotDatabaseStorerepost(
           trainingInfoId, listOfLocation, dateCheck,
              firstFrom, firstTo, userId);
        if (returnValue == 1) {
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("flag", "success");
            modelMap.addAttribute("message", "ctrainingsuccessrepost.htm");
                        return new ModelAndView("model",modelMap);
        }
      } catch (Exception e) {
         return new ModelAndView("error", "msg", e.toString());
      }
      return null;
    }
    /**
     * method for retrieving list of trainings posted by employee.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView clistTraining(final HttpServletRequest request ,
        final HttpServletResponse response) throws TMSException {
        ModelMap modelMap = new ModelMap();
        HttpSession session = request.getSession();
        try {
        long userId = (Long) session.getAttribute("userId");
        modelMap.addAttribute("trainingList",
             registrationDAOService.getAllPastTrainings());
        modelMap.addAttribute("employeeDetails",
                registrationDAOService.getEmployeeDetails(userId));
        return new ModelAndView("listPastTrainings", modelMap);
        } catch (Exception e) {
          return new ModelAndView("error", "msg", e.toString());
        }
   }
    /**
     * method to redirect an employee to post training page.
     * @param request { @link HttpServletRequest}
     * @param response { @link HttpServletResponse}
     * @return ModelAndView { @link ModelAndView}
     * @throws TMSException { @link TMSException}
     */
    public final ModelAndView crepostTraining(final HttpServletRequest request,
        final HttpServletResponse response) throws TMSException {
           HttpSession session = request.getSession();
           try {
             long userId = (Long) session.getAttribute("userId");
             ModelMap modelMap = new ModelMap();
             final long trainingInfoId = Long.parseLong(
                  request.getParameter("training"));
             session.setAttribute("trainingInfoId", trainingInfoId);
             modelMap.addAttribute("employeeDetails",
                registrationDAOService.getEmployeeDetails(userId));
             modelMap.addAttribute("trainingInfoObject",
                registrationDAOService.getTrainingsInfo(trainingInfoId));
             modelMap.addAttribute("trainingInfoId", trainingInfoId);
             return new ModelAndView("postTraining", modelMap);
           } catch (Exception e) {
              return new ModelAndView("error", "msg", e.toString());
           }
    }
    /**
    * method to redirect employee to post training success page.
    * @param request { @link HttpServletRequest}
    * @param response { @link HttpServletResponse}
    * @return ModelAndView { @link ModelAndView}
    * @throws TMSException { @link TMSException}
    */
    public final ModelAndView ctrainingsuccessrepost(
    final HttpServletRequest request, final HttpServletResponse response)
    throws TMSException {
        HttpSession session = request.getSession();
        try {
            long userId = (Long) session.getAttribute("userId");
            Employee employeeDetails = registrationDAOService
            .getEmployeeDetails(userId);
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("employeeDetails",
            registrationDAOService.getEmployeeDetails(userId));
            if (employeeDetails.getEmployeeCategory().equals("e")) {
                modelMap.addAttribute(
                "message",
                "Training request is sent to project " +
                "manager for approval and venue is booked");
                return new ModelAndView("trainingSuccess", modelMap);
                } else {
                modelMap.addAttribute("message", "Venue is booked");
                return new ModelAndView("trainingSuccess", modelMap);
            }
            } catch (Exception e) {
            return new ModelAndView("error", "msg", e.toString());
        }
    }
    /**
    * method to redirect employee to post training success page.
    * @param request { @link HttpServletRequest}
    * @param response { @link HttpServletResponse}
    * @return ModelAndView { @link ModelAndView}
    * @throws TMSException { @link TMSException}
    */
    public final ModelAndView ctrainingsuccess(
    final HttpServletRequest request, final HttpServletResponse response)
    throws TMSException {
        HttpSession session = request.getSession();
        try {
            long userId = (Long) session.getAttribute("userId");
            Employee employeeDetails = registrationDAOService
            .getEmployeeDetails(userId);
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("employeeDetails",
            registrationDAOService.getEmployeeDetails(userId));
            if (employeeDetails.getEmployeeCategory().equals("e")) {
                modelMap.addAttribute(
                "message",
                "Training request is sent to project manager for approval and venue is booked");
                return new ModelAndView("trainingSuccess", modelMap);
                } else {
                modelMap.addAttribute("message", "Venue is booked");
                return new ModelAndView("trainingSuccess", modelMap);
            }
            } catch (Exception e) {
            return new ModelAndView("error", "msg", e.toString());
        }
    }
    }