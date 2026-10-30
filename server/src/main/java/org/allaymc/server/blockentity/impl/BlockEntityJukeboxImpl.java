package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityJukeboxBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityJukebox;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityJukeboxImpl} delegates jukebox disc storage and playback to
 * the configured component assembly. The inherited block-entity implementation
 * creates components, injects their dependencies and owner fields, and runs
 * completion hooks before construction returns, making this class the concrete
 * public capability for those components.
 *
 * @author IWareQ
 */
public class BlockEntityJukeboxImpl extends BlockEntityImpl implements BlockEntityJukebox {

    /**
     * {@code jukeboxBaseComponent} supplies the injected
     * {@link BlockEntityJukeboxBaseComponent} capability. Component assembly
     * requires exactly one provider result assignable to this field before
     * delegated operations are available.
     */
    @Delegate
    private BlockEntityJukeboxBaseComponent jukeboxBaseComponent;

    /**
     * Constructs the composed jukebox using the supplied component providers.
     * Superclass construction injects the inherited base and declared component
     * fields before invoking completion hooks; provider or injection failures
     * abort construction without rollback.
     */
    public BlockEntityJukeboxImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
