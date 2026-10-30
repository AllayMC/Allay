package org.allaymc.api.world.particle;

import java.awt.*;

/**
 * {@code FlameParticle} describes a flame effect with an explicit color
 * payload. The runtime reads the supplied RGB value directly during encoding;
 * callers must supply a color because this descriptor provides no default
 * flame tint for {@code null}.
 *
 * @param color required flame particle tint
 *
 * @author daoge_cmd
 */
public record FlameParticle(Color color) implements Particle {
}
