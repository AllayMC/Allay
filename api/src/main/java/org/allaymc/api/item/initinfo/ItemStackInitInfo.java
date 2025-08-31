package org.allaymc.api.item.initinfo;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

import static org.allaymc.api.item.component.ItemBaseComponent.EMPTY_STACK_NETWORK_ID;

/**
 * Represents the initialization information for an item stack.
 *
 * @author Cool_Loong | daoge_cmd
 */
public class ItemStackInitInfo implements ComponentInitInfo {
    protected final int count;
    protected final int meta;
    protected final int stackNetworkId;
    protected final boolean autoAssignStackNetworkId;
    protected NbtMap extraTag;
    @Getter
    @Setter
    protected ItemType<?> itemType;

    protected ItemStackInitInfo(int count, int meta, NbtMap extraTag, int stackNetworkId, boolean autoAssignStackNetworkId) {
        this.count = count;
        this.meta = meta;
        this.extraTag = extraTag;
        this.stackNetworkId = stackNetworkId;
        this.autoAssignStackNetworkId = autoAssignStackNetworkId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Get the count of the item stack.
     *
     * @return the count of the item stack
     */
    public int count() {
        return count;
    }

    /**
     * Get the meta of the item stack.
     *
     * @return the meta of the item stack
     */
    public int meta() {
        return meta;
    }

    /**
     * Get the extra tag of the item stack.
     *
     * @return the extra tag of the item stack
     */
    public NbtMap extraTag() {
        return extraTag;
    }

    /**
     * Get the stack network id of the item stack.
     *
     * @return the stack network id of the item stack
     */
    public int stackNetworkId() {
        return stackNetworkId;
    }

    /**
     * Whether to auto assign the stack network id.
     *
     * @return whether to auto assign the stack network id
     */
    public boolean autoAssignStackNetworkId() {
        return autoAssignStackNetworkId;
    }

    public static class Builder {
        protected int count;
        protected int meta;
        protected NbtMapBuilder extraTagBuilder = NbtMap.builder();
        protected int stackNetworkId = EMPTY_STACK_NETWORK_ID;
        protected boolean autoAssignStackNetworkId = true;

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder meta(int meta) {
            this.meta = meta;
            return this;
        }

        public Builder stackNetworkId(int stackNetworkId) {
            Preconditions.checkArgument(stackNetworkId >= 0);
            this.stackNetworkId = stackNetworkId;
            this.autoAssignStackNetworkId = false;
            return this;
        }

        public Builder autoAssignStackNetworkId(boolean autoAssignStackNetworkId) {
            if (autoAssignStackNetworkId) {
                this.stackNetworkId = 0;
            }
            this.autoAssignStackNetworkId = autoAssignStackNetworkId;
            return this;
        }

        public Builder extraTag(NbtMap extraTag) {
            extraTagBuilder.putAll(extraTag);
            return this;
        }

        public ItemStackInitInfo build() {
            return new ItemStackInitInfo(
                    count, meta, extraTagBuilder.build(),
                    stackNetworkId, autoAssignStackNetworkId);
        }
    }
}
