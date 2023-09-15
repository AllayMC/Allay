package cn.allay.api.blockentity.type;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.BlockEntityComponent;
import cn.allay.api.blockentity.init.BlockEntityInitInfo;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.entity.component.EntityComponent;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.identifier.Identified;

import java.util.List;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityType<T extends BlockEntity> extends Identified {
    List<ComponentProvider<? extends BlockEntityComponent>> getComponentProviders();

    T createEntity(BlockEntityInitInfo<T> info);
}
