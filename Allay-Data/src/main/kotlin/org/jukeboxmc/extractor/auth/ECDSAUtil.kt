/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor.auth

import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.Signature

/**
 * @author Kaooot
 * @version 1.0
 */
class ECDSAUtil {

    companion object {
        private val signature = Signature.getInstance("SHA256withECDSA")
        private val keypairGenerator = KeyPairGenerator.getInstance("EC")

        fun generateKeyPair(): KeyPair = this.keypairGenerator.generateKeyPair()

        fun sign(data: ByteArray, privateKey: PrivateKey): Array<BigInteger> {
            this.signature.initSign(privateKey)
            this.signature.update(data)

            return this.convertDERtoRS(this.signature.sign())
        }

        private fun convertDERtoRS(der: ByteArray): Array<BigInteger> {
            if (der.size < 8 || der.first().toInt() != 0x30) {
                throw RuntimeException("Invalid DER signature")
            }

            var offsetR = 4
            var lengthR = der[offsetR - 1]
            var offsetS = offsetR + lengthR + 2

            if (der[offsetR].toInt() == 0x0) {
                offsetR++
                lengthR--
            }

            var lengthS = der[offsetS - 1]

            if (der[offsetS].toInt() == 0x0) {
                offsetS++
                lengthS--
            }

            return arrayOf(
                BigInteger(1, der.copyOfRange(offsetR, offsetR + lengthR)),
                BigInteger(1, der.copyOfRange(offsetS, offsetS + lengthS))
            )
        }
    }
}