package com.practice.tms.service;

import com.practice.tms.domain.Location;
import com.practice.tms.exception.TMSException;
    /**
     *
     * @author bgundavarapu
     *
     */
    public interface IAddVenueService {
    /**
     *
     * @param venue { @link Location}}
     * @throws TMSException { @link TMSException}
     */
    void addVenueForm(final Location venue) throws TMSException;
    }
