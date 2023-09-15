package cn.allay.api.blockentity.type;

import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.BlockEntityComponent;
import cn.allay.api.blockentity.init.BlockEntityInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.identifier.Identified;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityType<T extends BlockEntity> {
    List<ComponentProvider<? extends BlockEntityComponent>> getComponentProviders();

    @ApiStatus.Internal
    T createBlockEntity(BlockEntityInitInfo<T> info);

    String getBlockEntityId();
}
