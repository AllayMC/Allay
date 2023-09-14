package cn.allay.api.entity.init;

import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.math.location.Location3f;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/9/14
 *
 * @author Cool_Loong
 */
class SimpleEntityInitInfo<T extends Entity> implements EntityInitInfo<T> {
    private final Location3f location;
    @Nullable
    private final NbtMap nbt;
    @Getter
    @Setter
    private EntityType<T> entityType;

    public SimpleEntityInitInfo(Location3f location) {
        this(location, null);
    }

    public SimpleEntityInitInfo(Location3f location, @Nullable NbtMap nbt) {
        this.location = location;
        this.nbt = nbt;
    }

    @Override
    public Location3f location() {
        return location;
    }

    @Nullable
    @Override
    public NbtMap nbt() {
        return nbt;
    }
}
