package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityNoteblockBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityNoteblock;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockEntityNoteblockImpl extends BlockEntityImpl implements BlockEntityNoteblock {
    public BlockEntityNoteblockImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public BlockEntityNoteblockBaseComponent getBaseComponent() {
        return (BlockEntityNoteblockBaseComponent) super.getBaseComponent();
    }
}
