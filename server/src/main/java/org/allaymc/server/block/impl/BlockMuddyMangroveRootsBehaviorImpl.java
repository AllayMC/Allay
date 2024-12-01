package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockMuddyMangroveRootsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockMuddyMangroveRootsBehaviorImpl extends BlockBehaviorImpl implements BlockMuddyMangroveRootsBehavior {
    public BlockMuddyMangroveRootsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
