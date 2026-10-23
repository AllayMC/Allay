package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityNoteblockBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityNoteblock;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityNoteblockImpl} delegates note block pitch and powered state
 * to the configured component assembly. The inherited block-entity
 * implementation creates components, injects their dependencies and owner
 * fields, and runs completion hooks before construction returns, making this
 * class the concrete public capability for those components.
 *
 * @author daoge_cmd
 */
public class BlockEntityNoteblockImpl extends BlockEntityImpl implements BlockEntityNoteblock {

    /**
     * {@code noteblockBaseComponent} supplies the injected
     * {@link BlockEntityNoteblockBaseComponent} capability. Component assembly
     * requires exactly one provider result assignable to this field before
     * delegated operations are available.
     */
    @Delegate
    private BlockEntityNoteblockBaseComponent noteblockBaseComponent;

    /**
     * Constructs the composed note block using the supplied component
     * providers. Superclass construction injects the inherited base and
     * declared component fields before invoking completion hooks; provider or
     * injection failures abort construction without rollback.
     */
    public BlockEntityNoteblockImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
