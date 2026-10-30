package org.allaymc.server.entity.effect;

import com.google.common.collect.AbstractIterator;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.gamerule.GameRule;

import java.awt.*;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.server.block.data.BlockId.WEB;

/**
 * {@code EffectWeavingType} represents weaving metadata and requests web
 * placement around an eligible dying entity. The current gate skips players and
 * skips execution when {@code MOB_GRIEFING} is enabled, so placement requests
 * occur only for non-player entities while that rule is disabled.
 *
 * <p>
 * The callback samples at most fifteen positions from a centered
 * three-by-three-by-three cube, stopping after two distinct positions whose
 * supporting block has a full upper collision edge. It does not require the
 * destination to be air or replaceable before requesting a web replacement.
 * Samples can repeat, leaving fewer than two selected positions even when other
 * suitable positions exist. Each world replacement can be rejected
 * independently, and effect level does not change the sampling or request
 * limits.
 *
 * @author IWareQ
 */
public class EffectWeavingType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful weaving effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectWeavingType() {
        super(33, new Identifier("minecraft:weaving"), new Color(0x78695a), true);
    }

    /**
     * Selects up to two supported sample positions and requests their
     * replacement with webs. Players and worlds with {@code MOB_GRIEFING}
     * enabled return before sampling, matching the current implementation's
     * gate.
     *
     * <p>
     * At most fifteen random samples are examined; each needs a full upper
     * collision edge below it, and repeated samples do not add a destination.
     * The callback does not check destination replaceability. It issues
     * requests afterward in set iteration order and ignores their boolean
     * results, so rejected placement does not stop later requests. A thrown
     * failure stops processing without rolling back earlier successful
     * placements.
     */
    @Override
    public void onEntityDies(EntityLiving entity, EffectInstance effectInstance) {
        // The current gate permits non-player placement only with mob griefing disabled;
        // this is the implemented condition, so enabling that rule skips all sampling here.
        if (
                entity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.MOB_GRIEFING) ||
                entity.isPlayer()
        ) return;

        var posToSpawnWeb = new HashSet<Position3ic>();
        var location = (Position3ic) new Position3i(
                (int) Math.floor(entity.getLocation().x()),
                (int) Math.floor(entity.getLocation().y()),
                (int) Math.floor(entity.getLocation().z()),
                entity.getDimension()
        );

        for (var pos : randomInCube(location)) {
            var posBelow = new Position3i(pos).add(0, -1, 0);
            if (
                    posToSpawnWeb.contains(pos) ||
                    !entity.getDimension().getBlockState(posBelow).getBlockStateData().collisionShape().isEdgeFull(BlockFace.UP)
            ) continue;

            posToSpawnWeb.add(pos);
            // Stop after two distinct supported positions, keeping the death effect spatially limited.
            if (posToSpawnWeb.size() >= 2) break;
        }

        var web = WEB.getBlockType().getDefaultState();
        posToSpawnWeb.forEach(pos -> entity.getDimension().setBlockState(pos, web));
    }

    /**
     * Returns an iterable producing fifteen random samples in the surrounding
     * three-block cube. Samples can repeat; each iterator gets its own count,
     * while all iterators use the random source and captured minimum
     * coordinates prepared here.
     */
    private Iterable<Position3ic> randomInCube(Position3ic around) {
        var random = ThreadLocalRandom.current();
        var minX = around.x() - 1;
        var minY = around.y() - 1;
        var minZ = around.z() - 1;
        return () -> new AbstractIterator<>() {
            // Fifteen attempts bound sampling work even when support tests reject every position.
            int counter = 15;

            @Override
            protected Position3ic computeNext() {
                if (counter <= 0) {
                    return this.endOfData();
                }
                counter--;
                // Each coordinate selects the adjacent block, center, or opposite neighbor.
                return new Position3i(
                        minX + random.nextInt(3),
                        minY + random.nextInt(3),
                        minZ + random.nextInt(3),
                        around.dimension()
                );
            }
        };
    }
}
