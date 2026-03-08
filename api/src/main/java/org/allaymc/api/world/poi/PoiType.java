package org.allaymc.api.world.poi;

/**
 * Represents a Point of Interest type. Used to index "interesting" block positions
 * (portals, beds, job sites, etc.) per chunk for fast spatial queries.
 * <p>
 * Built-in POI types are available in {@link PoiTypes}.
 * Custom POI types can be registered via {@link org.allaymc.api.registry.Registries#POI_TYPES}.
 *
 * @author daoge_cmd
 */
public record PoiType(String name) {
}
