package org.allaymc.api.world.particle;

import org.allaymc.api.item.ItemStack;

/**
 * {@code ItemBreakParticle} describes item-textured fragments such as a
 * breaking egg. The runtime combines the retained stack's runtime item ID and
 * metadata when encoding the effect, so changes to that shared stack can alter
 * a pending particle.
 *
 * @param itemStack {@code itemStack} retains the texture source. Callers must
 * keep its type and metadata stable until encoding finishes.
 *
 * @author daoge_cmd
 */
public record ItemBreakParticle(ItemStack itemStack) implements Particle {
}
