package org.allaymc.server.item.component;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.data.PaintingType;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.entity.impl.EntityPaintingImpl;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author PMMP Team | daoge_cmd
 */
public class ItemPaintingBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemPaintingBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void rightClickItemOn(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        super.rightClickItemOn(dimension, placeBlockPos, interactInfo);

        var player = interactInfo.player();
        if (player.getGameMode() == GameMode.ADVENTURE || player.getGameMode() == GameMode.SPECTATOR) {
            return;
        }

        var clickedBlock = interactInfo.getClickedBlock();
        if (!clickedBlock.getBlockStateData().isSolid()) {
            return;
        }

        var face = interactInfo.blockFace();
        if (!face.isHorizontal()) {
            return;
        }

        var types = new ArrayList<PaintingType>();
        var totalDimension = 0;
        for (var type : PaintingType.values()) {
            var currentTotalDimension = type.getHeight() + type.getWidth();
            if (currentTotalDimension < totalDimension) {
                continue;
            }

            if (EntityPaintingImpl.canPlace(type, dimension, placeBlockPos, face, true)) {
                if (currentTotalDimension > totalDimension) {
                    totalDimension = currentTotalDimension;
                    // This drops all type possibilities smaller than this. We use the total of height plus width
                    // to allow equal chance of horizontal/vertical paintings when there is an L-shape of space
                    // available.
                    types.clear();
                }

                types.add(type);
            }
        }

        if (types.isEmpty()) {
            // No available painting type found
            return;
        }

        var entity = EntityTypes.PAINTING.createEntity(
                EntityInitInfo
                        .builder()
                        .pos(placeBlockPos)
                        .rot(face.yaw(), 0)
                        .dimension(dimension)
                        .build()
        );
        // Pick a random one from all possible types
        entity.setPaintingType(types.get(ThreadLocalRandom.current().nextInt(types.size())));
        dimension.getEntityManager().addEntity(entity);
        dimension.addSound(MathUtils.center(placeBlockPos), SimpleSound.PAINTING_PLACE);
        player.tryConsumeItemInHand();
    }
}
