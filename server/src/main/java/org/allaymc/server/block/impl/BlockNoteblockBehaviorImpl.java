package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockNoteblockBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityNoteblock;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockNoteblockBehaviorImpl extends BlockBehaviorImpl implements BlockNoteblockBehavior {

    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityNoteblock> blockEntityHolderComponent;

    public BlockNoteblockBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
