package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public abstract class BlockEntityImpl extends ComponentClass implements BlockEntity {
    protected BlockEntityBaseComponent baseComponent;

    public BlockEntityImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    public BlockEntityBaseComponent getBaseComponent() {
        return baseComponent;
    }
}
