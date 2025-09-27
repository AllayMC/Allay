package org.allaymc.api.world.particle;

import org.allaymc.api.item.ItemStack;

/**
 * ItemBreakParticle is a particle shown when an item break, such as an egg smashing on a block.
 *
 * @author daoge_cmd
 */
public record ItemBreakParticle(ItemStack itemStack) implements Particle {
}
