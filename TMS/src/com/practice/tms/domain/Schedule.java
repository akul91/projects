package com.practice.tms.domain;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author jyothitamma.
 *
 */
    @Entity
    @Table(name = "Schedule")
    public class Schedule {
    /**
     * declaration of scheduleId.
     */
    private long scheduleId;
    /**
     * declaration of date.
     */
    private Date date;
    /**
     * declaration of timings.
     */
    private int timings;
    /**
     * declaration of locationId.
     */
    private long locationId; //one to one relationship with the location.
    /**
     * getter of scheduleId.
     * @return scheduleId {@link long}
     */
    @Id // primary key
    @GeneratedValue // for auto generation
    @Column(name = "scheduleId")
    public final long  getScheduleId() {
    return scheduleId;
    }
    /**
     * getter of locationId.
     * @return locationId {@link long}
     */
    @Column (name = "locationId")
    public final long getLocationId() {
    return locationId;
    }
    /**
     * getter of date.
     * @return date {@link Date}
     */
    @Column (name = "date")
    public final Date getDate() {
    return date;
    }
    /**
     * getter of timings.
     * @return timings {@link long}
     */
    @Column (name = "timings")
    public final int getTimings() {
    return timings;
    }
    /**
     * setter of scheduleId.
     * @param schedId {@link long}
     */
    public final void setScheduleId(final long schedId) {
    this.scheduleId = schedId;
    }
    /**
     * setter of locationId.
     * @param locateId {@link long}
     */
    public final void setLocationId(final long locateId) {
    this.locationId = locateId;
    }
    /**
     * setter of date.
     * @param dte {@link Date}
     */
    public  final void setDate(final Date dte) {
    this.date = dte;
    }
    /**
     * setter of timings.
     * @param time {@link int}
     */
    public final void setTimings(final int time) {
    this.timings = time;
    }
}