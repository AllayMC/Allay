package org.allaymc.server.item.creative;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.api.item.creative.CreativeItemGroup;
import org.allaymc.api.item.interfaces.ItemAirStack;

import java.util.Collections;
import java.util.Map;

/**
 * {@code AllayCreativeItemCategory} stores one creative tab's groups by registry
 * index and registered name. Group construction obtains a global index from the
 * registry before this category publishes its mappings. Duplicate names replace
 * only named lookup; earlier group indices remain accessible.
 *
 * @author daoge_cmd
 */
public class AllayCreativeItemCategory implements CreativeItemCategory {
    /**
     * Registry allocating global creative indices.
     */
    protected final AllayCreativeItemRegistry registry;
    /**
     * Owning creative inventory tab.
     */
    @Getter
    protected final Type type;

    /**
     * {@code groups} retains every group by its registry-wide index. The map keeps
     * earlier groups even when a later registration replaces their named lookup.
     */
    protected Map<Integer, CreativeItemGroup> groups;
    /**
     * {@code namedGroups} maps each supplied name to its latest registered group.
     * The unnamed-group helper does not add entries to this map.
     */
    protected Map<String, CreativeItemGroup> namedGroups;

    /**
     * Creates an empty category attached to its registry and tab type.
     */
    public AllayCreativeItemCategory(AllayCreativeItemRegistry registry, Type type) {
        this.registry = registry;
        this.type = type;
        this.groups = new Int2ObjectOpenHashMap<>();
        this.namedGroups = new Object2ObjectOpenHashMap<>();
    }

    /**
     * Returns this category's group associated with the supplied global index.
     * An index absent from this category returns {@code null}, regardless of groups
     * registered under the same registry's other categories.
     */
    @Override
    public CreativeItemGroup getGroup(int index) {
        return groups.get(index);
    }

    /**
     * Returns the latest group registered under the supplied nonblank name.
     * The lookup uses the name exactly, and an absent name returns {@code null}.
     * Blank names throw before lookup.
     *
     * @throws IllegalArgumentException if {@code name} is blank
     */
    @Override
    public CreativeItemGroup getNamedGroup(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Group name cannot be blank");
        }

        return namedGroups.get(name);
    }

    /**
     * Registers a group using non-{@code null} name and icon references.
     * The constructor appends the group to the registry before category mappings are
     * updated. A duplicate name replaces named lookup while retaining every group
     * by index; blank names are accepted here even though named lookup rejects them.
     *
     * @throws IllegalStateException if the registry has frozen registration
     */
    @Override
    public CreativeItemGroup registerGroup(String name, ItemStack icon) {
        registry.ensureMutable();
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(icon);
        var group = new AllayCreativeItemGroup(registry, this, name, icon);
        groups.put(group.getIndex(), group);
        namedGroups.put(name, group);
        return group;
    }

    /**
     * Registers an unnamed group with the shared air-stack icon.
     * The group receives a global index and enters this category's index map, but
     * does not enter named lookup. Frozen registration rejects the addition.
     */
    @Override
    public CreativeItemGroup registerUnnamedGroup() {
        registry.ensureMutable();
        var group = new AllayCreativeItemGroup(registry, this, "", ItemAirStack.AIR_STACK);
        groups.put(group.getIndex(), group);
        return group;
    }

    /**
     * Returns an unmodifiable view of the category's indexed group map.
     * The view observes later additions to this map and retains the registered
     * group objects rather than copying their state.
     */
    @Override
    public Map<Integer, CreativeItemGroup> getGroups() {
        return Collections.unmodifiableMap(groups);
    }
}
