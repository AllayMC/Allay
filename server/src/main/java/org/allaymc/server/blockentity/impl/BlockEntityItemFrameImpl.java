package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityItemFrameBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityGlowItemFrame;
import org.allaymc.api.blockentity.interfaces.BlockEntityItemFrame;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityItemFrameImpl} delegates item frame contents and rotation
 * to the configured component assembly. The inherited block-entity
 * implementation creates components, injects their dependencies and owner
 * fields, and runs completion hooks before construction returns, making this
 * class the concrete public capability for those components.
 *
 * @author daoge_cmd
 */
public class BlockEntityItemFrameImpl extends BlockEntityImpl implements BlockEntityItemFrame, BlockEntityGlowItemFrame {

    /**
     * {@code itemFrameBaseComponent} supplies the injected
     * {@link BlockEntityItemFrameBaseComponent} capability. Component assembly
     * requires exactly one provider result assignable to this field before
     * delegated operations are available.
     */
    @Delegate
    private BlockEntityItemFrameBaseComponent itemFrameBaseComponent;

    /**
     * Constructs the composed item frame using the supplied component
     * providers. Superclass construction injects the inherited base and
     * declared component fields before invoking completion hooks; provider or
     * injection failures abort construction without rollback.
     */
    public BlockEntityItemFrameImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
