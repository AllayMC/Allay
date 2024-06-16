/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor.util

import org.cloudburstmc.nbt.NbtMap
import org.cloudburstmc.nbt.NbtUtils
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

/**
 * @author Kaooot
 * @version 1.0
 */
class ExtractionUtil {

    companion object {
        /**
         * Writes nbt data to a specified file
         */
        fun writeNBT(nbtMap: NbtMap, file: File) {
            FileOutputStream(file).use { stream ->
                NbtUtils.createGZIPWriter(stream).use {
                    it.writeTag(nbtMap)
                }
            }
        }

        /**
         * Writes json data to a specified file
         */
        fun writeJson(json: String, file: File) {
            FileOutputStream(file).use {
                it.write(json.toByteArray())
            }
        }

        /**
         * Converts a nbt tag to a base64 encoded string
         */
        fun nbtToBase64(nbtMap: NbtMap): String {
            ByteArrayOutputStream().use { stream ->
                NbtUtils.createWriterLE(stream).use {
                    it.writeTag(nbtMap)

                    return Base64.getEncoder().encodeToString(stream.toByteArray())
                }
            }
        }
    }
}