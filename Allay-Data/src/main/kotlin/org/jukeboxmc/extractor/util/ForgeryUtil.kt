/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor.util

import com.google.gson.JsonObject
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSObject
import com.nimbusds.jose.Payload
import com.nimbusds.jose.crypto.ECDSASigner
import com.nimbusds.jose.jwk.Curve
import java.net.URI
import java.security.KeyPair
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author Kaooot
 * @version 1.0
 */
class ForgeryUtil {

    companion object {
        /**
         * Forges chain data. The first chain must be self-signed, the others are signed by Mojang and are therefore not changed
         */
        fun forgeChain(keyPair: KeyPair, loginChain: JsonObject): List<String> {
            val forgedChain = ArrayList<String>()
            val chain = JWSObject.parse(loginChain["chain"].asJsonArray.first().toString())

            val map = mapOf(
                "certificateAuthority" to true,
                "identityPublicKey" to chain.header.x509CertURL.toString(),
                "exp" to (System.currentTimeMillis() / 1000) + TimeUnit.MINUTES.toSeconds(6),
                "nbf" to (System.currentTimeMillis() / 1000) - TimeUnit.MINUTES.toSeconds(6)
            )

            val jwt = JWSObject(
                JWSHeader.Builder(JWSAlgorithm.ES384)
                    .x509CertURL(URI.create(Base64.getEncoder().encodeToString(keyPair.public.encoded)))
                    .build(), Payload(map)
            )

            jwt.sign(ECDSASigner(keyPair.private, Curve.P_384))

            forgedChain.add(jwt.serialize())
            forgedChain.add(loginChain["chain"].asJsonArray.first().asString)
            forgedChain.add(loginChain["chain"].asJsonArray[1].asString)

            return forgedChain
        }

        /**
         * Forges skin data with the local key pair
         */
        fun forgeSkin(keyPair: KeyPair, skinData: String, address: String, thirdPartyName: String): String {
            val map = JWSObject.parse(skinData).payload.toJSONObject()
            map["DeviceOS"] = 1
            map["DeviceModel"] = ""
            map["ServerAddress"] = address
            map["ThirdPartyName"] = thirdPartyName

            val jwt = JWSObject(
                JWSHeader.Builder(JWSAlgorithm.ES384)
                    .x509CertURL(URI.create(Base64.getEncoder().encodeToString(keyPair.public.encoded)))
                    .build(), Payload(map)
            )

            jwt.sign(ECDSASigner(keyPair.private, Curve.P_384))

            return jwt.serialize()
        }
    }
}