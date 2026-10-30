package org.allaymc.server.item.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemSpawnEggBaseComponent;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.entity.data.EntityId;
import org.allaymc.server.world.physics.AllayEntityPhysicsEngine;
import org.joml.Vector3ic;

import java.util.function.Supplier;

/**
 * {@code ItemSpawnEggBaseComponentImpl} creates the entity selected by a spawn
 * egg at the clicked world position. A retained supplier resolves the entity
 * type when requested, allowing registry initialization to finish before use;
 * placement adds a small physics clearance above the clicked surface.
 *
 * @author IWareQ
 */
@Slf4j
public class ItemSpawnEggBaseComponentImpl extends ItemBaseComponentImpl implements ItemSpawnEggBaseComponent {
    /**
     * {@code entityType} resolves the spawn target each time it is requested.
     * The constructors retain the supplier rather than resolving its result
     * during component construction.
     */
    protected final Supplier<EntityType<?>> entityType;

    /**
     * Creates a spawn egg component with deferred entity-type resolution.
     * The component retains {@code entityTypeSupplier} and invokes it for each
     * type lookup, so the supplier must be usable when the egg is inspected or placed.
     */
    public ItemSpawnEggBaseComponentImpl(ItemStackInitInfo initInfo, Supplier<EntityType<?>> entityTypeSupplier) {
        super(initInfo);
        this.entityType = entityTypeSupplier;
    }

    /**
     * Creates a spawn egg component that resolves the supplied entity identifier
     * on demand. {@code entityId} must be non-{@code null}; its current entity
     * type is retrieved later through the retained supplier.
     */
    public ItemSpawnEggBaseComponentImpl(ItemStackInitInfo initInfo, EntityId entityId) {
        this(initInfo, entityId::getEntityType);
    }

    /**
     * Returns the entity type obtained from the retained supplier.
     * The lookup invokes the supplier each time and returns its result directly,
     * including {@code null} if the supplier currently provides no type.
     */
    @Override
    public EntityType<?> getEntityType() {
        return entityType.get();
    }

    /**
     * Creates and adds the egg's entity at the clicked world position.
     * A {@code null} interaction returns {@code false}; successful creation returns
     * {@code true} without reducing this stack or consulting the inherited
     * item-use handlers. The resolved entity type must be available for creation.
     */
    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var clickedPos = interactInfo.clickedPos();
        var clickedBlockPos = interactInfo.clickedBlockPos();
        var entity = getEntityType().createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(
                                (double) clickedPos.x() + clickedBlockPos.x(),
                                // Keep the spawn just above the clicked surface by the physics engine's
                                // clearance margin so the entity does not begin embedded in that surface.
                                (double) clickedPos.y() + clickedBlockPos.y() + AllayEntityPhysicsEngine.FAT_AABB_MARGIN,
                                (double) clickedPos.z() + clickedBlockPos.z()
                        )
                        .build()
        );
        dimension.getEntityManager().addEntity(entity);
        return true;
    }
}
