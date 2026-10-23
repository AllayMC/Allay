package org.allaymc.server.container.impl;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerException;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.ContainerType;
import org.allaymc.server.container.ContainerNetworkInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code BaseContainerHolder} stores containers by type identity and resolves network
 * slot categories to those held types. Typed API lookup returns the same stored
 * container, and unknown type or slot-category lookup returns {@code null}. The exposed
 * container map is an unmodifiable live view rather than copied container storage.
 *
 * <p>
 * Registration rejects an existing type before adding the container, then registers the
 * slot categories declared by its network metadata. Categories shared by several types
 * resolve to the most recently added mapping. Metadata lookup follows the type
 * insertion, so missing metadata can leave a type registered without its network
 * category mappings.
 *
 * @author daoge_cmd
 */
public class BaseContainerHolder implements ContainerHolder {
    /**
     * {@code typeToContainer} retains each held container under its identity token.
     * {@code getContainers()} exposes an unmodifiable live view of this mutable map.
     */
    protected final Map<ContainerType<?>, Container> typeToContainer = new HashMap<>();
    /**
     * {@code slotTypeToFullType} resolves network categories to held type identities.
     * Later container registrations replace earlier mappings when their metadata
     * declares the same category.
     */
    protected final Map<ContainerSlotType, ContainerType<?>> slotTypeToFullType = new HashMap<>();

    /**
     * Creates a holder and registers the supplied containers in argument order. Each
     * registration uses duplicate-type checks and network metadata, so a later failure
     * can follow earlier successful registrations.
     */
    public BaseContainerHolder(Container... containers) {
        for (Container container : containers) {
            this.addContainer(container);
        }
    }

    /**
     * Returns an unmodifiable live view of registered type identities and containers.
     * Later additions remain visible through that view, and the stored container objects
     * remain shared with their owners.
     */
    @Override
    public @UnmodifiableView Map<ContainerType<?>, Container> getContainers() {
        return Collections.unmodifiableMap(typeToContainer);
    }

    /**
     * Returns whether the supplied type identity has a registered container.
     */
    @Override
    public boolean hasContainer(ContainerType<?> type) {
        return typeToContainer.containsKey(type);
    }

    /**
     * Returns the held container for the supplied type identity. A missing mapping
     * returns {@code null}; the generic cast relies on registration using the
     * container's matching type token.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Container> T getContainer(ContainerType<T> type) {
        return (T) typeToContainer.get(type);
    }

    /**
     * Resolves a network slot category to its held container. Unknown categories return
     * {@code null}; categories shared by several registered types use the last mapping
     * installed by registration.
     */
    @SuppressWarnings("unchecked")
    public <T extends Container> T getContainer(ContainerSlotType slotType) {
        return (T) getContainer(slotTypeToFullType.get(slotType));
    }

    /**
     * Registers one container and its network slot categories. An existing type identity
     * throws {@code ContainerException} before either map changes.
     *
     * <p>
     * The type map receives the container before metadata supplies category mappings, so
     * the container must have registered network metadata. Missing metadata can fail
     * after the first insertion, leaving partial registration; no rollback restores the
     * previous maps. For overlapping categories, later category writes select this
     * container's type for subsequent network lookups.
     */
    @Override
    public void addContainer(Container container) {
        if (typeToContainer.containsKey(container.getContainerType())) {
            throw new ContainerException("Inventory " + container.getClass().getSimpleName() + " already exists");
        }

        // Record type ownership before resolving network categories; missing metadata
        // can leave this first insertion visible without category mappings.
        typeToContainer.put(container.getContainerType(), container);
        ContainerNetworkInfo.getInfo(container.getContainerType()).heldSlotTypes().forEach(slotType -> slotTypeToFullType.put(slotType, container.getContainerType()));
    }
}
