/*
 * Copyright (c) 2024, Kaooot
 */
package org.jukeboxmc.extractor.config

import com.google.gson.annotations.SerializedName

/**
 * @author Kaooot
 * @version 1.0
 */
class Config(
    @SerializedName("proxy_address") val proxyAddress: String,
    @SerializedName("proxy_port") val proxyPort: Int,
    @SerializedName("target_address") val targetAddress: String,
    @SerializedName("target_port") val targetPort: Int
)