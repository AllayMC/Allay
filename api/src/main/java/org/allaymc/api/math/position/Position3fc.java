package org.allaymc.api.math.position;

import org.allaymc.api.world.Dimension;
import org.joml.Vector3fc;

/**
 * {@code Position3fc} exposes single-precision coordinates and an associated dimension.
 * This interface supplies a read-only access surface, but implementations may remain
 * mutable; retaining the interface does not freeze coordinates or keep a weakly referenced
 * dimension alive.
 * @author Cool_Loong
 */
public interface Position3fc extends Vector3fc {
    /**
     * Returns the dimension currently associated with these coordinates. An implementation
     * using a weak dimension reference may return {@code null} after its referent is
     * cleared or when no dimension was supplied.
     */
    Dimension dimension();
}
