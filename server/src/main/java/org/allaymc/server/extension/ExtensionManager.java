package org.allaymc.server.extension;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.server.Allay;
import org.allaymc.server.utils.JSONUtils;

import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ExtensionManager} discovers extension JARs, makes their resources
 * available to bootstrap, and invokes their optional entry points.
 * JARs without an extension descriptor remain available as resources. Entry
 * instances run once while discovered and again after the walk completes;
 * discovery failures abort the pass without undoing already added resources.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class ExtensionManager {

    /**
     * {@code PATH_MATCHER} selects JAR paths for extension discovery using the
     * default filesystem's glob rules before a separate regular-file check.
     */
    private static final PathMatcher PATH_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.jar");

    /** Recursive extension search root. */
    private final Path source;
    /**
     * {@code extensionInstances} retains entries whose first invocation
     * succeeded. Each successful discovery walk invokes every retained entry
     * again, including entries retained from earlier load calls.
     */
    private final List<Extension> extensionInstances = new ArrayList<>();

    /**
     * Creates an extension manager retaining the supplied discovery root path.
     */
    public ExtensionManager(Path source) {
        this.source = source;
    }

    /**
     * Discovers extension JARs recursively and invokes their entry points.
     * A missing root is created without creating missing parents. Successful
     * entries run during discovery and again afterward; any uncaught discovery
     * or callback failure stops further processing without rollback.
     */
    @SneakyThrows
    public void loadExtensions(String[] args) {
        if (!Files.exists(source)) {
            Files.createDirectory(source);
        }

        try (var stream = Files.walk(source)) {
            stream.filter(path -> PATH_MATCHER.matches(path) && Files.isRegularFile(path))
                    .forEach(extensionPath -> loadExtension(extensionPath, args));
        }

        // The walk has made all discovered JAR resources visible before this
        // second invocation, which also includes instances retained from earlier calls.
        this.extensionInstances.forEach(extension -> extension.main(args));
    }

    /**
     * Adds one JAR to resource lookup and invokes its optional extension entry.
     * Entry construction requires a public no-argument constructor. Failures do
     * not remove the JAR; only entries whose initial invocation returns are
     * retained for the later invocation pass.
     */
    private void loadExtension(Path extensionPath, String[] args) {
        log.info(I18n.get().tr(TrKeys.ALLAY_EXTENSION_LOADING, extensionPath));
        Allay.EXTRA_RESOURCE_CLASS_LOADER.addJar(extensionPath);

        // A descriptor is optional for resource-only extensions; adding the JAR
        // first also makes its entry class available to the shared resource loader.
        var mainClass = findMainClass(extensionPath);
        if (mainClass == null) {
            return;
        }

        if (!Extension.class.isAssignableFrom(mainClass)) {
            throw new ExtensionException(I18n.get().tr(TrKeys.ALLAY_EXTENSION_MAINCLASS_TYPEINVALID, mainClass.getName()));
        }

        Extension extensionInstance;
        try {
            extensionInstance = (Extension) mainClass.getConstructor().newInstance();
            extensionInstance.main(args);
        } catch (Exception e) {
            throw new ExtensionException(I18n.get().tr(TrKeys.ALLAY_EXTENSION_CONSTRUCT_INSTANCE_ERROR, extensionPath, e));
        }

        extensionInstances.add(extensionInstance);
    }

    /**
     * Resolves the entry named by {@code extension.json}, or returns {@code null}
     * when the descriptor is absent.
     * Missing entrance values and unresolvable classes fail loading. The opened
     * JAR filesystem is not closed here, including when no descriptor exists.
     */
    @SneakyThrows
    private Class<?> findMainClass(Path extensionPath) {
        var jarFileSystem = FileSystems.newFileSystem(extensionPath);
        try {
            var extensionDescriptorPath = jarFileSystem.getPath("extension.json");
            if (!Files.exists(extensionDescriptorPath)) {
                return null;
            }
            var entrance = (String) JSONUtils.fromMap(Files.readString(extensionDescriptorPath)).get("entrance");
            if (entrance == null) {
                throw new ExtensionException("Missing extrance field in extension.json!");
            }
            return Allay.EXTRA_RESOURCE_CLASS_LOADER.loadClass(entrance);
        } catch (ClassNotFoundException e1) {
            throw new ExtensionException(I18n.get().tr(TrKeys.ALLAY_EXTENSION_ENTRANCE_MISSING, extensionPath));
        } catch (MalformedURLException e2) {
            throw new ExtensionException("Invalid URL: " + extensionPath.toUri());
        }
    }
}
