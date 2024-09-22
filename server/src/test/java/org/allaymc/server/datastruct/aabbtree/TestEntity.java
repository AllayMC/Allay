package org.allaymc.server.datastruct.aabbtree;

import lombok.Getter;
import org.allaymc.api.world.service.HasAABB;
import org.allaymc.api.world.service.HasLongId;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

/**
 * @author daoge_cmd
 */
@Getter
public class TestEntity implements HasAABB, HasLongId {

    private final float x, y, z;
    private final float width, height, length;
    private final int id;

    public TestEntity(int id, float x, float y, float width, float height) {
        this(id, x, y, 0, width, height, 0);
    }

    public TestEntity(int id, float x, float y, float z, float width, float height, float length) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    @Override
    public AABBfc getOffsetAABB() {
        var dest = new AABBf();
        dest.setMin(x, y, z);
        dest.setMax(x + width, y + height, z + length);
        return dest;
    }

    @Override
    public AABBfc getAABB() {
        return new AABBf(
                - width / 2f, 0, - length / 2f,
                width / 2f, height, length / 2f
        );
    }

    @Override
    public long getLongId() {
        return id;
    }
}
