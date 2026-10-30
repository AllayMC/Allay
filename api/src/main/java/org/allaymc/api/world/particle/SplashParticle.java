package org.allaymc.api.world.particle;

import java.awt.*;

/**
 * {@code SplashParticle} describes the colored effect emitted when a splash
 * potion breaks. The runtime reads the supplied RGB value for the
 * potion-splash payload, so callers provide a color and separately choose the
 * position where the splash appears.
 *
 * @param color required potion splash tint
 *
 * @author daoge_cmd
 */
public record SplashParticle(Color color) implements Particle {
}
