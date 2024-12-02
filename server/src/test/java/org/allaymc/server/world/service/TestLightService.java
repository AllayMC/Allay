package org.allaymc.server.world.service;

import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.Weather;
import org.allaymc.api.world.chunk.Chunk;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public class TestLightService extends AllayLightService {

    public TestLightService() {
        super(DimensionInfo.OVERWORLD, () -> 0, () -> Set.of(Weather.CLEAR));
    }

    @Override
    public void onChunkLoad(Chunk chunk) {}

    @Override
    public void onChunkUnload(Chunk chunk) {}

    @Override
    public void onBlockChange(int x, int y, int z, int lightEmission, int lightDampening) {}
}
