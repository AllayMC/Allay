package org.allaymc.server.zlib;

/**
 * @author Cool_Loong
 */
public enum ZlibProviderType {
    LibDeflateThreadLocal,
    JavaZlibThreadLocal;

    public ZlibProvider of(CompressionType type, int level) {
        return this == ZlibProviderType.LibDeflateThreadLocal ?
                new LibDeflateThreadLocal(type, level) :
                new JavaZibThreadLocal(type, level);
    }
}
