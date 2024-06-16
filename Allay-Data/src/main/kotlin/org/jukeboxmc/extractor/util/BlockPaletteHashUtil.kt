/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor.util

/**
 * @author Kaooot
 * @version 1.0
 */
class BlockPaletteHashUtil {

    companion object {
        private const val fnv1_32_init: Int = 0x811c9dc5.toInt()
        private const val fnv1_32_prime: Int = 0x01000193

        /**
         * Creates a hashed runtime identifier from the raw nbt byte data. Hashed block runtime ids are persistent
         * across versions which should make support for custom blocks easier.
         *
         * @return a hashed block runtime id
         */
        fun fnv1a_32(data: ByteArray): Int {
            var hash: Int = this.fnv1_32_init

            for (datum in data) {
                hash = hash xor (datum.toInt() and 0xff)
                hash *= this.fnv1_32_prime
            }

            return hash
        }
    }
}