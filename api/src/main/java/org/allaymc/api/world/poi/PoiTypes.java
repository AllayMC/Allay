package org.allaymc.api.world.poi;

/**
 * {@code PoiTypes} provides built-in point-of-interest type instances used by
 * chunk indexing and registry associations. The current catalog contains the
 * Nether portal category; blocks receive that category through the
 * point-of-interest registry rather than through this static holder alone.
 *
 * @author daoge_cmd
 */
public final class PoiTypes {
    /**
     * {@code NETHER_PORTAL} identifies indexed Nether portal positions and
     * supplies the stable name written by the point-of-interest storage codec.
     */
    public static final PoiType NETHER_PORTAL = new PoiType("NETHER_PORTAL");

    /**
     * Prevents instances of this static built-in point-of-interest type
     * catalog.
     */
    private PoiTypes() {}
}
