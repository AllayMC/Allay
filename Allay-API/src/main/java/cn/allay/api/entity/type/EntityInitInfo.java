package cn.allay.api.entity.type;

import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.entity.Entity;
import cn.allay.api.math.Location3d;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public interface EntityInitInfo<T extends Entity> extends ComponentInitInfo {
    Location3d location();

    EntityType<T> getEntityType();

    void setEntityType(EntityType<T> entityType);

    final class Simple<T extends Entity> implements EntityInitInfo<T> {
        private final Location3d location;
        @Getter
        @Setter
        private EntityType<T> entityType;

        public Simple(Location3d location) {
            this.location = location;
        }

        @Override
        public Location3d location() {
            return location;
        }
    }
}
