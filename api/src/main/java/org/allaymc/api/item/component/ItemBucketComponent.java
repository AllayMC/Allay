package org.allaymc.api.item.component;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.type.EntityType;

/**
 * {@code ItemBucketComponent} identifies the liquid and optional entity carried by
 * a bucket item. An empty bucket uses the air block type and no entity type;
 * the shared emptiness check requires both conditions before treating the bucket
 * as available for filling.
 *
 * @author daoge_cmd
 */
public interface ItemBucketComponent extends ItemComponent {
    /**
     * Returns the block type representing this bucket's liquid contents.
     * An empty bucket returns {@code BlockTypes.AIR}, which {@code isEmpty} combines
     * with the absence of a captured entity type.
     */
    BlockType<?> getLiquidType();

    /**
     * Returns the entity type carried by this bucket, if any.
     * The result is {@code null} when no entity is stored; liquid contents can still
     * make the bucket nonempty.
     */
    EntityType<?> getEntityType();

    /**
     * Returns whether this bucket contains neither liquid nor an entity.
     * The result requires {@code BlockTypes.AIR} from {@code getLiquidType} and
     * {@code null} from {@code getEntityType}; either stored content makes it {@code false}.
     */
    default boolean isEmpty() {
        return getLiquidType() == BlockTypes.AIR && getEntityType() == null;
    }
}
