package org.allaymc.api.item;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.api.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;

import static org.allaymc.api.item.component.ItemBaseComponent.EMPTY_UNIQUE_ID;

/**
 * Represents the initialization information for an item stack.
 *
 * @author Cool_Loong | daoge_cmd
 */
public class ItemStackInitInfo implements ComponentInitInfo {
    protected final int count;
    protected final int meta;
    protected final int uniqueId;
    protected final boolean assignUniqueId;
    protected NbtMap extraTag;
    @Getter
    @Setter
    protected ItemType<?> itemType;

    protected ItemStackInitInfo(int count, int meta, NbtMap extraTag, int uniqueId, boolean assignUniqueId) {
        this.count = count;
        this.meta = meta;
        this.extraTag = extraTag;
        this.uniqueId = uniqueId;
        this.assignUniqueId = assignUniqueId;
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
     * Get the unique id of the item stack.
     *
     * @return the unique id of the item stack
     */
    public int uniqueId() {
        return uniqueId;
    }

    /**
     * Whether the unique id should be assigned.
     *
     * @return {@code true} if the unique id should be assigned, otherwise {@code false}
     */
    public boolean assignUniqueId() {
        return assignUniqueId;
    }

    public static class Builder {
        protected int count;
        protected int meta;
        protected NbtMapBuilder extraTagBuilder;
        protected int uniqueId;
        protected boolean assignUniqueId;

        public Builder() {
            this.extraTagBuilder = NbtMap.builder();
            this.uniqueId = EMPTY_UNIQUE_ID;
            this.assignUniqueId = true;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder meta(int meta) {
            this.meta = meta;
            return this;
        }

        public Builder uniqueId(int uniqueId) {
            Preconditions.checkArgument(uniqueId >= 0);
            this.uniqueId = uniqueId;
            this.assignUniqueId = false;
            return this;
        }

        public Builder assignUniqueId(boolean assignUniqueId) {
            if (assignUniqueId) {
                this.uniqueId = EMPTY_UNIQUE_ID;
            }
            this.assignUniqueId = assignUniqueId;
            return this;
        }

        public Builder extraTag(NbtMap extraTag) {
            extraTagBuilder.putAll(extraTag);
            return this;
        }

        public ItemStackInitInfo build() {
            return new ItemStackInitInfo(
                    count, meta, extraTagBuilder.build(),
                    uniqueId, assignUniqueId
            );
        }
    }
}
