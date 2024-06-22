package org.allaymc.api.entity.metadata;

import lombok.Getter;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

import java.util.Objects;

/**
 * Allay Project 2023/7/9
 *
 * @author JukeboxMC | daoge_cmd
 */
@Getter
public class Metadata {

    private final EntityDataMap entityDataMap = new EntityDataMap();

    public <T> Metadata set(EntityDataType<T> entityData, T value) {
        var oldValue = this.entityDataMap.get(entityData);
        if (!Objects.equals(oldValue, value)) {
            this.entityDataMap.put(entityData, value);
        }
        return this;
    }

    public <T> T get(EntityDataType<T> entityData) {
        return this.entityDataMap.get(entityData);
    }

    public Metadata set(EntityFlag entityFlag, boolean value) {
        var oldValue = this.get(entityFlag);
        if (oldValue != value) {
            this.entityDataMap.setFlag(entityFlag, value);
        }
        return this;
    }

    public boolean get(EntityFlag entityFlag) {
        return this.entityDataMap.getOrCreateFlags().contains(entityFlag);
    }
}
