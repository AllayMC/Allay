package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockNoteblockBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockNoteblockBehaviorImpl extends BlockBehaviorImpl implements BlockNoteblockBehavior {
    public BlockNoteblockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
