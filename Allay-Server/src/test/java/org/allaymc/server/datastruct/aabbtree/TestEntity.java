package org.allaymc.server.datastruct.aabbtree;

import org.allaymc.api.world.service.HasAABB;
import org.allaymc.api.world.service.HasLongId;
import org.joml.primitives.AABBf;

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
    public AABBf copyOffsetAABBTo(AABBf dest) {
        if (dest == null) {
            dest = new AABBf();
        }
        dest.setMin(x, y, z);
        dest.setMax(x + width, y + height, z + length);
        return dest;
    }

    @Override
    public long getLongId() {
        return id;
    }
}
