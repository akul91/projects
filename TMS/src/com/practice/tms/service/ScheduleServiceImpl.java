package com.practice.tms.service;
import java.util.ArrayList;
import java.util.Date;

import com.practice.tms.dao.ScheduleDaoImpl;
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
    public class ScheduleServiceImpl implements IScheduleService {
    /**
     * declaration of scheduleDaoImpl.
     */
       private ScheduleDaoImpl scheduleDaoImpl;
       /**
        * setter injection for scheduleDaoImpl.
        * @param SchDaoImpl { @link ScheduleDaoImpl}
        */
        public final void setScheduleDaoImpl(
        final ScheduleDaoImpl SchDaoImpl) {
        this.scheduleDaoImpl = SchDaoImpl;
        }
        /**
         * a method to insert.
         * @throws TMSException { @link TMSException}
         */
        public final void insert() throws TMSException {
        try {
        scheduleDaoImpl.insert();
        } catch (Exception e) {
        throw new TMSException(e.toString());
         }
        }
        /**
         * method to retrieve the date.
         * @return ArrayList<Date>
         * @throws TMSException { @link TMSException}
         */
        public final ArrayList<Date> dateRetrieve() throws TMSException {
        try {
        return scheduleDaoImpl.dateRetrieve();
        } catch (Exception e) {
        throw new TMSException(e.toString());
        }
        }
        /**
         * method to retrieve the location.
         * @return { @link ArrayList<String>}
         * @throws TMSException { @link TMSException}
         */
        public final ArrayList<String> locationRetrieve() throws TMSException {
        try {
        return scheduleDaoImpl.locationRetrieve();
        } catch (Exception e) {
        throw new TMSException(e.toString());
        }
        }
        /**
         * a method to book the slot.
         * @param slot { @link String}
         * @param time { @link String}
         * @param date { @link String}
         * @return { @link ArrayList<Schedule>}
         * @throws TMSException { @link TMSException}
         */
        public final ArrayList<Schedule> retrieveSlot(final String slot,
        final String time, final String date) throws TMSException {
        try {
        return scheduleDaoImpl.retrieveSlot(slot, time, date);
        } catch (Exception e) {
        throw new TMSException(e.toString());
    }
        }
        /**
         * method to  check whether the slot is booked or not.
         * @param date { @link ScheduleDao}
         * @param location { @link Location}
         * @param timeSlot { @link char}
         * @return { @link Schedule}
         * @throws TMSException { @link TMSException}
         */
        public final Schedule slotStore(final String date,
        final String location,
        final char timeSlot) throws TMSException {
        try {
        return scheduleDaoImpl.slotStore(date, location, timeSlot);
        } catch (Exception e) {
        throw new TMSException(e.toString());
    }
        }
        /**
         * a method to get the request id.
         * @param trainReq { @link TrainingsRequested}
         * @return { @link long}
         * @throws TMSException { @link TMSException}
         */
        public final long finalStore(
        final Trainings trainReq)
        throws TMSException {
        try {
        return scheduleDaoImpl.finalStore(trainReq);
        } catch (Exception e) {
        throw new TMSException(e.toString());
        }
        }
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
        public final long employeePost(
            final TrainingInformation trainingInfoObj,
            final int trainId, final String date,
            final String location,
            final String slot) throws TMSException {
        try {
        return scheduleDaoImpl.employeePost(trainingInfoObj,
        trainId, date, location, slot);
        } catch (Exception e) {
        throw new TMSException(e.toString());
        }
        }
        /**
         * a method to identify the category of the user.
         * @param userId { @link long}
         * @return { @link char}
         * @throws TMSException { @link TMSException}
         */
        public final char getCategory(final long userId) throws TMSException {
        try {
        return scheduleDaoImpl.getCategory(userId);
        } catch (Exception e) {
        throw new TMSException(e.toString());
    }
        }
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
        public final long managerPost(
        final TrainingInformation trainingInfoObj,
        final int trainId, final String date,
        final String location, final String slot) throws TMSException {
        try {
        return scheduleDaoImpl.managerPost(trainingInfoObj, trainId, date,
        location, slot);
        } catch (Exception e) {
        throw new TMSException(e.toString());
        }
        }
        /**
         * @param date { @link String}
         * @param firstFrom { @link int}
         * @param firstTo { @link int}
         * @return ArrayList<Location>
         * @throws TMSException { @link TMSException}
         */
        public final ArrayList<Location> oneSlotLocationRetrieve(final String
        date , final int firstFrom, final int firstTo) throws TMSException {
        try {
        return scheduleDaoImpl.oneSlotLocationRetrieve(date, firstFrom,
        firstTo);
        } catch (Exception e) {
        throw new TMSException(e.toString());
        }
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
         * @return { @link int}
         * @throws TMSException { @link TMSException}
         */
        public final int oneSlotDatabaseStore(final String trainName
        , final String tech , final String txtArea , final String Tier
        , final String listOfLocation, final Date date,
        final int firstFrom, final int firstTo , final long userId)
        throws TMSException {
        try {
        return scheduleDaoImpl.oneSlotDatabaseStore(trainName, tech,
        txtArea, Tier, listOfLocation, date, firstFrom, firstTo,
        userId);
        } catch (Exception e) {
        throw new TMSException(e.toString());
        }
        }
        /**
         * @param trainingInfoId { @link int}
         * @param listOfLocation { @link String}
         * @param date { @link Date}
         * @param firstFrom { @link int}
         * @param firstTo { @link int}
         * @param userId { @link long}
         * @return { @link int}
         * @throws TMSException { @link TMSException}
         */
        public final int oneSlotDatabaseStorerepost(final int trainingInfoId
        , final String listOfLocation , final Date date,
        final int firstFrom, final int firstTo , final long userId)
        throws TMSException {
        try {
        return scheduleDaoImpl.oneSlotDatabaseStorerepost(trainingInfoId ,
        listOfLocation , date , firstFrom , firstTo , userId);
        } catch (Exception e) {
        throw new TMSException(e.toString());
        }
    }
    }