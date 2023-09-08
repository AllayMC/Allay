package cn.allay.api.item.type;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.identifier.Identified;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponent;
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

    @Nullable
    Identifier getBlockIdentifier();

    @Nullable
    BlockType<?> getBlockType();

    default ItemDefinition toNetworkDefinition() {
        return new SimpleItemDefinition(getIdentifier().toString(), getRuntimeId(), false);
    }
}
