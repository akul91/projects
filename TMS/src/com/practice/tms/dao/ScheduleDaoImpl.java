package com.practice.tms.dao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.practice.tms.domain.Employee;
import com.practice.tms.domain.Location;
import com.practice.tms.domain.Schedule;
import com.practice.tms.domain.TrainingInformation;
import com.practice.tms.domain.Trainings;
import com.practice.tms.exception.TMSException;
    /**
     * 
     * @author bgundavarapu
     *
     */
    public class ScheduleDaoImpl implements IScheduleDao {
    /**
     *declaration of hibernate template.
     */
    private HibernateTemplate hibernateTemplate;
    /**
     * declaration of schedules.
     */
    private Set<Schedule> schedules = null;
    /**
     * setter  injection for sessionFactory.
     * @param sessionFactory {@link SessionFactory}
     */
    public final void setSessionFactory(final SessionFactory sessionFactory) {
    this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    /**
     *a method to insert.
     */
    public void insert() {
    }
    /**
     * method to retrieve the date.
     * @return ArrayList<Date>
     * @throws TMSException { @link TMSException}
     */
        @SuppressWarnings("unchecked")
        public final ArrayList<Date> dateRetrieve() throws TMSException {
            final ArrayList <Date> dateRetrieve = new ArrayList<Date>();
            List <Date> dateList = new ArrayList<Date>();
            try {
                dateList = hibernateTemplate.find("select e.date"
            + " from Schedule e"
            + " where date>curdate() group by e.date");
                final Iterator<Date> iterator = dateList.iterator();
                    while (iterator.hasNext()) {
                        dateRetrieve.add((Date) iterator.next());
                        }
                    } catch (Exception e) {
                throw new TMSException(e.toString());
                }
            return dateRetrieve;
            }
        /**
        * method to retrieve the location.
        * @return { @link ArrayList<String>}
        * @throws TMSException { @link TMSException}
        */
        @SuppressWarnings("unchecked")
        public final ArrayList<String> locationRetrieve() throws TMSException {
            List <Long> locationIdList = new ArrayList<Long>();
            final ArrayList <String>locationRetrieve = new ArrayList<String>();
            try {
                locationIdList =
                hibernateTemplate.find("select distinct e.locationId"
                + " from Schedule e");
                final Iterator<Long> iterator = locationIdList.iterator();
                while (iterator.hasNext()) {
                    final long locationId = iterator.next();
                    List<String> listLocation =
                    hibernateTemplate.find("select e.location"
                    + " from Location e where e.locationId = " + locationId);
                    Iterator<String> iteratorLocation = listLocation.iterator();
                    String location = iteratorLocation.next();
                    locationRetrieve.add(location);
                    }
                } catch (Exception e) {
                throw new TMSException(e.toString());
                }
            return locationRetrieve;
            }
        /**
         *a method to book the slot.
         *@param slot { @link String}
         *@param time { @link String}
         *@param date { @link String}
         *@return { @link ArrayList<Schedule>}
         *@throws TMSException { @link TMSException}
         */
        @SuppressWarnings("unchecked")
        public final ArrayList<Schedule> retrieveSlot(final String slot,
        final String time, final String date) throws TMSException {
             ArrayList <Schedule> locationList = new ArrayList<Schedule>();
             long locationId = 0;
             List <Long>listLocation = new ArrayList<Long>();
             List <Schedule>listSchedule = new ArrayList<Schedule>();
             try {
                  listLocation = hibernateTemplate.
                find("select e.locationId"
                  + " from Location e"
                  + " where e.location='" + slot + "'");
                  Iterator<Long> iterator = listLocation.iterator();
                  if (iterator.hasNext()) {
                   locationId = iterator.next();
                   }
                  listSchedule = hibernateTemplate.find("from Schedule e"
                   + " where e.date='" + date + "' and e.availability='e'");
                  Iterator<Schedule> iteratorSchedule = listSchedule.iterator();
                  while (iteratorSchedule.hasNext()) {
                   Schedule schedule = new Schedule();
                   schedule = iteratorSchedule.next();
                   if (locationId == schedule.getLocationId()) {
                   locationList.add(schedule);
                   }
                   }
                  } catch (Exception e) {
               throw new TMSException(e.toString());
               }
             return locationList;
             }
        /**
         *a method to book the slot.
         *@param locationName { @link String}
         *@param timeSlot { @link char}
         *@param date { @link String}
         *@return { @link Schedule}
         *@throws TMSException { @link TMSException}
         */
        @SuppressWarnings("unchecked")
        public final Schedule slotStore(final String date,
        final String locationName,
        final char timeSlot) throws TMSException {
        long locationId = 0;
        List <Long>listLocation = new ArrayList<Long>();
        List <Schedule>listSchedule = new ArrayList<Schedule>();
        try {
        listLocation = hibernateTemplate.find("select e.locationId"
        + " from Location e"
        + " where e.location='" + locationName + "'");
        Iterator<Long> iterator = listLocation.iterator();
        if (iterator.hasNext()) {
        locationId = iterator.next();
        }
        listSchedule = hibernateTemplate.find("from Schedule e"
        + " where e.date='" + date + "' and e.availability='e'");
        Iterator<Schedule> iteratorSchedule = listSchedule.iterator();
        while (iteratorSchedule.hasNext()) {
        Schedule schedule = new Schedule();
        schedule = iteratorSchedule.next();
        if (locationId == schedule.getLocationId()) {
        int slot = Integer.parseInt(String.valueOf(timeSlot));
        if (schedule.getTimings() == slot) {
        Schedule scheduleM = (Schedule) hibernateTemplate.merge(schedule);
        hibernateTemplate.saveOrUpdate(scheduleM);
        return scheduleM;
        }
        }
        }
        } catch (Exception e) {
        throw new TMSException(e.toString());
        }
        return new Schedule();
        }
        /**
         * a method to get the request id.
         * @param trainingsRequestedObj { @link TrainingsRequested}
         * @return { @link long}
         * @throws TMSException { @link TMSException}
         */
        public final long finalStore(
        final Trainings trainingsRequestedObj)
        throws TMSException {
        long requestId;
        try {
        hibernateTemplate.saveOrUpdate(trainingsRequestedObj);
       // requestId = trainingsRequestedObj.getScheduleId();
        } catch (Exception e) {
        throw new TMSException(e.toString());
        }
        return 0;
        }
        /**
         * a method for an employee to post a training.
         * @param trainingInformationObj { @link TrainingInformation}
         * @param id { @link int}
         * @param date { @link String}
         * @param location { @link String}
         * @param slot { @link String}
         * @return { @link long}
           @throws TMSException { @link TMSException}
           */
@SuppressWarnings("unchecked")
    public final long employeePost(
    final TrainingInformation trainingInformationObj,
        final int id, final String date,
        final String location, final String slot)
            throws TMSException {
    schedules = new HashSet<Schedule>();
    Schedule[] schedule = new Schedule[100];
    int k = 0;
    int slotChosen;
    long locationId = 0;
    long requestId = 0;
    List <Long>listLocation = new ArrayList<Long>();
    List <Schedule>listSchedule = new ArrayList<Schedule>();
    try {
    for (int i = 1; i < slot.length(); i++) {
        slotChosen = Integer.parseInt(String.valueOf(slot.charAt(i)));
        listLocation = hibernateTemplate.find("select e.locationId"
        + " from Location e where e.location='" + location + "'");
        Iterator<Long> iterator = listLocation.iterator();
        if (iterator.hasNext()) {
        locationId = iterator.next();
}
        listSchedule = hibernateTemplate.find("from Schedule e"
        + " where e.date='" + date + "' and e.availability='e' ");
        Iterator<Schedule> iteratorSchedule = listSchedule.iterator();
        while (iteratorSchedule.hasNext()) {
            schedule[k] = new Schedule();
            schedule[k] = iteratorSchedule.next();
            if (schedule[k].getLocationId() == locationId) {
            if (schedule[k].getTimings() == slotChosen) {
            hibernateTemplate.update(schedule[k]);
            schedules.add(schedule[k]);
            }
            }
        }
        ++k;
    }
    Trainings trainingsRequestedObj = new Trainings();
    trainingsRequestedObj.setTrainerId(id);
    trainingsRequestedObj.setStatus("IP");
    trainingsRequestedObj.setTrainingInformation(trainingInformationObj);
    hibernateTemplate.save(trainingsRequestedObj);
    requestId = trainingsRequestedObj.getTrainingId();
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return requestId;
    }
    /**
    * a method for the project manager to post a training.
    * @param trainingInformationObj { @link TrainingInformation}
    * @param id { @link int}
    * @param date { @link String}
    * @param location { @link String}
    * @param slot { @link String}
    * @return { @link long}
    * @throws TMSException { @link TMSException}
    */
    @SuppressWarnings("unchecked")
    public final long managerPost(
    final TrainingInformation trainingInformationObj,
    final int id, final String date,
    final String location,
    final String slot)
    throws TMSException {
    schedules = new HashSet<Schedule>();
    Schedule[] schedule = new Schedule[100];
    int k = 0;
    long locationId;
    long requestId;
    List <Long>listLocation = new ArrayList<Long>();
    List <Schedule>listSchedule = new ArrayList<Schedule>();
    try {
        for (int i = 1; i < slot.length(); i++) {
            int slotChosen = Integer.parseInt(String.valueOf(slot.charAt(i)));
            locationId = 0;
            listLocation = hibernateTemplate.find("select e.locationId"
            + " from Location e "
            + " where e.location='" + location + "'");
            Iterator<Long> iterator = listLocation.iterator();
            if (iterator.hasNext()) {
            locationId = iterator.next();
            }
            listSchedule = hibernateTemplate.find("from Schedule e "
            + "where e.date='" + date + "' and e.availability='e' ");
            Iterator<Schedule> iteratorSchedule = listSchedule.iterator();
            while (iteratorSchedule.hasNext()) {
            schedule[k] = new Schedule();
            schedule[k] = iteratorSchedule.next();
            if (schedule[k].getLocationId() == locationId) {
            if (schedule[k].getTimings() == slotChosen) {
            hibernateTemplate.update(schedule[k]);
            schedules.add(schedule[k]);
            }
            }
            }
            ++k;
        }
        Trainings trainingsRequestedObj = new Trainings();
        trainingsRequestedObj.setTrainerId(id);
        trainingsRequestedObj.setStatus("inprogress");
        trainingsRequestedObj.setTrainingInformation(trainingInformationObj);
        hibernateTemplate.save(trainingsRequestedObj);
        requestId = trainingsRequestedObj.getTrainingId();
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return requestId;
    }
    /**
     * a method to identify the category of the user.
     * @param userId { @link long}
     *@return { @link char}
     * @throws TMSException { @link TMSException}
     */
    @SuppressWarnings("unchecked")
    public final char getCategory(final long userId) throws TMSException {
    List <Employee> employee = new ArrayList<Employee>();
    try {
    employee = hibernateTemplate.find(""
    + " from Employee e where e.employeeId=" + userId);
    Iterator<Employee> iterator = employee.iterator();
    if (iterator.hasNext()) {
    Employee e = new Employee();
    e = (Employee) iterator.next();
    String category = e.getEmployeeCategory();
    return category.charAt(0);
    }
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    return 'e';
    }
    /**
     * @param date { @link String}
     * @param firstFrom { @link int}
     * @param firstTo { @link int}
     * @return ArrayList<Location>
     * @throws TMSException { @link TMSException}
     *
     */
    @Override
    @SuppressWarnings("unchecked")
    public final ArrayList<Location> oneSlotLocationRetrieve(final String date,
            final int firstFrom, final int firstTo) throws TMSException {
        ArrayList<Location> locationList = new ArrayList<Location>();
       try {
            int breakloop = 1;
            int code = slots(firstFrom, firstTo);
            String codeString = code + "";
            SimpleDateFormat formatter;
            Date dateCheck;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateCheck = (Date) formatter.parse(date);
            //(dateCheck);
            List<Location> list = hibernateTemplate.find("from Location");
            Iterator<Location> iterator = list.iterator();
            java.sql.Date sqlDate = new java.sql.Date(dateCheck.getTime());
            while (iterator.hasNext()) {
                System.out.println("codeString" + codeString);
                Location location = iterator.next();
                long locationId = location.getLocationId();
                List<Schedule> listValue =
                hibernateTemplate.find(" from Schedule s where s.date='"
                + sqlDate + " 00:00:00' and s.locationId="
                + location.getLocationId());
                Iterator<Schedule> iteratorValue = listValue.iterator();
                if (listValue.size() > 0) {
                    while (iteratorValue.hasNext()) {
                    long timing = iteratorValue.next().getTimings();
                    String timingRetrieved = String.valueOf(timing);
                    for (int timingDiv = 0;
                    timingDiv < timingRetrieved.length(); timingDiv++) {
                        String timingPart = Character.toString(
                        timingRetrieved.charAt(timingDiv));
                        System.out.println("timingpart" + timingPart);
                        if (codeString.indexOf(timingPart) >= 0) {
                breakloop = 0;
            }
            }
                    if (breakloop == 1) {
                        locationList.add(location);
                    }
                }
                } else {
                    locationList.add(location);
                }
                breakloop = 1;
            }
    } catch (Exception e) {
            e.printStackTrace();
            throw new TMSException(e.toString());
        }

        return locationList;
    }
    /**
     *
     * @param firstFrom { @link int}
     * @param firstTo { @link int}
     * @return int { @link int}
     */
    public final int slots(final int firstFrom, final int firstTo) {
    System.out.println("in");
    int[][] timings = new int[13][3];
    timings[0][0] = 9;
    timings[0][1] = 11;
    timings[0][2] = 1;
    timings[1][0] = 11;
    timings[1][1] = 1;
    timings[1][2] = 2;
    timings[2][0] = 2;
    timings[2][1] = 4;
    timings[2][2] = 3;
    timings[3][0] = 4;
    timings[3][1] = 6;
    timings[3][2] = 4;
    timings[4][0] = 9;
    timings[4][1] = 1;
    timings[4][2] = 12;
    timings[5][0] = 9;
    timings[5][1] = 4;
    timings[5][2] = 123;
    timings[6][0] = 9;
    timings[6][1] = 6;
    timings[6][2] = 1234;
    timings[7][0] = 11;
    timings[7][1] = 4;
    timings[7][2] = 23;
    timings[8][0] = 11;
    timings[8][1] = 6;
    timings[8][2] = 234;
    timings[9][0] = 2;
    timings[9][1] = 6;
    timings[9][2] = 34;
    timings[10][0] = 9;
    timings[10][1] = 11;
    timings[10][2] = 1;
    timings[11][0] = 9;
    timings[11][1] = 11;
    timings[11][2] = 1;
    timings[12][0] = 9;
    timings[12][1] = 11;
    timings[12][2] = 1;
    for (int i = 0; i < 13; i++) {
    if (timings[i][0] == firstFrom) {
    if (timings[i][1] == firstTo) {
    System.out.println("CODE: " + timings[i][2]);
    return timings[i][2];
    }
    }
    }
    return 0;
    }
    /**
     * @param trainName { @link String}
     * @param tech { @link String}
     * @param txtArea { @link String}
     * @param Tier { @link String}
     * @param listOfLocation { @link String}
     * @param date { @link Date}
     * @param firstFrom { @link int}
     * @param firstTo { @link int}
     * @param userId { @link long}
     * @return int
     * @throws TMSException { @link TMSException}
     */
    @SuppressWarnings("unchecked")
    public final int oneSlotDatabaseStore(final String trainName
    , final String tech , final String txtArea , final String Tier
    , final String listOfLocation , final Date date , final int firstFrom
    , final int firstTo , final long userId) throws TMSException {
        Schedule schedule = new Schedule();
        schedule.setDate(date);
        schedule.setLocationId(Integer.parseInt(listOfLocation));
        int code = slots(firstFrom, firstTo);
        schedule.setTimings(code);
        String technology;
        if (tech.equals("1")) {
        technology = "Java";
        } else if (tech.equals("2")) {
        technology = "SAP";
        } else if (tech.equals("3")) {
        technology = "Cloud Computing";
        } else if (tech.equals("4")) {
        technology = "Web Designing";
        } else if (tech.equals("5")) {
        technology = "Scripting Languages";
        } else if (tech.equals("6")) {
        technology = ".Net";
        } else {
        technology = tech;
        }
        String tierModified = "";
        for (int insert = 1; insert < Tier.length(); insert++) {
        System.out.println(Tier.charAt(insert));
        tierModified = tierModified + Tier.charAt(insert);
        }
        TrainingInformation trainingInformationObj = new TrainingInformation();
        trainingInformationObj.setTrainingProgram(trainName);
        trainingInformationObj.setTier(tierModified);
        trainingInformationObj.setTrainingDescription(txtArea);
        trainingInformationObj.setTechnology(technology);
        Trainings trainingObj = new Trainings();
        trainingObj.setSchedule(schedule);
        trainingObj.setTrainingInformation(trainingInformationObj);
        trainingObj.setTrainerId(userId);
        if (getCategory(userId) == 'e') {
        trainingObj.setStatus("Waiting For Approval");
        } else {
        trainingObj.setStatus("InProgress");
        }
        hibernateTemplate.save(trainingObj);
        return 1;
    }
    /**
     *
     * @param trainingInfoId { @link int}
     * @param listOfLocation { @link String}
     * @param date { @link Date}
     * @param firstFrom { @link int}
     * @param firstTo { @link int}
     * @param userId { @link long}
     * @return int
     * @throws TMSException { @link TMSException}
     */
    @SuppressWarnings("unchecked")
    public final int oneSlotDatabaseStorerepost(final int trainingInfoId
    , final String listOfLocation , final Date date , final int firstFrom ,
    final int firstTo, final long userId) throws TMSException {
    Schedule schedule = new Schedule();
    schedule.setDate(date);
    schedule.setLocationId(Integer.parseInt(listOfLocation));
    int code = slots(firstFrom, firstTo);
    schedule.setTimings(code);
    String technology;
    List<TrainingInformation> list = hibernateTemplate.find(
    "from TrainingInformation where trainingInformationId =" + trainingInfoId);
    Iterator<TrainingInformation> iterator = list.iterator();
    hibernateTemplate.save(schedule);
    TrainingInformation trainingInformationObj = iterator.next();
    Trainings trainingObj = new Trainings();
    trainingObj.setSchedule(schedule);
    trainingObj.setTrainingInformation(trainingInformationObj);
    trainingObj.setTrainerId(userId);
    if (getCategory(userId) == 'e') {
    trainingObj.setStatus("Waiting For Approval");
    } else {
    trainingObj.setStatus("InProgress");
    }
    hibernateTemplate.save(trainingObj);
    return 1;
    }
    }