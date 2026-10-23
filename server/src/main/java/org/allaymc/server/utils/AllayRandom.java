package org.allaymc.server.utils;

import org.apache.commons.rng.RestorableUniformRandomProvider;
import org.apache.commons.rng.sampling.distribution.ContinuousSampler;
import org.apache.commons.rng.sampling.distribution.GaussianSampler;
import org.apache.commons.rng.sampling.distribution.ZigguratSampler;
import org.apache.commons.rng.simple.RandomSource;

/**
 * {@code AllayRandom} supplies uniform random values and a bounded Gaussian-like sample for
 * server calculations. Uniform methods share a Mersenne Twister provider, while Gaussian
 * sampling uses a separately seeded ISAAC source and clamps samples to the interval from
 * minus one to one. The class does not synchronize access to either source.
 *
 * @author daoge_cmd | Cool_Loong
 */
public final class AllayRandom {
    // provider and sampler advance independent random streams. A seeded provider therefore
    // makes uniform values reproducible without fixing the Gaussian sampler's independently created source.
    /**
     * Uniform random value source.
     */
    private final RestorableUniformRandomProvider provider;
    /**
     * Independent Gaussian sampling source.
     */
    private final ContinuousSampler sampler;

    /**
     * Creates independently seeded uniform and Gaussian providers for this random instance.
     */
    public AllayRandom() {
        provider = RandomSource.MT.create();
        // The one-third deviation concentrates most samples inside the later unit clamp.
        sampler = GaussianSampler.of(ZigguratSampler.NormalizedGaussian.of(RandomSource.ISAAC.create()),
                0, 0.33333);
    }

    /**
     * Creates a seeded uniform provider and an independently seeded Gaussian sampler. Reusing
     * {@code seed} reproduces the uniform stream, but it does not make {@code nextGaussian}
     * produce the same sequence across instances.
     */
    public AllayRandom(long seed) {
        provider = RandomSource.MT.create(seed);
        // The one-third deviation concentrates most samples inside the later unit clamp.
        sampler = GaussianSampler.of(ZigguratSampler.NormalizedGaussian.of(RandomSource.ISAAC.create()),
                0, 0.33333);
    }

    /**
     * Returns a uniform integer from {@code min} through {@code max}, including both bounds.
     * The upper bound must be below {@code Integer.MAX_VALUE} because the provider receives
     * {@code max + 1} as its exclusive endpoint.
     */
    public int nextInt(int min, int max) {
        // Convert the public inclusive maximum to the provider's exclusive upper endpoint.
        return provider.nextInt(min, max + 1);
    }

    /**
     * Returns a uniform integer from {@code min} through {@code max}, including both bounds.
     * The upper bound must be below {@code Integer.MAX_VALUE} because the provider receives
     * {@code max + 1} as its exclusive endpoint.
     */
    public int nextRange(int min, int max) {
        // Convert the public inclusive maximum to the provider's exclusive upper endpoint.
        return provider.nextInt(min, max + 1);
    }

    /**
     * Returns a uniform integer from zero through {@code bound}, including the endpoint. The
     * bound must be nonnegative and below {@code Integer.MAX_VALUE}; despite the method name,
     * the bound is inclusive.
     */
    public int nextBoundedInt(int bound) {
        return nextInt(bound);
    }

    /**
     * Returns a uniform integer from zero through {@code max}, including the endpoint. The bound
     * must be nonnegative and below {@code Integer.MAX_VALUE} so converting it to an exclusive
     * provider endpoint does not overflow.
     */
    public int nextInt(int max) {
        // Convert the inclusive caller endpoint to the provider's exclusive bound.
        return provider.nextInt(max + 1);
    }

    /**
     * Returns an unrestricted integer from the retained uniform random provider.
     */
    public int nextInt() {
        return provider.nextInt();
    }

    /**
     * Returns an unrestricted long from the retained uniform random provider.
     */
    public long nextLong() {
        return provider.nextLong();
    }

    /**
     * Returns a uniform double from zero inclusive to one exclusive.
     */
    public double nextDouble() {
        return provider.nextDouble();
    }

    /**
     * Returns a zero-mean Gaussian sample with standard deviation approximately one third,
     * clamped to the interval from minus one to one. Clamping accumulates out-of-range samples
     * at the endpoints rather than resampling them.
     */
    public double nextGaussian() {
        double sample = sampler.sample();
        // Clamp Gaussian tails to preserve the bounded displacement expected by callers.
        return Math.min(1, Math.max(sample, -1));
    }

    /**
     * Returns a uniform float from zero inclusive to one exclusive.
     */
    public float nextFloat() {
        return provider.nextFloat();
    }

    /**
     * Returns a boolean from the retained uniform random value provider.
     */
    public boolean nextBoolean() {
        return provider.nextBoolean();
    }
}
