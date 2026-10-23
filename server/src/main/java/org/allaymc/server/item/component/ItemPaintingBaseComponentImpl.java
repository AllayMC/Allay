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
 * {@code ItemPaintingBaseComponentImpl} places a randomly selected painting on a
 * solid block's horizontal face. The component filters painting kinds through
 * their support and collision checks before constructing an entity or consuming
 * the player's item.
 *
 * <p>
 * Candidate selection maximizes width plus height rather than area. When a larger
 * sum fits, the component discards every previously collected smaller candidate;
 * equal sums remain eligible together. This keeps horizontal and vertical
 * paintings with the same summed dimensions equally eligible in irregular
 * available space. A uniform random choice among the retained kinds determines
 * the painting, and an empty candidate list leaves the world unchanged.
 *
 * @author PMMP Team | daoge_cmd
 */
public class ItemPaintingBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates a painting item component from the supplied stack initialization.
     */
    public ItemPaintingBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Selects a fitting painting and creates it against the clicked horizontal face.
     * The caller must supply interaction information; adventure and spectator modes,
     * an unsuitable support face, or no fitting painting return {@code false}.
     *
     * <p>
     * The candidate list retains only kinds with the largest fitting width-plus-height
     * sum. A larger fitting kind clears smaller candidates, while equal sums remain
     * for uniform random selection. Success adds the entity, plays placement sound,
     * and attempts to consume the held item; the inherited event result is ignored.
     */
    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        super.useItemOnBlock(dimension, placeBlockPos, interactInfo);

        var player = interactInfo.player();
        if (player.getGameMode() == GameMode.ADVENTURE || player.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }

        var clickedBlock = interactInfo.getClickedBlock();
        if (!clickedBlock.getBlockStateData().isSolid()) {
            return false;
        }

        var face = interactInfo.blockFace();
        if (!face.isHorizontal()) {
            return false;
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
                    // A larger summed dimension supersedes smaller candidates;
                    // equal sums retain both orientations for random selection
                    // when an irregular space admits horizontal and vertical kinds.
                    types.clear();
                }

                types.add(type);
            }
        }

        if (types.isEmpty()) {
            return false;
        }

        var entity = EntityTypes.PAINTING.createEntity(
                EntityInitInfo
                        .builder()
                        .pos(placeBlockPos)
                        .rot(face.yaw(), 0)
                        .dimension(dimension)
                        .build()
        );
        // Every retained kind fits and has the same maximal summed dimensions,
        // so a uniform draw preserves equal eligibility among those candidates.
        entity.setPaintingType(types.get(ThreadLocalRandom.current().nextInt(types.size())));
        dimension.getEntityManager().addEntity(entity);
        dimension.addSound(MathUtils.center(placeBlockPos), SimpleSound.PAINTING_PLACE);
        player.tryConsumeItemInHand();
        return true;
    }
}
