package org.allaymc.api.item.component;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.type.EntityType;

/**
 * Component for bucket items that can store a liquid and optionally a captured entity.
 *
 * @author daoge_cmd
 */
public interface ItemBucketComponent extends ItemComponent {
    /**
     * Get the liquid inside the bucket.
     *
     * @return the liquid inside the bucket, or air type if the bucket is empty
     */
    BlockType<?> getLiquidType();

    /**
     * Get the entity inside the bucket.
     *
     * @return the entity inside the bucket, or {@code null} if the bucket does not hold an entity
     */
    EntityType<?> getEntityType();

    /**
     * Check if the bucket is empty.
     *
     * @return {@code true} if the bucket is empty, {@code false} otherwise.
     */
    default boolean isEmpty() {
        return getLiquidType() == BlockTypes.AIR && getEntityType() == null;
    }
}
