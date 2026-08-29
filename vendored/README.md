# Vendored Artifacts

## spark-common (me.lucko:spark-common)

- **Version**: `1.10.172` (pinned from `1.10.172-SNAPSHOT`)
- **Source repository**: https://github.com/lucko/spark
- **Source commit**: `1b42ef3390a3faf4f121bb0629df51d041af15ea` (`v1.10-172-g1b42ef3`, 2026-03-26)
- **License**: GPL-3.0 (see `maven/me/lucko/spark-common/1.10.172/LICENSE`)
- **Why vendored**: spark-common is published only to lucko's Nexus
  (https://repo.lucko.me/me/lucko/spark-common/) as SNAPSHOT artifacts. Pinning a
  timestamped snapshot in Gradle is fragile, so the exact snapshot build
  `1.10.172-20260326.215512-1` was downloaded and re-published into this local
  Maven repository layout with the clean version `1.10.172`. The binary jar and
  pom are byte-identical to the snapshot; only version strings were rewritten.
- **Contents**: `spark-common-1.10.172.jar` (binary), `spark-common-1.10.172-sources.jar`
  (sources jar assembled from the spark repository at the pinned commit; lucko's
  Nexus does not publish sources jars), `spark-common-1.10.172.pom`, `LICENSE`.
- **Consumed by**: `server` module (stage 1 of embedding spark as a built-in
  `/spark` command). The `spark-native/**` resources inside the binary jar are
  loaded at runtime and must survive shading.

## Vendored transitive dependencies

All were downloaded from lucko's Nexus as timestamped snapshots and pinned with
clean versions. Their poms were rewritten the same way; original dependency
lists are preserved.

| Artifact | Pinned version | Nexus snapshot build |
|---|---|---|
| `me.lucko:spark-api` | `0.1` | `0.1-20250703.200108-1` |
| `me.lucko:bytesocks-java-client` | `1.0` | `1.0-20260208.124123-2` |
| `me.lucko:bytesocks-java-client-parent` | `1.0` | `1.0-20260208.124123-1` |
| `net.kyori:adventure-text-feature-pagination` | `4.0.0` | `4.0.0-20250630.190727-1` |

`bytesocks-java-client-parent` is required for Maven dependency management
(Java-WebSocket version) and is packaging `pom` only.

## Not vendored (resolved from Maven Central)

`net.kyori:adventure-api:4.21.0`, `net.kyori:adventure-text-serializer-gson:4.21.0`,
`net.kyori:adventure-text-serializer-legacy:4.21.0`,
`tools.profiler:async-profiler:4.3`, `org.ow2.asm:asm:9.7`,
`net.bytebuddy:byte-buddy-agent:1.14.17`, `com.google.protobuf:protobuf-javalite:4.31.1`,
`org.java-websocket:Java-WebSocket:1.6.0`.

## Updating

Fetch the latest snapshot metadata from https://repo.lucko.me/me/lucko/spark-common/maven-metadata.xml,
download the timestamped artifacts, re-pin with a clean version, and update this
file.
