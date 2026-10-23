package org.allaymc.server.entity.ai.route.posevaluator;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * {@code WalkingPosEvaluator} checks ground support for walking routes while
 * rejecting lava and cactus. Water support is accepted directly; other blocks
 * use their collision-shape top to position the entity's standing check,
 * keeping partial-height support aligned with the entity's collision bounds.
 *
 * @author daoge_cmd
 */
public class WalkingPosEvaluator implements GroundPosEvaluator {

    /**
     * Returns whether the support block is suitable for walking. Lava and
     * cactus fail, water succeeds without a standing-volume check, and other
     * blocks delegate to {@code canStandSafely} at the support's
     * collision-derived height.
     */
    @Override
    public boolean evaluate(EntityIntelligent entity, Block block) {
        var blockType = block.getBlockType();

        if (blockType.hasBlockTag(BlockTags.LAVA)) return false;

        if (blockType == BlockTypes.CACTUS) return false;

        if (blockType.hasBlockTag(BlockTags.WATER)) return true;

        // Position the standing check at the support collision top so slabs and
        // other partial-height blocks align the entity bounds with their actual surface.
        var pos = block.getPosition();
        var data = block.getBlockStateData();
        double standingY = data.hasCollision() ? pos.y() + data.collisionShape().unionAABB().maxY() : pos.y() + 1;
        return entity.canStandSafely(pos.x() + 0.5, standingY, pos.z() + 0.5, pos.dimension());
    }
}
