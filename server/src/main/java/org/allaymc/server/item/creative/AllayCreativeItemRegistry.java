package org.allaymc.server.item.creative;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.api.item.creative.CreativeItemCategory.Type;
import org.allaymc.api.item.creative.CreativeItemEntry;
import org.allaymc.api.item.creative.CreativeItemGroup;
import org.allaymc.api.item.creative.CreativeItemRegistry;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * {@code AllayCreativeItemRegistry} stores the creative inventory's categories,
 * global group order, and global entry order used for protocol initialization.
 * The constructor loads group definitions before item definitions so each item
 * can resolve its recorded group index within its category. Missing group
 * references are logged and skipped rather than reassigned to another group.
 *
 * <p>
 * The {@code groups} and {@code entries} list positions are the authoritative
 * zero-based indices. Category and group maps retain those global indices;
 * protocol encoding adds one only for creative entry network IDs. The server
 * freezes registration before preparing protocol data, and {@code ensureMutable}
 * rejects later registration attempts. Freezing protects further assignments
 * through these paths; stored stacks and icons remain shared mutable objects.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayCreativeItemRegistry implements CreativeItemRegistry {

    /**
     * Creative categories keyed by inventory tab.
     */
    protected final Map<Type, AllayCreativeItemCategory> categories;
    /**
     * {@code entries} retains creative entries in global index order. Each list
     * position is the server index consumed by creative-action processing.
     */
    protected final List<CreativeItemEntry> entries;
    /**
     * {@code groups} retains groups in global index order. Category group maps use
     * these positions rather than allocating separate indices within each category.
     */
    protected final List<CreativeItemGroup> groups;
    /**
     * {@code frozen} makes subsequent {@code ensureMutable} checks reject registration.
     * The flag does not turn registration's multiple list and map updates into an
     * atomic concurrent operation.
     */
    protected volatile boolean frozen;

    /**
     * Creates all four categories and loads their built-in groups and entries.
     * Loading requires initialized item types for icons and item stacks; resource
     * errors escape construction, and registration initially remains mutable.
     */
    public AllayCreativeItemRegistry() {
        this.categories = new EnumMap<>(Type.class);
        this.categories.put(Type.CONSTRUCTION, new AllayCreativeItemCategory(this, Type.CONSTRUCTION));
        this.categories.put(Type.NATURE, new AllayCreativeItemCategory(this, Type.NATURE));
        this.categories.put(Type.EQUIPMENT, new AllayCreativeItemCategory(this, Type.EQUIPMENT));
        this.categories.put(Type.ITEMS, new AllayCreativeItemCategory(this, Type.ITEMS));
        this.entries = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.load();
    }

    /**
     * Loads creative groups and then attaches resource items to those groups.
     * Group registration must precede item lookup because the item resource stores
     * global group indices, and a category lookup rejects an index from another
     * category even when it exists globally.
     *
     * <p>
     * Item stacks start without an automatically allocated unique ID; registering
     * each item assigns its positive creative entry ID instead. Unknown item or
     * icon types fail loading, while an unknown group logs a warning and skips that
     * item. Completed registrations are not rolled back after a later failure.
     */
    @SneakyThrows
    protected void load() {
        // Resolve group order first because item records refer to global group
        // indices and category lookup must find their owning groups.
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("creative_groups.json")))) {
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> {
                var group = entry.getAsJsonObject();
                var category = getCategory(Type.valueOf(group.get("category").getAsString().toUpperCase(Locale.ROOT)));
                var name = group.get("name").getAsString();
                if (name.isEmpty()) {
                    category.registerUnnamedGroup();
                } else {
                    var iconItemTypeName = new Identifier(group.get("icon").getAsString());
                    var iconItemType = Registries.ITEMS.get(iconItemTypeName);
                    Objects.requireNonNull(iconItemType, "Unknown icon item type: " + iconItemTypeName);
                    category.registerGroup(name, iconItemType.createItemStack());
                }
            });
        }

        // Register items only after the complete group table exists, preserving
        // each resource entry's group association during positive ID assignment.
        try (var reader = NbtUtils.createGZIPReader(new BufferedInputStream(Utils.getResource("creative_items.nbt")))) {
            var items = ((NbtMap) reader.readTag()).getList("items", NbtType.COMPOUND);
            for (var item : items) {
                var itemTypeName = new Identifier(item.getString("name"));
                var itemType = Registries.ITEMS.get(itemTypeName);
                Objects.requireNonNull(itemType, "Unknown item type: " + itemTypeName);

                var category = getCategory(Type.valueOf(item.getString("category").toUpperCase(Locale.ROOT)));
                var itemStack = itemType.createItemStack(
                        ItemStackInitInfo
                                .builder().count(1).meta(item.getShort("damage"))
                                .extraTag(item.getCompound("tag", NbtMap.builder().build()))
                                .assignUniqueId(false).build()
                );
                int groupIndex = (int) item.getLong("groupIndex");
                var group = category.getGroup(groupIndex);
                // Missing category membership cannot be repaired from the global
                // index alone; skip this item rather than attach it to another tab.
                if (group == null) {
                    log.warn("Unknown group index {} for item {} in category {}!", groupIndex, itemTypeName, category.getType());
                    continue;
                }

                group.registerItem(itemStack);
            }
        }
    }

    /**
     * Returns the category registered for the supplied creative inventory tab.
     */
    @Override
    public CreativeItemCategory getCategory(Type type) {
        return categories.get(type);
    }

    /**
     * Returns the entry at the supplied global zero-based list index.
     * The index must be within {@code entries}; the backing list throws for an
     * invalid index instead of returning {@code null}.
     *
     * @throws IndexOutOfBoundsException if {@code index} is outside {@code entries}
     */
    @Override
    public CreativeItemEntry getEntryByIndex(int index) {
        return entries.get(index);
    }

    /**
     * Returns the group at the supplied global zero-based list index.
     * The index must be within {@code groups}; the backing list throws for an
     * invalid index instead of returning {@code null}.
     *
     * @throws IndexOutOfBoundsException if {@code index} is outside {@code groups}
     */
    @Override
    public CreativeItemGroup getGroupByIndex(int index) {
        return groups.get(index);
    }

    /**
     * Returns an unmodifiable view of global entries in index order.
     * The view reflects later additions to {@code entries}; each entry still holds
     * the same mutable item stack registered by its group.
     */
    @Override
    public List<CreativeItemEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    /**
     * Returns an unmodifiable view of global groups in index order.
     * The view reflects later additions to {@code groups}; each group and its icon
     * remain the registered objects rather than snapshots.
     */
    @Override
    public List<CreativeItemGroup> getGroups() {
        return Collections.unmodifiableList(groups);
    }

    /**
     * Appends an entry retaining the supplied group and stack references.
     * The new entry's index is its list position; this method does not update the
     * stack unique ID or the owning group's map. Frozen registration is rejected.
     */
    CreativeItemEntry assignIndexForEntry(CreativeItemGroup group, ItemStack itemStack) {
        ensureMutable();
        var entry = new CreativeItemEntry(entries.size(), group, itemStack);
        entries.add(entry);
        return entry;
    }

    /**
     * Appends the supplied group and returns its zero-based global list index.
     * The caller publishes category mappings separately after this assignment;
     * {@code ensureMutable} rejects the addition when registration is frozen.
     */
    int assignIndexForGroup(CreativeItemGroup group) {
        ensureMutable();
        groups.add(group);
        return groups.size() - 1;
    }

    /**
     * Marks creative registration frozen for later assignment checks.
     * Repeated calls retain the frozen state; existing lists, entries, stacks, and
     * icons remain accessible through their current views and references.
     */
    public void freeze() {
        frozen = true;
    }

    /**
     * Rejects registration when the creative registry has been frozen.
     * Registration paths invoke this check before assigning indices, so an observed
     * frozen flag prevents their next list or map update.
     *
     * @throws IllegalStateException if this registry is frozen
     */
    void ensureMutable() {
        if (frozen) {
            throw new IllegalStateException("Creative item registry is frozen");
        }
    }
}
