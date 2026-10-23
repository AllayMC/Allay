package org.allaymc.server.network.protocol;

/**
 * {@code ClientVariant} identifies the Bedrock client distribution used by a
 * connection. Protocol registration combines this distribution with the wire
 * version, allowing international and NetEase clients to select different
 * codecs or processors even when their numeric versions coincide.
 */
public enum ClientVariant {
    /**
     * International Bedrock client distribution.
     */
    INTERNATIONAL,
    /**
     * NetEase Bedrock client distribution.
     */
    NETEASE
}
