package org.allaymc.api.world.particle;

import java.awt.*;

/**
 * EffectParticle is a particle that shows up around an entity when it has effects on.
 *
 * @param color the color of the particle
 * @author daoge_cmd
 */
public record EffectParticle(Color color) implements Particle {
}
