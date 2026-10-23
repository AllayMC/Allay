package org.allaymc.server.blockentity.impl;

import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityEnchantTable;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityEnchantTableImpl} delegates enchantment table identity to
 * the configured component assembly. The inherited block-entity implementation
 * creates components, injects their dependencies and owner fields, and runs
 * completion hooks before construction returns, making this class the concrete
 * public capability for those components.
 *
 * @author daoge_cmd
 */
public class BlockEntityEnchantTableImpl extends BlockEntityImpl implements BlockEntityEnchantTable {
    /**
     * Constructs the composed enchantment table using the supplied component
     * providers. Superclass construction injects the inherited base and
     * declared component fields before invoking completion hooks; provider or
     * injection failures abort construction without rollback.
     */
    public BlockEntityEnchantTableImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
