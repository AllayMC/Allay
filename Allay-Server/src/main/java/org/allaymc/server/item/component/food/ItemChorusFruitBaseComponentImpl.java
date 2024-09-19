package org.allaymc.server.item.component.food;

import org.allaymc.api.block.component.BlockLiquidComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3f;
import org.joml.Vector3f;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author IWareQ, PowerNukkitX
 */
public class ItemChorusFruitBaseComponentImpl extends ItemFoodComponentImpl {
    public ItemChorusFruitBaseComponentImpl() {
        super(4, 2.4f);
    }

    @Override
    public void onEaten(EntityPlayer player) {
        super.onEaten(player);

        var min = player.getLocation().sub(8, 8, 8, new Vector3f());
        var max = min.add(16, 16, 16, new Vector3f());

        var random = ThreadLocalRandom.current();
        var dimension = player.getDimension();
        for (int attempts = 0; attempts < 128; attempts++) {
            var x = (int) random.nextFloat(min.x(), max.x);
            var y = (int) random.nextFloat(min.y(), max.y());
            var z = (int) random.nextFloat(min.z(), max.z());

            if (y < 0) continue;

            // TODO: use height map

            while (y >= 0 && !dimension.getBlockState(x, y + 1, z).getBlockType().getMaterial().isSolid()) {
                y--;
            }
            y++; // Back up to non-solid

            var blockUp = dimension.getBlockState(x, y + 1, z);
            var blockUp2 = dimension.getBlockState(x, y + 2, z);

            if (
                    blockUp.getBlockType().getMaterial().isSolid() || blockUp instanceof BlockLiquidComponent ||
                    blockUp2.getBlockType().getMaterial().isSolid() || blockUp2 instanceof BlockLiquidComponent
            ) {
                continue;
            }

            player.teleport(new Location3f(x + 0.5f, y + 1, z + 0.5f, dimension));
            break;
        }
    }
}
