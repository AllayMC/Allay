package org.allaymc.api.blockentity.type;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.component.interfaces.ComponentProvider;

import java.util.List;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityType<T extends BlockEntity> {
    String getName();

    List<ComponentProvider<? extends BlockEntityComponent>> getComponentProviders();

    T createBlockEntity(BlockEntityInitInfo info);
}
