package org.allaymc.api.entity.metadata;

import lombok.Getter;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

import java.util.Objects;

/**
 * @author JukeboxMC | daoge_cmd
 */
@Getter
public class Metadata {

    private final EntityDataMap entityDataMap = new EntityDataMap();

    /**
     * Sets a value in the metadata.
     *
     * @param entityData The type of data to set.
     * @param value      The value to set.
     * @param <T>        The type of the value.
     *
     * @return This Metadata instance.
     */
    public <T> Metadata set(EntityDataType<T> entityData, T value) {
        var oldValue = this.entityDataMap.get(entityData);
        if (!Objects.equals(oldValue, value)) {
            this.entityDataMap.put(entityData, value);
        }
        return this;
    }

    /**
     * Gets a value from the metadata.
     *
     * @param entityData The type of data to get.
     * @param <T>        The type of the value.
     *
     * @return The value.
     */
    public <T> T get(EntityDataType<T> entityData) {
        return this.entityDataMap.get(entityData);
    }

    /**
     * Sets a flag in the metadata.
     *
     * @param entityFlag The flag to set.
     * @param value      The value to set.
     *
     * @return This Metadata instance.
     */
    public Metadata set(EntityFlag entityFlag, boolean value) {
        var oldValue = this.get(entityFlag);
        if (oldValue != value) {
            this.entityDataMap.setFlag(entityFlag, value);
        }
        return this;
    }

    /**
     * Gets a flag from the metadata.
     *
     * @param entityFlag The flag to get.
     *
     * @return The value of the flag.
     */
    public boolean get(EntityFlag entityFlag) {
        return this.entityDataMap.getOrCreateFlags().contains(entityFlag);
    }
}
