package cn.allay.api.entity.metadata;

import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

import java.util.Objects;

/**
 * Allay Project 2023/7/9
 *
 * @author JukeboxMC | daoge_cmd
 */
public class Metadata {

    private final EntityDataMap entityDataMap = new EntityDataMap();

    public Metadata setByte(EntityDataType<Byte> entityData, byte value) {
        Byte oldValue = this.getByte(entityData);
        if (oldValue != value) {
            this.entityDataMap.put(entityData, value);
        }
        return this;
    }

    public Byte getByte(EntityDataType<Byte> entityData) {
        return this.entityDataMap.containsKey(entityData) ? this.entityDataMap.get(entityData) : 0;
    }

    public Metadata setLong(EntityDataType<Long> entityData, long value) {
        Long oldValue = this.getLong(entityData);
        if (oldValue != value) {
            this.entityDataMap.put(entityData, value);
        }
        return this;
    }

    public Long getLong(EntityDataType<Long> entityData) {
        return this.entityDataMap.containsKey(entityData) ? this.entityDataMap.get(entityData) : 0L;
    }

    public Metadata setShort(EntityDataType<Short> entityData, short value) {
        Short oldValue = this.getShort(entityData);
        if (oldValue != value) {
            this.entityDataMap.put(entityData, value);
        }
        return this;
    }

    public Short getShort(EntityDataType<Short> entityData) {
        return this.entityDataMap.containsKey(entityData) ? this.entityDataMap.get(entityData) : 0;
    }

    public Metadata setString(EntityDataType<String> entityData, String value) {
        String oldValue = this.getString(entityData);
        if (!Objects.equals(oldValue, value)) {
            this.entityDataMap.put(entityData, value);
        }
        return this;
    }

    public String getString(EntityDataType<String> entityData) {
        return this.entityDataMap.containsKey(entityData) ? this.entityDataMap.get(entityData) : "";
    }

    public Metadata setFloat(EntityDataType<Float> entityData, float value) {
        Float oldValue = this.getFloat(entityData);
        if (oldValue != value) {
            this.entityDataMap.put(entityData, value);
        }
        return this;
    }

    public Float getFloat(EntityDataType<Float> entityData) {
        return this.entityDataMap.containsKey(entityData) ? this.entityDataMap.get(entityData) : 0F;
    }

    public Metadata setInt(EntityDataType<Integer> entityData, int value) {
        Integer oldValue = this.getInt(entityData);
        if (oldValue != value) {
            this.entityDataMap.put(entityData, value);
        }
        return this;
    }

    public Integer getInt(EntityDataType<Integer> entityData) {
        return this.entityDataMap.containsKey(entityData) ? this.entityDataMap.get(entityData) : 0;
    }

    public Metadata setFlag(EntityFlag entityFlag, boolean value) {
        boolean oldValue = this.getFlag(entityFlag);
        if (oldValue != value) {
            this.entityDataMap.setFlag(entityFlag, value);
        }
        return this;
    }

    public boolean getFlag(EntityFlag entityFlag) {
        return this.entityDataMap.getOrCreateFlags().contains(entityFlag);
    }

    public EntityDataMap getEntityDataMap() {
        return this.entityDataMap;
    }
}
