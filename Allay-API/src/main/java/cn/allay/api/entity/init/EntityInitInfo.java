package cn.allay.api.entity.init;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.math.location.Location3f;
import cn.allay.api.world.World;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityInitInfo<T extends Entity> extends ComponentInitInfo {
    World world();
    NbtMap nbt();

    EntityType<T> getEntityType();

    void setEntityType(EntityType<T> entityType);
}
