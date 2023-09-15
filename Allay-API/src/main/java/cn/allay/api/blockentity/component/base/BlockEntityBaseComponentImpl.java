package cn.allay.api.blockentity.component.base;

import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.init.BlockEntityInitInfo;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.position.Position3i;
import cn.allay.api.math.position.Position3ic;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BlockEntityDataPacket;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public class BlockEntityBaseComponentImpl<T extends BlockEntity> implements BlockEntityBaseComponent {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_base_component");

    protected BlockEntityType<T> blockEntityType;
    protected Position3ic position;
    protected String customName = "";

    public BlockEntityBaseComponentImpl(BlockEntityInitInfo<T> initInfo) {
        this.blockEntityType = initInfo.getBlockEntityType();
        this.position = initInfo.position();
        if (initInfo.customName() != null) customName = initInfo.customName();
        var nbt = initInfo.nbt();
        if (nbt != null) load(nbt);
    }

    @Override
    @Impl
    public BlockEntityType<? extends BlockEntity> getBlockEntityType() {
        return blockEntityType;
    }

    @Override
    @Impl
    public Position3ic getPosition() {
        return position;
    }

    @Override
    @Impl
    public NbtMap save() {
        return NbtMap.builder()
                .putString("id", blockEntityType.getBlockEntityId())
                .putInt("x", position.x())
                .putInt("y", position.y())
                .putInt("z", position.z())
                .putString("CustomName", customName)
                .putBoolean("isMovable",getBlockState().getBlockAttributes().canBeMovingBlock())
                .build();
    }

    @Override
    @Impl
    public void load(NbtMap nbt) {
        if (nbt.containsKey("CustomName")) {
            this.customName = nbt.getString("CustomName");
        }
        var pos = new Position3i(position);
        pos.x = nbt.getInt("x", position.x());
        pos.y = nbt.getInt("y", position.y());
        pos.z = nbt.getInt("z", position.z());
        position = pos;
    }
}
