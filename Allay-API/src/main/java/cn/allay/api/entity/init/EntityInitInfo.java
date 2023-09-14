package cn.allay.api.entity.init;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.math.location.Location3f;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityInitInfo<T extends Entity> extends ComponentInitInfo {
    Location3f location();

    @Nullable
    NbtMap nbt();

    EntityType<T> getEntityType();

    void setEntityType(EntityType<T> entityType);

    static <T extends Entity> EntityInitInfo<T> of(Location3f location) {
        return new SimpleEntityInitInfo<>(location);
    }

    static <T extends Entity> EntityInitInfo<T> of(Location3f location, @Nullable NbtMap nbt) {
        return new SimpleEntityInitInfo<>(location, nbt);
    }
}
