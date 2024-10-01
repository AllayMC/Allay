package org.allaymc.server.utils;

import org.apache.commons.rng.RestorableUniformRandomProvider;
import org.apache.commons.rng.sampling.distribution.ContinuousSampler;
import org.apache.commons.rng.sampling.distribution.GaussianSampler;
import org.apache.commons.rng.sampling.distribution.ZigguratSampler;
import org.apache.commons.rng.simple.RandomSource;

/**
 * @author daoge_cmd | Cool_Loong
 */
public final class AllayRandom {
    private final RestorableUniformRandomProvider provider;
    private final ContinuousSampler sampler;

    public AllayRandom() {
        provider = RandomSource.MT.create();
        sampler = GaussianSampler.of(ZigguratSampler.NormalizedGaussian.of(RandomSource.ISAAC.create()),
                0, 0.33333);
    }

    public AllayRandom(long seed) {
        provider = RandomSource.MT.create(seed);
        sampler = GaussianSampler.of(ZigguratSampler.NormalizedGaussian.of(RandomSource.ISAAC.create()),
                0, 0.33333);
    }

    public int nextInt(int min, int max) {
        return provider.nextInt(min, max + 1);
    }

    public int nextRange(int min, int max) {
        return provider.nextInt(min, max + 1);
    }

    public int nextBoundedInt(int bound) {
        return nextInt(bound);
    }

    public int nextInt(int max) {
        return provider.nextInt(max + 1);
    }

    public int nextInt() {
        return provider.nextInt();
    }

    public long nextLong() {
        return provider.nextLong();
    }

    public double nextDouble() {
        return provider.nextDouble();
    }

    public double nextGaussian() {
        double sample = sampler.sample();
        return Math.min(1, Math.max(sample, -1));
    }

    public float nextFloat() {
        return provider.nextFloat();
    }

    public boolean nextBoolean() {
        return provider.nextBoolean();
    }
}
