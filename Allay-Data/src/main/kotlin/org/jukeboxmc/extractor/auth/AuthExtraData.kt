/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor.auth

import com.google.gson.JsonObject
import com.nimbusds.jwt.SignedJWT
import java.util.*

/**
 * @author Kaooot
 * @version 1.0
 */
data class AuthExtraData(val displayName: String, val identity: UUID, val xuid: String, val titleId: String) {

    companion object {
        /**
         * Creates a fresh object from the given login chain json
         *
         * @param loginChain the serialized data which is used to create this object
         *
         * @return a fresh auth extra data object
         */
        fun fromChain(loginChain: JsonObject): AuthExtraData {
            val extraData = SignedJWT.parse(loginChain.getAsJsonArray("chain").get(1).asString)
                .payload.toJSONObject()["extraData"] as Map<*, *>

            return AuthExtraData(
                extraData["displayName"].toString(),
                UUID.fromString(extraData["identity"].toString()),
                extraData["XUID"].toString(),
                extraData["titleId"].toString()
            )
        }
    }
}