package org.allaymc.server.item.creative;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.api.item.creative.CreativeItemEntry;
import org.allaymc.api.item.creative.CreativeItemGroup;

import java.util.Collections;
import java.util.Map;

/**
 * {@code AllayCreativeItemGroup} stores one category's named or unnamed creative
 * entries and retains its display icon. The constructor appends the group to
 * the registry and stores the resulting global group index; the category then
 * adds its own lookup mapping.
 *
 * <p>
 * {@code registerItem} associates the same stack with a global entry and this
 * group's entry map. The server index starts at zero, while the client creative
 * network ID starts at one, so registration writes {@code entry.index() + 1}
 * into the stack. These steps mutate the supplied stack and have no rollback
 * if a later operation fails.
 *
 * @author daoge_cmd
 */
public class AllayCreativeItemGroup implements CreativeItemGroup {
    /**
     * Registry allocating global creative indices.
     */
    protected final AllayCreativeItemRegistry registry;
    /**
     * Category owning this creative group.
     */
    @Getter
    protected final CreativeItemCategory category;
    /**
     * Registered creative group display name.
     */
    @Getter
    protected final String name;
    /**
     * {@code icon} retains the supplied display stack. The generated getter returns
     * that same reference, so icon mutations affect subsequent protocol encoding.
     */
    @Getter
    protected final ItemStack icon;
    /**
     * Zero-based global creative group index.
     */
    @Getter
    protected final int index;

    /**
     * Global-indexed entries belonging to this group.
     */
    protected Map<Integer, CreativeItemEntry> items;

    /**
     * Creates a group retaining its registry, category, name, and icon.
     * The constructor immediately appends this group to the registry to obtain its
     * index; it does not publish category lookup mappings itself.
     */
    public AllayCreativeItemGroup(AllayCreativeItemRegistry registry, CreativeItemCategory category, String name, ItemStack icon) {
        this.registry = registry;
        this.category = category;
        this.name = name;
        this.icon = icon;
        this.index = registry.assignIndexForGroup(this);
        this.items = new Int2ObjectOpenHashMap<>();
    }

    /**
     * Registers the supplied stack in the registry and this group's entry map.
     * The stack is retained directly and its unique ID becomes the global entry
     * index plus one, matching the positive creative network ID sent to clients.
     *
     * <p>
     * The registry appends the entry before the stack setter and group-map insertion
     * run. A failure in either later step leaves the global entry registered;
     * there is no rollback. Frozen registration throws before assignment starts.
     */
    @Override
    public CreativeItemEntry registerItem(ItemStack itemStack) {
        registry.ensureMutable();
        var entry = registry.assignIndexForEntry(this, itemStack);
        // Creative requests use positive network IDs and subtract one before
        // registry lookup, so the stack must carry the offset entry index.
        itemStack.setUniqueId(entry.index() + 1);
        this.items.put(entry.index(), entry);
        return entry;
    }

    /**
     * Returns an unmodifiable view of this group's registered entry map.
     * The map reflects later additions, and the entry values retain their original
     * mutable stack references rather than copying item state.
     */
    @Override
    public Map<Integer, CreativeItemEntry> getItems() {
        return Collections.unmodifiableMap(items);
    }
}
