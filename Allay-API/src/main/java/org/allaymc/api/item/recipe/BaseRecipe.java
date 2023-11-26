package org.allaymc.api.item.recipe;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;

import java.util.UUID;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
public abstract class BaseRecipe implements Recipe, NetworkRecipe, UniqueRecipe, TaggedRecipe, IdentifiedRecipe {
    protected static ItemDescriptor[] EMPTY_DESCRIPTOR_ARRAY = new ItemDescriptor[0];
    protected static int NETWORK_ID_COUNTER = 1;
    protected Identifier identifier;
    protected ItemStack[] outputs;
    protected String tag;
    protected int networkId;
    protected UUID uuid;
    // TODO: unlock
    protected ItemDescriptor[] unlockItems;
    protected String unlockCondition;

    protected BaseRecipe(Identifier identifier, ItemStack[] outputs, String tag, ItemDescriptor[] unlockItems, String unlockCondition) {
        this(identifier, outputs, tag, unlockItems, unlockCondition, UUID.randomUUID());
    }

    protected BaseRecipe(Identifier identifier, ItemStack[] outputs, String tag, ItemDescriptor[] unlockItems, String unlockCondition, UUID uuid) {
        this.identifier = identifier;
        this.outputs = outputs;
        this.tag = tag;
        this.unlockItems = unlockItems == null ? EMPTY_DESCRIPTOR_ARRAY : unlockItems;
        this.unlockCondition = unlockCondition == null ? "" : unlockCondition;
        this.networkId = NETWORK_ID_COUNTER++;
        this.uuid = uuid;
    }


    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public ItemStack[] getOutputs() {
        return outputs;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public int getNetworkId() {
        return networkId;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public ItemDescriptor[] getUnlockItems() {
        return unlockItems;
    }

    @Override
    public String getUnlockCondition() {
        return unlockCondition;
    }
}
