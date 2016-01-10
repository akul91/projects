package com.practice.tms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author kbharathi
 *
 */
   @Entity
   @Table(name = "Location")
   public class Location {
      /**
       * declaration of locationId.
       */
   private long locationId;
   /**
    *declaration of location.
    */
   private String location; // (Room Name/Room Number)
   /**
    * declaration of floor.
    */
   private String floor;
   /**
    *
    * @return locationId { @link long}
    */
   @Id
   @GeneratedValue
   @Column(name = "locationId")
   public final long getLocationId() {
       return locationId;
   }
   /**
    *
    * @return location { @link String}
    */
   @Column (name = "location")
   public final String getLocation() {
       return location;
   }
   /**
    * @return floor { @link String}
    */
   @Column (name = "floor")
   public final String getFloor() {
       return floor;
   }
   /**
    *
    * @param locatId { @link String}
    */
   public final void setLocationId(final long locatId) {
       this.locationId = locatId;
   }
   /**
    *
    * @param loc { @link String}
    */
   public final void setLocation(final String loc) {
        this.location = loc;
   }
   /**
    *
    * @param flr { @link String}
    */
   public final void setFloor(final String flr) {
       this.floor = flr;
   }
   }