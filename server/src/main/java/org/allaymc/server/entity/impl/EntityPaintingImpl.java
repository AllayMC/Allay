package org.allaymc.server.entity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPaintingBaseComponent;
import org.allaymc.api.entity.data.PaintingType;
import org.allaymc.api.entity.interfaces.EntityPainting;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.ComponentProvider;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;

import java.util.List;

import static org.allaymc.server.world.physics.AllayEntityPhysicsEngine.FAT_AABB_MARGIN;

/**
 * {@code EntityPaintingImpl} implements the painting API through supplied
 * living and artwork components and checks whether a proposed painting fits its
 * world footprint. Component wiring delegates entity behavior to the same
 * instances, while {@code canPlace} validates every occupied block and its
 * supporting block using the selected painting dimensions and horizontal face.
 *
 * <p>
 * The placement anchor matches {@code PaintingType.getAABB}, including
 * even-sized artwork. Optional overlap checking first queries nearby physics
 * entities, then shrinks an existing painting's collision margin before testing
 * intersection. This second check permits adjacent paintings whose expanded
 * collision boxes would otherwise appear to overlap.
 * {@code paintingBaseComponent} and {@code livingComponent} retain the selected
 * shared component instances for all delegated gameplay operations.
 */
public class EntityPaintingImpl extends EntityImpl implements EntityPainting {

    /**
     * Painting artwork-variant delegate.
     */
    @Delegate
    private EntityPaintingBaseComponent paintingBaseComponent;
    /**
     * Health and living-state delegate.
     */
    @Delegate
    private EntityLivingComponent livingComponent;

    /**
     * Creates a component-backed painting from the supplied initialization
     * data. Superclass wiring constructs providers and resolves each delegate
     * field before returning; the supplied providers must yield exactly one
     * assignable component for every required delegate type.
     */
    public EntityPaintingImpl(EntityInitInfo initInfo,
                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    /**
     * Returns whether the painting's footprint has empty space and solid
     * backing. The caller supplies a horizontal {@code blockFace}; vertical
     * faces fail when the method derives its horizontal axis. Every footprint
     * block must be non-solid, and the block directly behind each one must be
     * solid.
     *
     * <p>
     * When {@code checkOverlap} is {@code true}, the method also rejects
     * intersection with another painting after removing that entity's extra
     * physics margin. Other entity types do not reject placement through this
     * check, and adjacent artwork can share a boundary without becoming an
     * overlap.
     *
     * @throws IllegalStateException if {@code blockFace} is not horizontal
     */
    public static boolean canPlace(PaintingType paintingType, Dimension dimension, Vector3ic paintingPos, BlockFace blockFace, boolean checkOverlap) {
        // Match PaintingType.getAABB's anchor for odd and even artwork sizes so
        // backing-block checks and overlap queries cover the same world footprint.
        var horizontalStart = (int) (Math.ceil(paintingType.getWidth() / 2d) - 1);
        var verticalStart = (int) (Math.ceil(paintingType.getHeight() / 2d) - 1);

        var rotatedFace = blockFace.rotateYCCW();
        var oppositeFace = blockFace.opposite();

        var startPos = BlockFace.DOWN.offsetPos(rotatedFace.opposite().offsetPos(paintingPos, horizontalStart), verticalStart);

        for (var w = 0; w < paintingType.getWidth(); w++) {
            for (var h = 0; h < paintingType.getHeight(); h++) {
                var pos = BlockFace.UP.offsetPos(rotatedFace.offsetPos(startPos, w), h);
                if (dimension.getBlockState(pos).getBlockStateData().isSolid() ||
                    !dimension.getBlockState(oppositeFace.offsetPos(pos)).getBlockStateData().isSolid()) {
                    return false;
                }
            }
        }

        if (checkOverlap) {
            var aabb = paintingType.getAABB(blockFace).translate(paintingPos.x(), paintingPos.y(), paintingPos.z(), new AABBd());
            for (var entity : dimension.getEntityManager().getPhysicsService().computeCollidingEntities(aabb)) {
                if (entity.getEntityType() == EntityTypes.PAINTING &&
                    // Remove the existing painting's physics margin before intersection,
                    // otherwise adjacent artwork would be rejected by the expanded box.
                    entity.getOffsetAABB().expand(-FAT_AABB_MARGIN, new AABBd()).intersectsAABB(aabb)) {
                    return false;
                }
            }
        }

        return true;
    }
}
