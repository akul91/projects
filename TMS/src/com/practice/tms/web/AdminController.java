
package com.practice.tms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.practice.tms.domain.Enrollment;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
import com.practice.tms.service.IAddVenueService;
import com.practice.tms.service.ITrainingAdminService;
/**.
*
* @author kbharathi
*
*/
  public class AdminController extends MultiActionController {
  /**.
   * declaring logger for this controller
   */
  static final Logger LOGGER = Logger.getLogger(AdminController.class);
  /**.
   * declaring a variable of AddVenueService type
   */
  private IAddVenueService addVenueServ;
  /**.
   * declaring a variable of ITrainingAdminService type
   */
  private ITrainingAdminService iTrngAdminService;
  /**iTrainingAdminService.
   *getter method for iTrainingAdminService
   * @return ITrainingAdminService
   */
  public final ITrainingAdminService getITrngAdminService() {
     return iTrngAdminService; }
  /**.
   * setter method for iTrainingAdminService
   * @param iTrgAdminService { @ link ITrainingAdminService}
   */
  public final void setiTrngAdminService(
       final ITrainingAdminService iTrgAdminService) {
          this.iTrngAdminService = iTrgAdminService;
  }
  /**.
   * setter method for addVenueService
   * @param addVenueService { @link AddVenueService}
   */
  public final void setAddVenueServ(final IAddVenueService addVenueService) {
     this.addVenueServ = addVenueService; }
/**.
   * method to view the current trainings
   * @param request { @link HttpServletRequest}
   * @param response { @link HttpServletResponse}
   * @return ModelAndViewiTrainingAdminService
   */
  public final ModelAndView tstatus(final
        HttpServletRequest request , final HttpServletResponse response) {
      final ModelMap modelMap = new ModelMap();
      final HttpSession httpSession = request.getSession();
      try {
         final long userId = (Long) httpSession.getAttribute("userId");
         modelMap.addAttribute("history" ,
               iTrngAdminService.viewCurrentTrainings());
         modelMap.addAttribute("trainingScheduled" ,
               new Trainings());
         modelMap.addAttribute("location" ,
                 iTrngAdminService.retrieveLocations());
         modelMap.addAttribute("employeeDetails",
              iTrngAdminService.getEmployeeDetails(userId));
         return new ModelAndView("trainingAdminStatus" , modelMap);
         } catch (Exception e) {
           return new ModelAndView("error" , "msg" ,
                   "No records were found for your request");
           }
  }
  /**.
   * method to view history of all the trainings
   * @param request { @link HttpServletRequest}
   * @param response { @link HttpServletResponse}
   * @return ModelAndView
   */
  public final ModelAndView thistory(final
         HttpServletRequest request , final HttpServletResponse response) {
        final ModelMap modelMap = new ModelMap();
        final HttpSession httpSession = request.getSession();
        try {
           final long userId = (Long) httpSession.getAttribute("userId");
           modelMap.addAttribute("history" ,
                iTrngAdminService.viewHistory());
           modelMap.addAttribute("employees",
                 iTrngAdminService.retrieveEmployees());
           modelMap.addAttribute("location" ,
                 iTrngAdminService.retrieveLocations());
           modelMap.addAttribute("trainingScheduled" ,
              new Trainings());
           modelMap.addAttribute("employeeDetails",
                iTrngAdminService.getEmployeeDetails(userId));
           return new ModelAndView("viewHistory" , modelMap);
        } catch (Exception e) {
           return new ModelAndView("error" , "msg" ,
               "No records were found for your request");
        }
     }
  /**.
   * method to view the details of conducted trainings
   * @param request { @link HttpServletRequest}
   * @param response { @link HttpServletResponse}
   * @return ModelAndView
   */
  public final ModelAndView tviewDetails(final
       HttpServletRequest request , final HttpServletResponse response) {
      final HttpSession httpSession = request.getSession();
      try {
          final long userId = (Long) httpSession.getAttribute("userId");
          final String value = request.getParameter("enroll");
          final long trainingId = Long.parseLong(value);
          final ModelMap modelMap = new ModelMap();
          modelMap.addAttribute("history" ,
              iTrngAdminService.viewHistoryDetails(trainingId));
          modelMap.addAttribute("employeeDetails",
              iTrngAdminService.getEmployeeDetails(userId));
          modelMap.addAttribute("enrollment" , new Enrollment());
          return new ModelAndView("trainingAdminEmployeeDetails" , modelMap);
       } catch (Exception e) {
           return new ModelAndView("error" , "msg" ,
                 "No records were found for your request.");
       }
  }
  /**
   * method to add a venue.
   *
   * @param request { @link HttpServletRequest}
   * @param response { @link HttpServletResponse}
   * @return ModelAndView
   */
  public final ModelAndView taddVenue(final
    HttpServletRequest request , final HttpServletResponse response) {
      final ModelMap modelMap = new ModelMap();
      final HttpSession httpSession = request.getSession();
      try {
         final long userId = (Long) httpSession.getAttribute("userId");
         modelMap.addAttribute("employeeDetails",
              iTrngAdminService.getEmployeeDetails(userId));
         modelMap.addAttribute("venue" , new Location());
         return new ModelAndView("addVenue" , modelMap);
     } catch (Exception e) {
        return new ModelAndView("error" , "msg",
              "An error occurred while loading the page. Try again.");
     }
  }
  /**.
   * method to display an add venue form
   * @param request { @link HttpServletRequest}
   * @param response { @link HttpServletResponse}
   * @param venue { @link Location}
   * @return ModelAndView
   */
  public final ModelAndView taddVenueForm(final
        HttpServletRequest request , final HttpServletResponse response ,
       final Location venue) {
      final ModelMap modelMap = new ModelMap();
      final HttpSession httpSession = request.getSession();
      try {
        final long userId = (Long) httpSession.getAttribute("userId");
      addVenueServ.addVenueForm(venue);
          modelMap.addAttribute("venue" , new Location());
          modelMap.addAttribute("msg" , "New Venue has been added");
          modelMap.addAttribute("employeeDetails",
                iTrngAdminService.getEmployeeDetails(userId));
          return new ModelAndView("addVenue" , modelMap);
      } catch (Exception e) {
          return new ModelAndView("error" , "msg" ,
                 "Failed to add the venue. Try again.");
      }
    }
  /**.
   * method to retrieve list of users to be deleted.
   * @param request { @link HttpServletRequest}
   * @param response { @link HttpServletResponse}
   * @return ModelAndView
   */
  public final ModelAndView tdeleteFromList(final HttpServletRequest request,
       final HttpServletResponse response) {
        final HttpSession httpSession = request.getSession();
        try {
            final long userId = (Long) httpSession.getAttribute("userId");
            logger.info("control has reached delete user form  method ");
            final ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("userList", iTrngAdminService.getAllUser());
            modelMap.addAttribute("employeeDetails",
                  iTrngAdminService.getEmployeeDetails(userId));
            return new ModelAndView("trainingAdminDeleteUser", modelMap);
        } catch (Exception e) {
             return new ModelAndView("error", "msg", "Try again.");
        }
  }
  /**.
   * method to delete a user.
   * @param request { @link HttpServletRequest}
   * @param response { @link HttpServletResponse}
   * @return ModelAndView
   */
  public final ModelAndView tdeleteUser(final HttpServletRequest request,
        final HttpServletResponse response) {
        final HttpSession httpSession = request.getSession();
        try {
           final long userId = (Long) httpSession.getAttribute("userId");
           logger.info("control has reached delete user form  method ");
           final long employeeId = Long.parseLong(
                request.getParameter("enroll"));
           final int result = iTrngAdminService.deleteUser(employeeId);
           String message = "";
           if (result == 1) {
                message = "Deletion Successfull";
           } else {
                message = "Some Error Occurred";
           }
           final ModelMap modelMap = new ModelMap();
           modelMap.addAttribute("userList", iTrngAdminService.getAllUser());
           modelMap.addAttribute("employeeDetails",
                 iTrngAdminService.getEmployeeDetails(userId));
           modelMap.addAttribute("msg", message);
           return new ModelAndView("trainingAdminDeleteUser", modelMap);
        } catch (Exception e) {
           return new ModelAndView("error", "msg", "Try again.");
        }
      }
}