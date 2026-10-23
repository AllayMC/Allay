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
 * {@code ItemStackInitInfo} carries stack values into item component construction.
 * Its builder collects count, metadata, extra NBT, and the network-ID policy;
 * the creating item type supplies {@code itemType} before constructing ordinary
 * stacks so components can resolve their owning type.
 *
 * @author Cool_Loong | daoge_cmd
 */
public class ItemStackInitInfo implements ComponentInitInfo {
    /**
     * Initial stack item count.
     */
    protected final int count;
    /**
     * Initial item metadata value.
     */
    protected final int meta;
    /**
     * {@code uniqueId} holds the requested network ID. The base component uses a
     * nonzero value directly before considering {@code assignUniqueId}.
     */
    protected final int uniqueId;
    /**
     * {@code assignUniqueId} requests automatic allocation when {@code uniqueId} is
     * {@code EMPTY_UNIQUE_ID}; otherwise the explicit network ID takes precedence.
     */
    protected final boolean assignUniqueId;
    /**
     * Initial component extra NBT.
     */
    protected NbtMap extraTag;
    /**
     * {@code itemType} identifies the creating item type. Item creation sets this
     * reference before component construction; the builder leaves it unset.
     */
    @Getter
    @Setter
    protected ItemType<?> itemType;

    /**
     * Creates initialization values without validating or normalizing their contents.
     * The constructor retains {@code extraTag} and leaves {@code itemType} unset for
     * the item factory to assign before constructing components.
     */
    protected ItemStackInitInfo(int count, int meta, NbtMap extraTag, int uniqueId, boolean assignUniqueId) {
        this.count = count;
        this.meta = meta;
        this.extraTag = extraTag;
        this.uniqueId = uniqueId;
        this.assignUniqueId = assignUniqueId;
    }

    /**
     * Returns a new builder for independent item initialization values.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns the item count supplied for component stack construction.
     */
    public int count() {
        return count;
    }

    /**
     * Returns the metadata value supplied for component stack construction.
     */
    public int meta() {
        return meta;
    }

    /**
     * Returns the extra NBT supplied for component stack initialization.
     */
    public NbtMap extraTag() {
        return extraTag;
    }

    /**
     * Returns the requested stack network ID for component construction.
     * {@code EMPTY_UNIQUE_ID} leaves allocation to {@code assignUniqueId}; a nonzero
     * ID is used directly by the constructed base component.
     */
    public int uniqueId() {
        return uniqueId;
    }

    /**
     * Returns whether component construction should allocate a stack network ID.
     * The base component consults this flag only when {@code uniqueId} is
     * {@code EMPTY_UNIQUE_ID}, so an explicit ID takes precedence.
     */
    public boolean assignUniqueId() {
        return assignUniqueId;
    }

    /**
     * {@code Builder} accumulates initialization values and controls automatic stack
     * network-ID assignment. Explicit IDs disable automatic assignment, while enabling
     * assignment clears a previously selected ID.
     */
    public static class Builder {
        /**
         * Requested initial stack count.
         */
        protected int count;
        /**
         * Requested initial metadata value.
         */
        protected int meta;
        /**
         * Accumulated component extra NBT.
         */
        protected NbtMapBuilder extraTagBuilder;
        /**
         * {@code uniqueId} holds the explicit ID selected for the next build. Enabling
         * {@code assignUniqueId} clears this value to {@code EMPTY_UNIQUE_ID}.
         */
        protected int uniqueId;
        /**
         * {@code assignUniqueId} controls automatic allocation for the next build.
         * {@code uniqueId(int)} disables this flag after accepting an explicit ID.
         */
        protected boolean assignUniqueId;

        /**
         * Creates a builder with empty extra NBT and automatic network-ID assignment.
         * The initial count and metadata remain {@code 0}; the explicitly selected ID
         * starts at {@code EMPTY_UNIQUE_ID}.
         */
        public Builder() {
            this.extraTagBuilder = NbtMap.builder();
            this.uniqueId = EMPTY_UNIQUE_ID;
            this.assignUniqueId = true;
        }

        /**
         * Selects the initial stack count and returns this builder.
         * This setter stores {@code count} without validation, including {@code 0};
         * the chosen component implementation determines any construction constraints.
         */
        public Builder count(int count) {
            this.count = count;
            return this;
        }

        /**
         * Selects the initial item metadata and returns this builder.
         * This setter stores {@code meta} without validation; the chosen component
         * implementation determines how that value is interpreted.
         */
        public Builder meta(int meta) {
            this.meta = meta;
            return this;
        }

        /**
         * Selects an explicit network ID and disables automatic assignment.
         * The ID must be nonnegative; {@code 0} selects {@code EMPTY_UNIQUE_ID} and leaves
         * the stack without an allocated ID unless assignment is enabled later.
         *
         * @throws IllegalArgumentException if {@code uniqueId} is negative
         */
        public Builder uniqueId(int uniqueId) {
            Preconditions.checkArgument(uniqueId >= 0);
            this.uniqueId = uniqueId;
            this.assignUniqueId = false;
            return this;
        }

        /**
         * Selects whether construction should allocate a network ID and returns this
         * builder. Enabling assignment clears any explicit ID; disabling assignment
         * retains the currently stored ID for the next build.
         */
        public Builder assignUniqueId(boolean assignUniqueId) {
            if (assignUniqueId) {
                this.uniqueId = EMPTY_UNIQUE_ID;
            }
            this.assignUniqueId = assignUniqueId;
            return this;
        }

        /**
         * Merges the supplied extra NBT entries into this builder.
         * Later entries replace earlier values with the same key; this method does not
         * clear unrelated entries accumulated by previous calls.
         */
        public Builder extraTag(NbtMap extraTag) {
            extraTagBuilder.putAll(extraTag);
            return this;
        }

        /**
         * Builds initialization values from the builder's current state.
         * The resulting object has no {@code itemType} until the item factory assigns
         * one, and building leaves this builder available for further edits.
         */
        public ItemStackInitInfo build() {
            return new ItemStackInitInfo(
                    count, meta, extraTagBuilder.build(),
                    uniqueId, assignUniqueId
            );
        }
    }
}
