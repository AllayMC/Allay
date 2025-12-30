package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBedBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBed;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author harry-xi
 */
public class BlockEntityBedImpl extends BlockEntityImpl implements BlockEntityBed {

    @Delegate
    private BlockEntityBedBaseComponent bedBaseComponent;

    public BlockEntityBedImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
