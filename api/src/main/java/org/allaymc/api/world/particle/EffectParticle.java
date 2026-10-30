package org.allaymc.api.world.particle;

import java.awt.*;

/**
 * {@code EffectParticle} describes the colored spell particles used around
 * entities with effects. The runtime reads the supplied RGB value directly
 * during encoding, so callers provide a color and independently choose the
 * position where the effect appears.
 *
 * @param color required spell particle tint
 *
 * @author daoge_cmd
 */
public record EffectParticle(Color color) implements Particle {
}
