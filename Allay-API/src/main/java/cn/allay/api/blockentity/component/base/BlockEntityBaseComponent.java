package cn.allay.api.blockentity.component.base;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.BlockEntityComponent;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.component.annotation.Inject;
import cn.allay.api.math.position.Position3ic;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityBaseComponent extends BlockEntityComponent {

    @Inject
    BlockEntityType<? extends BlockEntity> getBlockEntityType();

    @Inject
    Position3ic getPosition();

    @Inject
    NbtMap save();

    @Inject
    void load(NbtMap nbt);

    @Inject
    default void tick() {}

    default BlockState getBlockState() {
        var pos = getPosition();
        return pos.world().getBlockState(pos.x(), pos.y(), pos.z());
    }
}
