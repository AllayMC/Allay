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
     * @param entityData The type of data to set
     * @param value      The value to set
     * @param <T>        The type of the value
     *
     * @return This Metadata instance
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
     * @param entityData The type of data to get
     * @param <T>        The type of the value
     *
     * @return The value
     */
    public <T> T get(EntityDataType<T> entityData) {
        return this.entityDataMap.get(entityData);
    }

    /**
     * Checks whether a value is set for the specified entity data type.
     *
     * @param <T>        the type of the value
     * @param entityData the entity data type to check
     *
     * @return {@code true} if a value is present, {@code false} otherwise.
     */
    public <T> boolean has(EntityDataType<T> entityData) {
        return this.entityDataMap.containsKey(entityData);
    }

    /**
     * Sets a flag in the metadata.
     *
     * @param entityFlag The flag to set
     * @param value      The value to set
     *
     * @return This Metadata instance
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
     * @param entityFlag The flag to get
     *
     * @return The value of the flag
     */
    public boolean get(EntityFlag entityFlag) {
        return this.entityDataMap.getOrCreateFlags().contains(entityFlag);
    }

    /**
     * Checks whether the specified entity flag is present.
     *
     * @param entityFlag the flag to check
     *
     * @return {@code true} if the flag is present, {@code false} otherwise.
     */
    public boolean has(EntityFlag entityFlag) {
        return this.entityDataMap.getOrCreateFlags().contains(entityFlag);
    }
}
