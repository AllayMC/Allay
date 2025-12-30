package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockBarrelBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBarrelBehaviorImpl extends BlockBehaviorImpl implements BlockBarrelBehavior {
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityBarrel> blockEntityHolderComponent;

    public BlockBarrelBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
