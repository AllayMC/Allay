package cn.allay.api.blockentity.init;

import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.math.position.Position3ic;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public class SimpleBlockEntityInitInfo<T extends BlockEntity> implements BlockEntityInitInfo<T> {

    private final Position3ic position;
    private final NbtMap nbt;
    @Nullable
    private final String customName;
    @Getter
    @Setter
    private BlockEntityType<T> blockEntityType;

    public SimpleBlockEntityInitInfo(Position3ic position, NbtMap nbt) {
        this(position, nbt, null);
    }

    public SimpleBlockEntityInitInfo(Position3ic position, NbtMap nbt, @Nullable String customName) {
        this.position = position;
        this.nbt = nbt;
        this.customName = customName;
    }

    @Override
    public Position3ic position() {
        return position;
    }

    @Override
    public NbtMap nbt() {
        return nbt;
    }

    @Override
    @Nullable
    public String customName() {
        return customName;
    }
}
