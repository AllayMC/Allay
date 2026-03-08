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
 * @author IWareQ
 */
public class EffectWeavingType extends AbstractEffectType {
    public EffectWeavingType() {
        super(33, new Identifier("minecraft:weaving"), new Color(0x78695a), true);
    }

    @Override
    public void onEntityDies(EntityLiving entity, EffectInstance effectInstance) {
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
            if (posToSpawnWeb.size() >= 2) break;
        }

        var web = WEB.getBlockType().getDefaultState();
        posToSpawnWeb.forEach(pos -> entity.getDimension().setBlockState(pos, web));
    }

    private Iterable<Position3ic> randomInCube(Position3ic around) {
        var random = ThreadLocalRandom.current();
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
                        minX + random.nextInt(3),
                        minY + random.nextInt(3),
                        minZ + random.nextInt(3),
                        around.dimension()
                );
            }
        };
    }
}
