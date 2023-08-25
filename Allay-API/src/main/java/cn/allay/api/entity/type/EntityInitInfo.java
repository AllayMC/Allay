package cn.allay.api.entity.type;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.entity.Entity;
import cn.allay.api.math.location.Location3d;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityInitInfo<T extends Entity> extends ComponentInitInfo {
    Location3d location();

    @Nullable
    NbtMap nbt();

    EntityType<T> getEntityType();

    void setEntityType(EntityType<T> entityType);

    class Simple<T extends Entity> implements EntityInitInfo<T> {
        private final Location3d location;
        @Nullable
        private final NbtMap nbt;
        @Getter
        @Setter
        private EntityType<T> entityType;

        public Simple(Location3d location) {
            this(location, null);
        }

        public Simple(Location3d location, @Nullable NbtMap nbt) {
            this.location = location;
            this.nbt = nbt;
        }

        @Override
        public Location3d location() {
            return location;
        }

        @Nullable
        @Override
        public NbtMap nbt() {
            return nbt;
        }
    }
}
