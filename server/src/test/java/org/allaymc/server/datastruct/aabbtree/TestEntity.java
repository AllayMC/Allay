package org.allaymc.server.datastruct.aabbtree;

import lombok.Getter;
import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * {@code TestEntity} represents fixed identified boxes for AABB tree tests and benchmarks.
 * Offset bounds use the supplied minimum corner, while local bounds center horizontal
 * dimensions around zero; every bounds accessor allocates a new box.
 * @author daoge_cmd
 */
@Getter
public class TestEntity implements HasAABB, HasLongId {

    /**
     * {@code x}, {@code y}, and {@code z} define the minimum offset-box corner in test
     * coordinates. {@code x} is the horizontal X minimum, {@code y} is the vertical
     * minimum, and {@code z} is the horizontal Z minimum.
     */
    private final double x, y, z;
    /**
     * {@code width}, {@code height}, and {@code length} define the box dimensions in test
     * coordinate units. {@code width} spans X, {@code height} spans Y, and {@code length}
     * spans Z in both bounds accessors.
     */
    private final double width, height, length;
    /**
     * Stable identifier of this test box.
     */
    private final int id;

    /**
     * Creates an identified fixed box from the supplied minimum corner and dimensions. The
     * two-dimensional overload uses zero for {@code z} and {@code length}, producing a
     * zero-depth box in the XY plane.
     */
    public TestEntity(int id, double x, double y, double width, double height) {
        this(id, x, y, 0, width, height, 0);
    }

    /**
     * Creates an identified fixed box from the supplied minimum corner and dimensions. The
     * two-dimensional overload uses zero for {@code z} and {@code length}, producing a
     * zero-depth box in the XY plane.
     */
    public TestEntity(int id, double x, double y, double z, double width, double height, double length) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    /**
     * Returns a newly allocated box from the supplied corner and dimensions.
     */
    @Override
    public AABBdc getOffsetAABB() {
        var dest = new AABBd();
        dest.setMin(x, y, z);
        dest.setMax(x + width, y + height, z + length);
        return dest;
    }

    /**
     * Returns a newly allocated local box centered horizontally around zero.
     */
    @Override
    public AABBdc getAABB() {
        // Half-width and half-length place the local origin at the horizontal center.
        return new AABBd(
                -width / 2, 0, -length / 2,
                width / 2, height, length / 2
        );
    }

    /**
     * Returns the stable fixture identifier used by tree object keys.
     */
    @Override
    public long getLongId() {
        return id;
    }
}
