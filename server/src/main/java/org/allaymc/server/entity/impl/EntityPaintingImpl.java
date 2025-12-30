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

public class EntityPaintingImpl extends EntityImpl implements EntityPainting {

    @Delegate
    private EntityPaintingBaseComponent paintingBaseComponent;
    @Delegate
    private EntityLivingComponent livingComponent;

    public EntityPaintingImpl(EntityInitInfo initInfo,
                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    /**
     * Determines whether a painting of this type can be placed at the specified block position and facing.
     * The method checks whether the blocks below and opposite the painting's placement are solid, and,
     * optionally, whether the space overlaps with other painting entities.
     *
     * @param paintingType the painting type to check for collision with other paintings
     * @param dimension    the dimension where the painting is being placed
     * @param paintingPos  the painting's position
     * @param blockFace    the face of the block where the painting should be oriented
     * @param checkOverlap {@code true} if the method should check for collisions with other entities, {@code false} otherwise
     * @return {@code true} if the painting can be placed at the specified position and facing, {@code false} otherwise
     */
    public static boolean canPlace(PaintingType paintingType, Dimension dimension, Vector3ic paintingPos, BlockFace blockFace, boolean checkOverlap) {
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
                    // Slightly shrink the aabb so that the paintings can be placed side by side
                    entity.getOffsetAABB().expand(-FAT_AABB_MARGIN, new AABBd()).intersectsAABB(aabb)) {
                    return false;
                }
            }
        }

        return true;
    }
}
