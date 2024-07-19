package org.allaymc.api.registry.populator;

import org.allaymc.api.registry.Registry;

/**
 * Allay Project 2024/7/19
 *
 * @author daoge_cmd
 */
public interface RegistryPopulator<CONTENT> {
    <REGISTRY extends Registry<CONTENT>> void populate(REGISTRY registry);
}
