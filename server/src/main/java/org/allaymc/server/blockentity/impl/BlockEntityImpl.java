package org.allaymc.server.blockentity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.ComponentObject;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
@Getter
public abstract class BlockEntityImpl extends ComponentObject implements BlockEntity {
    protected BlockEntityBaseComponent baseComponent;

    public BlockEntityImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    protected BlockEntityBaseComponent getBaseComponent() {
        return baseComponent;
    }
}
