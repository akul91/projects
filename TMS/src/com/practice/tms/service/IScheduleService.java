package com.practice.tms.service;
import java.util.ArrayList;
import java.util.Date;

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
    public interface IScheduleService {
    /**
     * a method to insert.
     * @throws TMSException { @link TMSException}
     */
    void insert() throws TMSException;
    /**
     * method to retrieve the location.
     * @return { @link ArrayList<String>}
     * @throws TMSException { @link TMSException}
     */
    ArrayList<String> locationRetrieve() throws TMSException;
    /**
     * method to retrieve the date.
     * @return ArrayList<Date>
     * @throws TMSException { @link TMSException}
     */
    ArrayList<Date> dateRetrieve() throws TMSException;
    /**
     * a method to book the slot.
     * @param slot { @link String}
     * @param time { @link String}
     * @param date { @link String}
     * @return { @link ArrayList<Schedule>}
     * @throws TMSException { @link TMSException}
     */
    ArrayList<Schedule> retrieveSlot(String slot,
    String time, String date) throws TMSException;
    /**
     * method to  check whether the slot is booked or not.
     * @param date { @link ScheduleDao}
     * @param location { @link Location}
     * @param timeSlot { @link char}
     * @return { @link Schedule}
     * @throws TMSException { @link TMSException}
     */
    Schedule slotStore(String date,
    String location, char timeSlot) throws TMSException;
    /**
     * a method to get the request id.
     * @param trainReq { @link TrainingsRequested}
     * @return { @link long}
     * @throws TMSException { @link TMSException}
     */
    long finalStore(Trainings trainReq) throws TMSException;
    /**
     * a method for an employee to post a training.
     * @param trainingInfoObj { @link TrainingInformation}
     * @param trainId { @link int}
     * @param date { @link String}
     * @param location { @link String}
     * @param slot { @link String}
     * @return { @link long}
     * @throws TMSException { @link TMSException}
     */
    long employeePost(TrainingInformation trainingInfoObj,
    int trainId, String date,
    String location, String slot) throws TMSException;
    /**
     * a method to identify the category of the user.
     * @param parseLong { @link long}
     * @return { @link char}
     * @throws TMSException { @link TMSException}
     */
    char getCategory(long parseLong) throws TMSException;
    /**
     * a method for the project manager to post a training.
     * @param trainingInfoObj { @link TrainingInformation}
     * @param trainId { @link int}
     * @param date { @link String}
     * @param location { @link String}
     * @param slot { @link String}
     * @return { @link long}
     * @throws TMSException { @link TMSException}
     */
    long managerPost(TrainingInformation trainingInfoObj,
    int trainId, String date,
    String location, String slot) throws TMSException;
    /**
     *
     * @param date { @link String}
     * @param firstFrom { @link int}
     * @param firstTo { @link int}
     * @return ArrayList<Location>
     * @throws TMSException { @link TMSException}
     */
    ArrayList<Location> oneSlotLocationRetrieve(String date,
    int firstFrom, int firstTo) throws TMSException;
    /**
     *
     * @param trainName { @link String}
     * @param tech { @link String}
     * @param txtArea { @link String}
     * @param Tier { @link String}
     * @param listOfLocation { @link String}
     * @param date { @link date}
     * @param firstFrom { @link int}
     * @param firstTo { @link int}
     * @param userId { @link long}
     * @return array
     * @throws TMSException { @link TMSException}
     */
    int oneSlotDatabaseStore(String trainName , String tech,
    String txtArea, String Tier, String listOfLocation , Date date,
    int firstFrom, int firstTo, long userId) throws TMSException;
    /**
     *
     * @param trainingInfoId { @link int}
     * @param listOfLocation { @link String}
     * @param date { @link date}
     * @param firstFrom { @link int}
     * @param firstTo { @link int}
     * @param userId { @link long}
     * @return array
     * @throws TMSException { @link TMSException}
     */
    int oneSlotDatabaseStorerepost(int trainingInfoId, String listOfLocation
    , Date date,
    int firstFrom, int firstTo, long userId) throws TMSException;

}