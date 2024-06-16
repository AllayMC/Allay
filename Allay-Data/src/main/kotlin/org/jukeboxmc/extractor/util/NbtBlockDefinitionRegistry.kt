/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor.util

import org.cloudburstmc.nbt.NbtMap
import org.cloudburstmc.nbt.NbtUtils
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition
import org.cloudburstmc.protocol.common.DefinitionRegistry
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * @author Kaooot
 * @version 1.0
 */
class NbtBlockDefinitionRegistry(definitions: List<NbtMap>, hashed: Boolean) : DefinitionRegistry<BlockDefinition> {

    private val definitions: MutableMap<Int, NbtBlockDefinition> = HashMap()

    init {
        var counter = 0
        for (definition in definitions) {
            val runtimeId = if (hashed) this.hash(definition) else counter++

            this.definitions[runtimeId] = NbtBlockDefinition(runtimeId, definition)
        }
    }

    fun add(definition: NbtBlockDefinition) {
        this.definitions[definition.runtimeId] = definition
    }

    override fun getDefinition(runtimeId: Int): BlockDefinition {
        return this.definitions[runtimeId] as BlockDefinition
    }

    override fun isRegistered(definition: BlockDefinition): Boolean =
        this.definitions[definition.runtimeId] == definition

    data class NbtBlockDefinition(val rId: Int, val nbtTag: NbtMap) : BlockDefinition {
        override fun getRuntimeId(): Int {
            return this.rId
        }
    }

    /**
     * All states have to be in order before the runtime id hash can be created.
     * The exception is the identifier "minecraft:unknown" whose runtime id will always be -2.
     *
     * @param block the block state to create the hash from
     *
     * @return a fresh calculated runtimeId hash
     */
    private fun hash(block: NbtMap): Int {
        if (block.getString("name").equals("minecraft:unknown", true)) {
            return -2
        }

        val states = TreeMap(block.getCompound("states"))
        val statesBuilder = NbtMap.builder()
        statesBuilder.putAll(states)

        val tag = NbtMap.builder()
            .putString("name", block.getString("name"))
            .putCompound("states", statesBuilder.build())
            .build()

        ByteArrayOutputStream().use { stream ->
            NbtUtils.createWriterLE(stream).use {
                it.writeTag(tag)

                return BlockPaletteHashUtil.fnv1a_32(stream.toByteArray())
            }
        }
    }
}