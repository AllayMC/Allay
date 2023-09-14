package cn.allay.api.entity;

import cn.allay.api.datastruct.aabbtree.HasAABB;
import cn.allay.api.datastruct.aabbtree.HasLongId;
import cn.allay.api.entity.component.base.EntityBaseComponent;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.location.Location3f;
import cn.allay.api.world.World;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBf;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface Entity extends
        EntityBaseComponent,
        HasAABB, HasLongId {

    @Override
    default AABBf copyOffsetAABBTo(AABBf dest) {
        var aabb = getOffsetAABB();
        dest.setMin(aabb.minX(), aabb.minY(), aabb.minZ());
        dest.setMax(aabb.maxX(), aabb.maxY(), aabb.maxZ());
        return dest;
    }

    static Entity fromNBT(World world, NbtMap nbt) {
        var identifier = new Identifier(nbt.getString("identifier"));
        var entityType = EntityTypeRegistry.getRegistry().get(identifier);
        if (entityType == null) {
            throw new IllegalArgumentException("Unknown entity type " + identifier);
        }
        //此处nbt中存储的Pos，Rotation将会覆盖location的值
        return entityType.createEntity(EntityInitInfo.of(new Location3f(0, 0, 0, world), nbt));
    }
}
