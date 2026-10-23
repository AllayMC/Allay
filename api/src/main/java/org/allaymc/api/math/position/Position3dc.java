package org.allaymc.api.math.position;

import org.allaymc.api.world.Dimension;
import org.joml.Vector3dc;

/**
 * {@code Position3dc} exposes double-precision coordinates and an associated dimension.
 * This interface supplies a read-only access surface, but implementations may remain
 * mutable; retaining the interface does not freeze coordinates or keep a weakly referenced
 * dimension alive.
 * @author Cool_Loong
 */
public interface Position3dc extends Vector3dc {
    /**
     * Returns the dimension currently associated with these coordinates. An implementation
     * using a weak dimension reference may return {@code null} after its referent is
     * cleared or when no dimension was supplied.
     */
    Dimension dimension();
}
