/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor.auth

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.ByteArrayOutputStream
import java.net.URI
import java.net.URLEncoder
import java.security.KeyPair
import java.security.interfaces.ECPublicKey
import java.util.*
import javax.net.ssl.HttpsURLConnection

/**
 * @author Kaooot
 * @version 1.0
 */
class MsaAuth {

    private val clientId = "0000000048183522"
    private val oAuth20ConnectUrl = URI.create("https://login.live.com/oauth20_connect.srf")
    private val oAuth20TokenUrl = URI.create("https://login.live.com/oauth20_token.srf")
    private val xboxLiveSisuUrl = URI.create("https://sisu.xboxlive.com/authorize")
    private val xboxLiveDeviceAuthUrl = URI.create("https://device.auth.xboxlive.com/device/authenticate")
    private val minecraftMultiplayerUrl = URI.create("https://multiplayer.minecraft.net/authentication")

    private var deviceCode: String? = null
    private var accessToken: String? = null
    private var refreshToken: String? = null
    private var deviceTokenProofKeyPair: KeyPair? = null
    private var deviceToken: String? = null
    private var userHash: String? = null
    private var xblToken: String? = null

    /**
     * Generates the code which is used for the authentication process
     *
     * @return a fresh device code used for authentication
     */
    fun generateAuthCode(): String {
        val params = linkedMapOf(
            "client_id" to this.clientId,
            "scope" to "service::user.auth.xboxlive.com::MBI_SSL",
            "response_type" to "device_code"
        )

        val codeResponse = this.httpsRequest(this.oAuth20ConnectUrl, params)
            ?: throw RuntimeException("The authentication code could not be generated because the code response is null")

        this.deviceCode = codeResponse["device_code"].asString

        return codeResponse["user_code"].asString
    }

    /**
     * Checks if the authentication process is finished and stores the tokens retrieved by the
     * response
     *
     * @return true, when the process is finished, otherwise false
     */
    fun checkIfFinished(): Boolean {
        val params = linkedMapOf(
            "client_id" to this.clientId,
            "grant_type" to "urn:ietf:params:oauth:grant-type:device_code",
            "device_code" to this.deviceCode
        )

        val tokenResponse = this.httpsRequest(this.oAuth20TokenUrl, params) ?: return false

        this.accessToken = tokenResponse["access_token"].asString
        this.refreshToken = tokenResponse["refresh_token"].asString

        return true
    }

    /**
     * Refreshes the access token by the given refresh token
     *
     * @param refreshToken the refresh token used to update the access token
     */
    fun refreshToken(refreshToken: String) {
        val params = linkedMapOf(
            "client_id" to this.clientId,
            "scope" to "service::user.auth.xboxlive.com::MBI_SSL",
            "grant_type" to "refresh_token",
            "refresh_token" to refreshToken
        )

        val tokenResponse = this.httpsRequest(this.oAuth20TokenUrl, params)
            ?: throw RuntimeException("The access token could not be refreshed by the given refresh token")

        this.accessToken = tokenResponse["access_token"].asString
        this.refreshToken = tokenResponse["refresh_token"].asString
    }

    fun refreshToken(): String? = this.refreshToken

    /**
     * Generates a Minecraft Bedrock Edition login chain. The generation will be successful, when
     * the authentication process was completed.
     *
     * @param publicKey     the EC public key that is used to provide the identity public key field
     * @param clientVersion the client game version
     *
     * @return a fresh minecraft login chain that is signed by Mojang
     */
    fun generateLoginChain(publicKey: ECPublicKey, clientVersion: String): JsonObject {
        if (this.xblToken == null || this.userHash == null) {
            this.requestXBLToken()
        }

        val params = linkedMapOf(
            "Authorization" to "XBL3.0 x=${this.userHash};${this.xblToken}",
            "Content-Type" to "application/json",
            "User-Agent" to "MCPE/Android",
            "Client-Version" to clientVersion
        )

        val data = JsonObject()
        data.addProperty("identityPublicKey", Base64.getEncoder().encodeToString(publicKey.encoded))

        return this.httpsRequest(this.minecraftMultiplayerUrl, params, data)
            ?: throw RuntimeException("The login chain generation failed")
    }

    private fun requestXBLToken() {
        if (this.deviceToken == null) {
            this.deviceToken = this.generateDeviceToken()
        }

        val proofKey = JsonObject()
        proofKey.addProperty("crv", "P-256")
        proofKey.addProperty("alg", "ES256")
        proofKey.addProperty("use", "sig")
        proofKey.addProperty("kty", "EC")
        proofKey.addProperty(
            "x", Base64.getUrlEncoder().withoutPadding()
                .encodeToString(
                    this.clearFirstIfNull(
                        (this.deviceTokenProofKeyPair!!.public as ECPublicKey).w.affineX.abs().toByteArray()
                    )
                )
        )
        proofKey.addProperty(
            "y", Base64.getUrlEncoder().withoutPadding()
                .encodeToString(
                    this.clearFirstIfNull(
                        (this.deviceTokenProofKeyPair!!.public as ECPublicKey).w.affineY.abs().toByteArray()
                    )
                )
        )

        val data = JsonObject()
        data.addProperty("AccessToken", "t=" + this.accessToken)
        data.addProperty("AppId", this.clientId)
        data.addProperty("DeviceToken", this.deviceToken)
        data.addProperty("Sandbox", "RETAIL")
        data.addProperty("UseModernGamertag", "true")
        data.addProperty("SiteName", "user.auth.xboxlive.com")
        data.addProperty("RelyingParty", "https://multiplayer.minecraft.net/")
        data.add("ProofKey", proofKey)

        val params = linkedMapOf(
            "X-Xbl-Contract-Version" to "1",
            "Signature" to this.sign(data.toString().replace("\\/", "/"), this.xboxLiveSisuUrl.path)
        )

        val response = this.httpsRequest(this.xboxLiveSisuUrl, params, data)
            ?: throw RuntimeException("The xbl token could not be retrieved")
        val authToken = response["AuthorizationToken"].asJsonObject
        val claim = authToken["DisplayClaims"].asJsonObject
        val userInfo = claim["xui"].asJsonArray
        val userInfoEntry = userInfo.first().asJsonObject

        this.userHash = userInfoEntry["uhs"].asString
        this.xblToken = authToken["Token"].asString
    }

    private fun generateDeviceToken(): String? {
        if (this.deviceTokenProofKeyPair == null) {
            this.deviceTokenProofKeyPair = ECDSAUtil.generateKeyPair()
        }

        val proofKey = JsonObject()
        proofKey.addProperty("crv", "P-256")
        proofKey.addProperty("alg", "ES256")
        proofKey.addProperty("use", "sig")
        proofKey.addProperty("kty", "EC")
        proofKey.addProperty(
            "x", Base64.getUrlEncoder().withoutPadding().encodeToString(
                this.clearFirstIfNull(
                    (this.deviceTokenProofKeyPair!!.public as ECPublicKey).w.affineX.abs().toByteArray()
                )
            )
        )
        proofKey.addProperty(
            "y", Base64.getUrlEncoder().withoutPadding().encodeToString(
                this.clearFirstIfNull(
                    (this.deviceTokenProofKeyPair!!.public as ECPublicKey).w.affineY.abs().toByteArray()
                )
            )
        )

        val properties = JsonObject()
        properties.addProperty("AuthMethod", "ProofOfPossession")
        properties.addProperty("Id", "{" + UUID.randomUUID() + "}")
        properties.addProperty("DeviceType", "Android")
        properties.addProperty("Version", "10")
        properties.add("ProofKey", proofKey)

        val data = JsonObject()
        data.addProperty("RelyingParty", "http://auth.xboxlive.com")
        data.addProperty("TokenType", "JWT")
        data.add("Properties", properties)

        val params = linkedMapOf(
            "X-Xbl-Contract-Version" to "1",
            "Signature" to this.sign(data.toString().replace("\\/", "/"), this.xboxLiveDeviceAuthUrl.path)
        )

        val tokenResponse = this.httpsRequest(this.xboxLiveDeviceAuthUrl, params, data)
            ?: throw RuntimeException("Could not generate the Device Token")

        this.deviceToken = tokenResponse["Token"].asString

        return this.deviceToken
    }

    private fun httpsRequest(uri: URI, params: Map<String, String?>): JsonObject? {
        var jsonObject: JsonObject? = null

        val connection = uri.toURL().openConnection() as HttpsURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        connection.doOutput = true
        connection.doInput = true

        connection.outputStream.use { stream ->
            val builder = StringBuilder()
            val iterator = params.entries.iterator()

            while (iterator.hasNext()) {
                val entry = iterator.next()

                builder.append(URLEncoder.encode(entry.key, Charsets.UTF_8))
                    .append("=")
                    .append(URLEncoder.encode(entry.value, Charsets.UTF_8))

                if (iterator.hasNext()) {
                    builder.append("&")
                }
            }

            stream.write(builder.toString().toByteArray())
            stream.flush()
        }

        if (connection.responseCode == 200) {
            connection.inputStream.reader().use {
                jsonObject = JsonParser.parseReader(it).asJsonObject
            }
        }

        connection.disconnect()

        return jsonObject
    }

    private fun httpsRequest(uri: URI, params: Map<String, String?>, data: JsonObject): JsonObject? {
        var jsonObject: JsonObject? = null

        val connection = uri.toURL().openConnection() as HttpsURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")

        params.entries.forEach { connection.setRequestProperty(it.key, it.value) }

        connection.doOutput = true
        connection.doInput = true

        connection.outputStream.use { stream ->
            stream.write(data.toString().replace("\\/", "/").toByteArray())
        }

        if (connection.responseCode == 200) {
            connection.inputStream.reader().use {
                jsonObject = JsonParser.parseReader(it).asJsonObject
            }
        }

        connection.disconnect()

        return jsonObject
    }

    private fun sign(data: String, urlPathPath: String): String {
        ByteArrayOutputStream().use { stream ->
            val currentTime = ((System.currentTimeMillis() / 1000L) + 11644473600L) * 10000000L

            stream.write(byteArrayOf(0, 0, 0, 1))
            stream.write(0)

            stream.write(
                byteArrayOf(
                    (((currentTime.shr(56)) and 0xff).toByte()),
                    (((currentTime.shr(48)) and 0xff).toByte()),
                    (((currentTime.shr(40)) and 0xff).toByte()),
                    (((currentTime.shr(32)) and 0xff).toByte()),
                    (((currentTime.shr(24)) and 0xff).toByte()),
                    (((currentTime.shr(16)) and 0xff).toByte()),
                    (((currentTime.shr(8)) and 0xff).toByte()),
                    (((currentTime) and 0xFF).toByte())
                )
            )
            stream.write(0)

            stream.write("POST".toByteArray())
            stream.write(0)

            stream.write(urlPathPath.toByteArray())
            stream.write(0)

            stream.write(ByteArray(0))
            stream.write(0)

            stream.write(data.toByteArray())
            stream.write(0)

            val rs = ECDSAUtil.sign(stream.toByteArray(), this.deviceTokenProofKeyPair!!.private)

            stream.reset()

            stream.write(byteArrayOf(0, 0, 0, 1))
            stream.write(
                byteArrayOf(
                    (((currentTime.shr(56)) and 0xff).toByte()),
                    (((currentTime.shr(48)) and 0xff).toByte()),
                    (((currentTime.shr(40)) and 0xff).toByte()),
                    (((currentTime.shr(32)) and 0xff).toByte()),
                    (((currentTime.shr(24)) and 0xff).toByte()),
                    (((currentTime.shr(16)) and 0xff).toByte()),
                    (((currentTime.shr(8)) and 0xff).toByte()),
                    (((currentTime) and 0xFF).toByte())
                )
            )
            stream.write(this.clearFirstIfNull(rs[0].abs().toByteArray()))
            stream.write(this.clearFirstIfNull(rs[1].abs().toByteArray()))

            return Base64.getEncoder().encodeToString(stream.toByteArray())
        }
    }

    private fun clearFirstIfNull(data: ByteArray): ByteArray =
        if (data.first().toInt() == 0) {
            val newData = ByteArray(data.size - 1)

            System.arraycopy(data, 1, newData, 0, newData.size)

            newData
        } else {
            data
        }
}