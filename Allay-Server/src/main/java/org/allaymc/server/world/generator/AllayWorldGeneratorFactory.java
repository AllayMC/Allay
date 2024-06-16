package org.allaymc.server.world.generator;

import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.WorldGeneratorFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Allay Project 2024/2/3
 *
 * @author daoge_cmd
 */
public class AllayWorldGeneratorFactory extends SimpleMappedRegistry<String, Function<String, WorldGenerator>, Map<String, Function<String, WorldGenerator>>> implements WorldGeneratorFactory {
    public AllayWorldGeneratorFactory() {
        super(null, unused -> new ConcurrentHashMap<>());
        init();
    }

    protected void init() {
        register("VOID", preset -> AllayWorldGenerator.builder().name("VOID").preset(preset).build());
        register("FLAT", preset -> AllayWorldGenerator.builder().name("FLAT").noisers(new FlatNoiser()).preset(preset).build());
        // TODO: Pass preset to je generator loader
        register("JEGEN_OVERWORLD", preset -> JeGeneratorLoader.getJeGenerator(DimensionInfo.OVERWORLD));
        register("JEGEN_NETHER", preset -> JeGeneratorLoader.getJeGenerator(DimensionInfo.NETHER));
        register("JEGEN_END", preset -> JeGeneratorLoader.getJeGenerator(DimensionInfo.THE_END));
    }
}
