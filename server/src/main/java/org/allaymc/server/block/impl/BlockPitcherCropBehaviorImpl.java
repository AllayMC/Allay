package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockFertilizableComponent;
import org.allaymc.api.block.interfaces.BlockPitcherCropBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPitcherCropBehaviorImpl extends BlockBehaviorImpl implements BlockPitcherCropBehavior {
    @Delegate
    private BlockFertilizableComponent fertilizableComponent;

    public BlockPitcherCropBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
