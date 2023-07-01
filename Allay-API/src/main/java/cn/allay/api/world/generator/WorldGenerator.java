package cn.allay.api.world.generator;

import cn.allay.api.world.WorldType;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
public interface WorldGenerator {

    void generate(LimitedWorldRegion region);

    WorldType getGeneratorWorldType();
}
