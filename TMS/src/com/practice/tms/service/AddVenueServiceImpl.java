package com.practice.tms.service;

import com.practice.tms.dao.VenueDaoImpl;
import com.practice.tms.domain.Location;
import com.practice.tms.exception.TMSException;
    /**
     *
     *@author bgundavarapu
     *
     */
    public class AddVenueServiceImpl implements IAddVenueService {
    /**
     *declaring variable of type VenueDAOImpl implementation class.
     */
    private VenueDaoImpl venueDAOImpl;
    /**
     *setter injection of venueDAOImpl.
     * @param venueDAOImplObj { @link VenueDaoImpl}}
     */
    public final void setVenueDAOImpl(final VenueDaoImpl venueDAOImplObj) {
    this.venueDAOImpl = venueDAOImplObj;
    }
    /**
     *method to insert the location.
     * @param venue { @link Location}
     * @throws TMSException { @link TMSException}
     */
    public final void addVenueForm(final Location venue) throws TMSException {
    try {
    venueDAOImpl.saveVenue(venue);
    } catch (Exception e) {
    throw new TMSException(e.toString());
    }
    }
    }