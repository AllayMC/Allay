package org.allaymc.api.world.biome;

/**
 * {@code BiomeTag} represents a named category attached to biome metadata and encoded in biome
 * definitions. Record equality compares the stored name, allowing climate
 * classification and tag lookup to recognize equivalent tag objects without
 * requiring the same instance.
 *
 * @param name biome category name
 *
 * @author daoge_cmd
 */
public record BiomeTag(String name) {
}
