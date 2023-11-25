package org.allaymc.api.item.recipe;

import lombok.Builder;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.jetbrains.annotations.Nullable;

import static org.allaymc.api.item.interfaces.ItemAirStack.AIR_TYPE;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
public abstract class BaseRecipe implements Recipe {
    protected static ItemDescriptor[] EMPTY_DESCRIPTOR_ARRAY = new ItemDescriptor[0];
    protected Identifier identifier;
    protected String group;
    protected ItemStack output;
    protected String[] tags;
    protected ItemDescriptor[] unlockItems = EMPTY_DESCRIPTOR_ARRAY;

    protected BaseRecipe(Identifier identifier, String group, ItemStack output, String[] tags, ItemDescriptor[] unlockItems) {
        this.identifier = identifier;
        this.group = group;
        this.output = output;
        this.tags = tags;
        this.unlockItems = unlockItems;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public @Nullable String getGroup() {
        return group;
    }

    @Override
    public ItemStack getOutput() {
        return output;
    }

    @Override
    public String[] getTags() {
        return tags;
    }

    @Override
    public ItemDescriptor[] getUnlockItems() {
        return unlockItems;
    }
}
