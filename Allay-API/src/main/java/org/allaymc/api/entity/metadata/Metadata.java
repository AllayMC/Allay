package org.allaymc.api.entity.metadata;

import lombok.Getter;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
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

    public Metadata setNBT(EntityDataType<NbtMap> entityData, NbtMap value) {
        NbtMap oldValue = this.getNBT(entityData);
        if (!oldValue.equals(value)) {
            this.entityDataMap.put(entityData, value);
        }
        return this;
    }

    public NbtMap getNBT(EntityDataType<NbtMap> entityData) {
        return this.entityDataMap.containsKey(entityData) ? this.entityDataMap.get(entityData) : NbtMap.EMPTY;
    }

    public Vector3f getVector3f(EntityDataType<Vector3f> entityData) {
        return this.entityDataMap.containsKey(entityData) ? this.entityDataMap.get(entityData) : Vector3f.ZERO;
    }

    public Metadata setVector3f(EntityDataType<Vector3f> entityData, Vector3f value) {
        Vector3f oldValue = this.getVector3f(entityData);
        if (!oldValue.equals(value)) {
            this.entityDataMap.put(entityData, value);
        }
        return this;
    }
}
