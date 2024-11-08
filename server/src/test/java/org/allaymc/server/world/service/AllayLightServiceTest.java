package org.allaymc.server.world.service;

import org.allaymc.api.server.Server;
import org.allaymc.api.world.Weather;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class AllayLightServiceTest {

    @Test
    void testBlockLight() {
        var dim = Server.getInstance().getWorldPool().getDefaultWorld().getOverWorld();
        var lightService = new AllayLightService(dim);
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                lightService.addChunk(
                        AllayUnsafeChunk
                                .builder()
                                .emptyChunk(x, z, dim.getDimensionInfo())
                                .toSafeChunk()
                );
            }
        }
        tickLightService(lightService);

        lightService.onBlockChange(0, 1, 0, 14, 0);
        tickLightService(lightService);
        assertEquals(14, lightService.getBlockLight(0, 1, 0));
        assertEquals(13, lightService.getBlockLight(1, 1, 0));
        assertEquals(13, lightService.getBlockLight(0, 1, 1));
        assertEquals(13, lightService.getBlockLight(-1, 1, 0));
        assertEquals(13, lightService.getBlockLight(0, 1, -1));
        assertEquals(13, lightService.getBlockLight(0, 2, 0));

        lightService.onBlockChange(2, 1, 0, 0, 15);
        tickLightService(lightService);
        assertEquals(0, lightService.getBlockLight(2, 1, 0));
        assertEquals(9, lightService.getBlockLight(3, 1, 0));


        lightService.onBlockChange(0, 1, 0, 0, 0);
        tickLightService(lightService);
        assertEquals(0, lightService.getBlockLight(0, 1, 0));
        assertEquals(0, lightService.getBlockLight(1, 1, 0));
        assertEquals(0, lightService.getBlockLight(0, 1, 1));
        assertEquals(0, lightService.getBlockLight(-1, 1, 0));
        assertEquals(0, lightService.getBlockLight(0, 1, -1));
        assertEquals(0, lightService.getBlockLight(0, 2, 0));
    }

    @Test
    void testSkyLight() {
        var dim = Server.getInstance().getWorldPool().getDefaultWorld().getOverWorld();
        var lightService = new AllayLightService(dim);
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                lightService.addChunk(
                        AllayUnsafeChunk
                                .builder()
                                .emptyChunk(x, z, dim.getDimensionInfo())
                                .toSafeChunk()
                );
            }
        }
        tickLightService(lightService);

        lightService.onBlockChange(0, 0, 0, 0, 15);
        tickLightService(lightService);
        assertEquals(15, lightService.getSkyLight(0, 1, 0));
        assertEquals(15, lightService.getSkyLight(0, 2, 0));
        assertEquals(15, lightService.getSkyLight(0, 100, 0));
        assertEquals(15, lightService.getSkyLight(0, 200, 0));

        lightService.onBlockChange(0, 0, 0, 0, 0);
        tickLightService(lightService);
        assertEquals(15, lightService.getSkyLight(0, -1, 0));

        lightService.onBlockChange(0, 0, 0, 0, 1);
        tickLightService(lightService);
        assertEquals(14, lightService.getSkyLight(0, 0, 0));
        assertEquals(13, lightService.getSkyLight(0, -1, 0));
        assertEquals(12, lightService.getSkyLight(0, -2, 0));
        assertEquals(11, lightService.getSkyLight(0, -3, 0));
    }

    static void tickLightService(AllayLightService lightService) {
        while (lightService.getQueuedUpdateCount() > 0) {
            lightService.tick();
        }
    }

    @Test
    void testCalculateSkylightReduction() {
        assertEquals(11, AllayLightService.calculateSkylightReduction(13670, Set.of(Weather.CLEAR)));
        assertEquals(11, AllayLightService.calculateSkylightReduction(22330, Set.of(Weather.CLEAR)));
        assertEquals(11, AllayLightService.calculateSkylightReduction(13670, Set.of(Weather.RAIN)));
        assertEquals(11, AllayLightService.calculateSkylightReduction(22330, Set.of(Weather.RAIN)));
        assertEquals(11, AllayLightService.calculateSkylightReduction(13670, Set.of(Weather.RAIN, Weather.THUNDER)));
        assertEquals(11, AllayLightService.calculateSkylightReduction(22330, Set.of(Weather.RAIN, Weather.THUNDER)));

        assertEquals(10, AllayLightService.calculateSkylightReduction(22331, Set.of(Weather.CLEAR)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(22491, Set.of(Weather.CLEAR)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13509, Set.of(Weather.CLEAR)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13669, Set.of(Weather.CLEAR)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(22331, Set.of(Weather.RAIN)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(22565, Set.of(Weather.RAIN)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13436, Set.of(Weather.RAIN)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13669, Set.of(Weather.RAIN)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(22331, Set.of(Weather.RAIN, Weather.THUNDER)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(22671, Set.of(Weather.RAIN, Weather.THUNDER)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13330, Set.of(Weather.RAIN, Weather.THUNDER)));
        assertEquals(10, AllayLightService.calculateSkylightReduction(13669, Set.of(Weather.RAIN, Weather.THUNDER)));
    }
}