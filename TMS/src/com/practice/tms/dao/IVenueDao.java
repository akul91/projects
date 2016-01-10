package com.practice.tms.dao;

import com.practice.tms.domain.Location;
import com.practice.tms.exception.TMSException;
/**
 *
 * @author jyothitamma
 *
 */
public interface IVenueDao {
/**.
* *method to insert the location
* @param venue {@link Location}
* @throws TMSException {@link TMSException}
*/
void saveVenue(Location venue) throws TMSException;
}