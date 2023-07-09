package cn.allay.api.zlib;

/**
 * Allay Project 2023/6/6
 *
 * @author Cool_Loong
 */
public enum ZlibProviderType {
    LibDeflateThreadLocal,
    JavaZlibThreadLocal;

    public ZlibProvider of(CompressionType type, int level) {
        switch (this) {
            case LibDeflateThreadLocal -> {
                return new LibDeflateThreadLocal(type, level);
            }
            default -> {
                return new ZlibThreadLocal(type, level);
            }
        }
    }
}
