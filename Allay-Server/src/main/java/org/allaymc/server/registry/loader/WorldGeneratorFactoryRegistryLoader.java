package org.allaymc.server.registry.loader;

import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.server.world.generator.FlatNoiser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Allay Project 2024/7/20
 *
 * @author daoge_cmd
 */
public class WorldGeneratorFactoryRegistryLoader implements RegistryLoader<Void, Map<String, Function<String, WorldGenerator>>> {
    @Override
    public Map<String, Function<String, WorldGenerator>> load(Void $) {
        var map = new HashMap<String, Function<String, WorldGenerator>>();
        map.put("VOID", preset -> WorldGenerator.builder().name("VOID").preset(preset).build());
        map.put("FLAT", preset -> WorldGenerator.builder().name("FLAT").noisers(new FlatNoiser()).preset(preset).build());
        return map;
    }
}
