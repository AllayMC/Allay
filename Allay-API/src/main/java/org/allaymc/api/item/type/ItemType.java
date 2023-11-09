package org.allaymc.api.item.type;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.identifier.Identified;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.SimpleItemDefinition;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemType<T extends ItemStack> extends Identified {
    List<ComponentProvider<? extends ItemComponent>> getComponentProviders();

    T createItemStack(ItemStackInitInfo<T> info);

    int getRuntimeId();

    default Identifier getBlockIdentifier() {
        var blockType = getBlockType();
        return blockType == null ? null : blockType.getIdentifier();
    }

    BlockType<?> getBlockType();

    default ItemDefinition toNetworkDefinition() {
        return new SimpleItemDefinition(getIdentifier().toString(), getRuntimeId(), false);
    }
}
