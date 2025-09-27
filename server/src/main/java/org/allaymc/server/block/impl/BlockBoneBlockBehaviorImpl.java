package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockBoneBlockBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBoneBlockBehaviorImpl extends BlockBehaviorImpl implements BlockBoneBlockBehavior {
    public BlockBoneBlockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
