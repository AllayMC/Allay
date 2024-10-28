package org.allaymc.server.world.service;

import org.allaymc.api.world.Weather;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
class AllayLightServiceTest {

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