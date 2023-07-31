package cn.allay.server.math;

import cn.allay.api.math.Location3i;
import cn.allay.api.math.Position3i;
import cn.allay.api.world.World;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Allay Project 7/31/2023
 *
 * @author Cool_Loong
 */
@ExtendWith({MockitoExtension.class})
public class MathTest {
    @Mock
    World level;

    @Test
    void position() {
        var pos = new Position3i(1, 1, 1, level);
        assertNotNull(pos.world());
        assertEquals(1, pos.x());
        assertEquals(1, pos.y());
        assertEquals(1, pos.z());
        pos.set(2, 3, 4);
        assertEquals(2, pos.x());
        assertEquals(3, pos.y());
        assertEquals(4, pos.z());
    }

    @Test
    void location() {
        var loc = new Location3i(1, 1, 1, level);
        assertNotNull(loc.world());
        assertEquals(0d, loc.yaw());
        assertEquals(0d, loc.headYaw());
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
        loc.setHeadYaw(1);
        assertEquals(3d, loc.pitch());
        assertEquals(2d, loc.yaw());
        assertEquals(1d, loc.headYaw());
    }
}
