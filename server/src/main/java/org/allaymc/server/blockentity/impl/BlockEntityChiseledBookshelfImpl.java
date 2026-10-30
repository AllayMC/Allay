package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityChiseledBookshelfContainerHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityChiseledBookshelf;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityChiseledBookshelfImpl} delegates bookshelf inventory and
 * last-slot operations to injected components while inheriting common
 * block-entity behavior. Component assembly initializes the delegation fields
 * before completion hooks run; this class contributes the public capability
 * shape while its configured providers supply the underlying state and
 * implementation.
 *
 * @author daoge_cmd
 */
public class BlockEntityChiseledBookshelfImpl extends BlockEntityImpl implements BlockEntityChiseledBookshelf {
    /**
     * {@code containerHolderComponent} supplies the injected
     * {@link BlockEntityChiseledBookshelfContainerHolderComponent} capability.
     * Component assembly requires exactly one provider result assignable to
     * this field before delegated operations are available.
     */
    @Delegate
    private BlockEntityChiseledBookshelfContainerHolderComponent containerHolderComponent;

    /**
     * Constructs the composed chiseled bookshelf using the supplied component
     * providers. The superclass performs injection and completion hooks before
     * returning; providers must supply the inherited base and every declared
     * delegation capability.
     */
    public BlockEntityChiseledBookshelfImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
