package org.allaymc.api.math;

import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Cool_Loong
 */
@ExtendWith({MockitoExtension.class})
public class MathTest {
    @Mock
    Dimension dim;

    @Test
    void testPosition() {
        var pos = new Position3i(1, 1, 1, dim);
        assertNotNull(pos.dimension());
        assertEquals(1, pos.x());
        assertEquals(1, pos.y());
        assertEquals(1, pos.z());
        pos.set(2, 3, 4);
        assertEquals(2, pos.x());
        assertEquals(3, pos.y());
        assertEquals(4, pos.z());
    }

    @Test
    void testLocation() {
        var loc = new Location3i(1, 1, 1, dim);
        assertNotNull(loc.dimension());
        assertEquals(0d, loc.yaw());
        assertEquals(0d, loc.pitch());
        assertEquals(1, loc.x());
        assertEquals(1, loc.y());
        assertEquals(1, loc.z());
        loc.set(2, 3, 4);
        assertEquals(2, loc.x());
        assertEquals(3, loc.y());
        assertEquals(4, loc.z());
        loc.setPitch(3);
        loc.setYaw(2);
        assertEquals(3d, loc.pitch());
        assertEquals(2d, loc.yaw());
    }
}
