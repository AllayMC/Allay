package org.allaymc.api.zlib;

/**
 * Allay Project 2023/6/6
 *
 * @author Cool_Loong
 */
public enum ZlibProviderType {
    LibDeflateThreadLocal,
    JavaZlibThreadLocal;

    public ZlibProvider of(CompressionType type, int level) {
        return this == ZlibProviderType.LibDeflateThreadLocal ? new LibDeflateThreadLocal(type, level) : new JavaZibThreadLocal(type, level);

    }
}
