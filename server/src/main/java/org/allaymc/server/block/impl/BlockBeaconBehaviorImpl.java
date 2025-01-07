package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockBeaconBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBeacon;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBeaconBehaviorImpl extends BlockBehaviorImpl implements BlockBeaconBehavior {

    @Delegate
    protected BlockEntityHolderComponent<BlockEntityBeacon> blockEntityHolderComponent;

    public BlockBeaconBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
