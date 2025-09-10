package org.allaymc.server.entity;

import com.google.common.collect.BiMap;
import lombok.Getter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.data.EntityData;
import org.allaymc.api.entity.data.EntityFlag;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.utils.ReflectionUtils;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Objects;

/**
 * Metadata is a wrapper around the {@link EntityDataMap} in protocol library to allow us using {@link EntityData} and
 * {@link EntityFlag} in allay api.
 *
 * @author daoge_cmd
 */
public final class Metadata {
    private static final BiMap<EntityFlag, org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag> FLAG_MAP =
            ReflectionUtils.mapStaticFields(EntityFlag.class, org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag.class);
    private static final BiMap<EntityData<?>, org.cloudburstmc.protocol.bedrock.data.entity.EntityDataType<?>> DATA_MAP =
            ReflectionUtils.mapStaticFields(EntityData.class, org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes.class);

    @Getter
    private final EntityDataMap networkMetadata;

    public Metadata() {
        this.networkMetadata = new EntityDataMap();
    }

    public boolean get(EntityFlag flag) {
        return this.networkMetadata.getOrCreateFlags().contains(Objects.requireNonNull(FLAG_MAP.get(flag)));
    }

    @SuppressWarnings("unchecked")
    public <T> T get(EntityData<T> type) {
        return (T) fromNetwork(this.networkMetadata.get(Objects.requireNonNull(DATA_MAP.get(type))));
    }

    public void set(EntityFlag flag, boolean value) {
        this.networkMetadata.setFlag(Objects.requireNonNull(FLAG_MAP.get(flag)), value);
    }

    public <T> void set(EntityData<T> type, T data) {
        var networkType = Objects.requireNonNull(DATA_MAP.get(type));
        if (data != null) {
            this.networkMetadata.put(networkType, toNetwork(data));
        } else {
            this.networkMetadata.remove(networkType);
        }
    }

    private Object fromNetwork(Object data) {
        return switch (data) {
            // Null value is possible here
            case null -> null;
            case org.cloudburstmc.math.vector.Vector3f v -> new Vector3f(v.getX(), v.getY(), v.getZ());
            case org.cloudburstmc.math.vector.Vector3i v -> new Vector3i(v.getX(), v.getY(), v.getZ());
            case BlockDefinition b -> Registries.BLOCK_STATE_PALETTE.get(b.getRuntimeId());
            // TODO: particle type
            default -> data;
        };
    }

    private Object toNetwork(Object data) {
        return switch (data) {
            case Vector3fc v -> org.cloudburstmc.math.vector.Vector3f.from(v.x(), v.y(), v.z());
            case Vector3ic v -> org.cloudburstmc.math.vector.Vector3i.from(v.x(), v.y(), v.z());
            case BlockState b -> (BlockDefinition) b::blockStateHash;
            // TODO: particle type
            default -> data;
        };
    }
}
