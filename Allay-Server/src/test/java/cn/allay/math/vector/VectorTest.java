package cn.allay.math.vector;

import cn.allay.api.level.Level;
import cn.allay.api.math.location.Loc;
import cn.allay.api.math.position.Pos;
import cn.allay.api.math.vector.Vec3;
import net.bytebuddy.ByteBuddy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
class VectorTest {

    static Level mockLevel;

    static {
        try {
            mockLevel = new ByteBuddy()
                    .subclass(Level.class)
                    .make()
                    .load(VectorTest.class.getClassLoader())
                    .getLoaded()
                    .newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void vector() {
        var v3 = Vec3.of(1, 1, 1);
        assertEquals(1, v3.getX());
        assertEquals(1, v3.getY());
        assertEquals(1, v3.getZ());
        v3.setX(2);
        v3.setY(3);
        v3.setZ(4);
        assertEquals(2, v3.getX());
        assertEquals(3, v3.getY());
        assertEquals(4, v3.getZ());
    }

    @Test
    void position() {
        var pos = Pos.of(1, 1, 1, mockLevel);
        assertNotNull(pos.getLevel());
        assertEquals(1, pos.getX());
        assertEquals(1, pos.getY());
        assertEquals(1, pos.getZ());
        pos.setX(2);
        pos.setY(3);
        pos.setZ(4);
        assertEquals(2, pos.getX());
        assertEquals(3, pos.getY());
        assertEquals(4, pos.getZ());
    }

    @Test
    void location() {
        var loc = Loc.of(1, 1, 1, mockLevel);
        assertNotNull(loc.getLevel());
        assertEquals(0d, loc.getYaw());
        assertEquals(0d, loc.getHeadYaw());
        assertEquals(0d, loc.getPitch());
        assertEquals(1, loc.getX());
        assertEquals(1, loc.getY());
        assertEquals(1, loc.getZ());
        loc.setX(2);
        loc.setY(3);
        loc.setZ(4);
        assertEquals(2, loc.getX());
        assertEquals(3, loc.getY());
        assertEquals(4, loc.getZ());
    }
}