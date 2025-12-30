package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockBeaconBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBeacon;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBeaconBehaviorImpl extends BlockBehaviorImpl implements BlockBeaconBehavior {

    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityBeacon> blockEntityHolderComponent;

    public BlockBeaconBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
