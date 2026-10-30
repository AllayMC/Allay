package org.allaymc.api.world.poi;

/**
 * {@code PoiType} represents a named kind of point of interest stored at chunk-local positions.
 * Built-in types are available in {@link PoiTypes}; block-to-type associations
 * are registered through {@link org.allaymc.api.registry.Registries#POI_TYPES},
 * and record equality compares names rather than object identity.
 *
 * @param name persistent point-of-interest type name
 *
 * @author daoge_cmd
 */
public record PoiType(String name) {
}
