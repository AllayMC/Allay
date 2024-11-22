package org.allaymc.server.blockentity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
@Getter
public class BlockEntityBarrelImpl extends BlockEntityImpl implements BlockEntityBarrel {

    @Delegate
    protected BlockEntityContainerHolderComponent containerHolderComponent;

    public BlockEntityBarrelImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
