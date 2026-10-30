package org.allaymc.api.world.physics;

/**
 * {@code HasLongId} exposes the long identifier used to identify an indexed
 * object. Spatial index wrappers use this value when locating entries for
 * updates or removal, so implementations must keep the identifier consistent
 * while the object remains indexed.
 *
 * @author daoge_cmd
 */
public interface HasLongId {
    /**
     * Returns the long identifier used to identify this indexed object.
     */
    long getLongId();
}
