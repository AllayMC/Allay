package cn.allay.api.utils.zlib;

/**
 * @author Cool_Loong <br>
 * @date 6/6/2023 <br>
 * Allay Project
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
