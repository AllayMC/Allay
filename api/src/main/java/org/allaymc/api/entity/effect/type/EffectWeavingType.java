package org.allaymc.api.entity.effect.type;

import com.google.common.collect.AbstractIterator;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.gamerule.GameRule;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.data.BlockId.WEB;

/**
 * @author IWareQ
 */
public class EffectWeavingType extends AbstractEffectType {
    public EffectWeavingType() {
        super(33, new Identifier("minecraft:weaving"), true);
    }

    @Override
    public void onEntityDies(Entity entity, EffectInstance effectInstance) {
        if (
                entity.getWorld().getWorldData().getGameRule(GameRule.MOB_GRIEFING).equals(true) ||
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
                    !entity.getDimension().getBlockState(posBelow).getBlockStateData().shape().isEdgeFull(BlockFace.UP)
            ) continue;

            posToSpawnWeb.add(pos);
            if (posToSpawnWeb.size() >= 2) break;
        }

        var web = WEB.getBlockType().getDefaultState();
        for (var pos : posToSpawnWeb) {
            entity.getDimension().setBlockState(pos, web);
        }
    }

    private Iterable<Position3ic> randomInCube(Position3ic around) {
        var minX = around.x() - 1;
        var minY = around.y() - 1;
        var minZ = around.z() - 1;
        return () -> new AbstractIterator<>() {
            int counter = 15;

            @Override
            protected Position3ic computeNext() {
                if (counter <= 0) {
                    return this.endOfData();
                }
                counter--;
                return new Position3i(
                        minX + ThreadLocalRandom.current().nextInt(3),
                        minY + ThreadLocalRandom.current().nextInt(3),
                        minZ + ThreadLocalRandom.current().nextInt(3),
                        around.dimension()
                );
            }
        };
    }
}
