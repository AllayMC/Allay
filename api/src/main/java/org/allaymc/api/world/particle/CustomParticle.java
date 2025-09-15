package org.allaymc.api.world.particle;

/**
 * CustomParticle is a particle that accept a string name of the particle, which can be either
 * the build-in particle name or custom particle that is added by resource pack. It also accepts
 * a number of MoLang variables that may be applicable to the particle spawn.
 *
 * @param particleName    the name of the particle, can be either the name of build-in particle or custom particle
 * @param moLangVariables an encoded JSON map of MoLang variables that may be applicable to the particle spawn. This can
 *                        just be left {@code null} in most cases
 * @author daoge_cmd
 */
public record CustomParticle(String particleName, String moLangVariables) implements Particle {
    public CustomParticle(String particleName) {
        this(particleName, null);
    }
}
