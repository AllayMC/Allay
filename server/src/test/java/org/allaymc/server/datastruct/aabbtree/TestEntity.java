package org.allaymc.server.datastruct.aabbtree;

import lombok.Getter;
import org.allaymc.api.world.physics.HasAABB;
import org.allaymc.api.world.physics.HasLongId;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author daoge_cmd
 */
@Getter
public class TestEntity implements HasAABB, HasLongId {

    private final double x, y, z;
    private final double width, height, length;
    private final int id;

    public TestEntity(int id, double x, double y, double width, double height) {
        this(id, x, y, 0, width, height, 0);
    }

    public TestEntity(int id, double x, double y, double z, double width, double height, double length) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    @Override
    public AABBdc getOffsetAABB() {
        var dest = new AABBd();
        dest.setMin(x, y, z);
        dest.setMax(x + width, y + height, z + length);
        return dest;
    }

    @Override
    public AABBdc getAABB() {
        return new AABBd(
                -width / 2, 0, -length / 2,
                width / 2, height, length / 2
        );
    }

    @Override
    public long getLongId() {
        return id;
    }
}
