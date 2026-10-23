# Java type-overview examples

The primary type's Javadoc serves as the production file's overview. Use a single
comment for the type contract and overview, applying the stronger minimum rather
than adding their lengths. This applies to classes, interfaces, enums, and records.

Each excerpt assumes the behavior stated beside it and is not a complete file.
Verify those assumptions against the implementation and callers. Start with the
actual type name as the subject of a complete sentence explaining its role, then
develop the relevant problem, rule, or design. `{@code IpRules} parses access rules
and matches client addresses` identifies a responsibility; `{@code IpRules} provides
utilities` does not. A data type's overview first explains what its instances
represent; construction side effects belong in separate sentences naming the
constructors.
Use `<p>` between Javadoc prose paragraphs.

## Purpose overview: a registered value

Assume `BiomeEntry` stores a biome's namespaced identifier, numeric ID, and
metadata. Its constructors register new instances; the custom-biome constructor
obtains an allocated ID first. Describe the represented value before the
constructors' side effects.

```java
/**
 * {@code BiomeEntry} represents a registered biome with a namespaced identifier, a
 * numeric ID, and biome metadata. Its constructors register each instance in the
 * biome registry. Built-in biomes use predefined IDs, while custom biomes obtain
 * IDs from the custom biome allocator.
 */
```

## Purpose overview: a refresh failure

Assume `CatalogRefresher` retains the previous catalog after a failed refresh and
parses and validates replacement entries before publication. This facade delegates
the publication mechanism; it does not own a concurrency design.

```java
/**
 * {@code CatalogRefresher} refreshes the model catalog and keeps validated entries
 * available for lookups.
 * Cached entries are reused until a replacement has been parsed and validated.
 * Refresh errors leave the previous entries available, so readers can continue
 * model lookup while a valid replacement is being prepared.
 */
```

## Design overview: an account slot's lifetime

Assume `AccountSlots` limits concurrency and queues requests per account.
Cancellation can race with admission, and release is idempotent. Explain the
lifetime of a slot before describing the coordination that protects it.

```java
/**
 * {@code AccountSlots} limits concurrent requests per account and queues requests
 * until a slot is available. A waiting request can be cancelled without stopping
 * other requests.
 * Each account admits requests in arrival order and counts a request as active
 * until its caller releases the slot after reading or closing the response.
 * Routing may choose another eligible account before a request joins the queue.
 *
 * <p>
 * Queue membership and occupied slots change under the same lock. Cancellation
 * removes a waiter that has not been admitted. When cancellation races with a
 * grant, the unused slot is returned before the next waiter is admitted.
 * Release is idempotent because normal completion and cleanup may both invoke it.
 */
```

## Purpose overview: the editable unit

Assume `ProviderTableWriter` replaces only provider tables it generated and
preserves other TOML content. Make the unit of replacement and the retained
user content clear.

```java
/**
 * {@code ProviderTableWriter} updates generated provider tables in TOML
 * configuration while preserving the user's other text. These tables are replaced
 * as complete units. Text outside those tables stays as written, including
 * comments and ordering, so reconnecting a provider preserves the user's
 * surrounding settings.
 */
```

## Purpose overview: a dependency limitation

Assume `ProxyBridge` supplies a loopback HTTP proxy for SOCKS5 selections because
its plugin runtime accepts HTTP proxies but rejects SOCKS5. Verify this constraint
for the actual dependency version before using it as a design explanation.

```java
/**
 * {@code ProxyBridge} bridges SOCKS5 proxies to the HTTP proxy interface accepted
 * by the plugin runtime. A loopback HTTP proxy forwards plugin requests through
 * the selected SOCKS5 server because the runtime cannot use SOCKS5 directly.
 */
```

## Design overview: valid rules and configured rules

Assume `IpRules` provides `compile` to trim patterns and prepare reusable matchers.
`compile` skips invalid patterns but retains the supplied count in `patternCount`.
Allowlist checks reject unmatched addresses whenever that count is positive.
`matchesPattern` and `validatePattern` parse inputs as supplied;
`normalizeIp` trims addresses and removes a port only when `parseHostPort` succeeds.
Callers choose normalization explicitly. Separate parsing behavior from the
configuration decision it supports.

```java
/**
 * {@code IpRules} parses IP access rules and matches addresses against individual
 * IPs or CIDR ranges. {@code compile} prepares matchers for repeated checks, while
 * {@code matchesPattern} checks one supplied pattern against one address.
 *
 * <p>
 * {@code compile} trims whitespace and skips invalid entries, but
 * {@code patternCount} includes every supplied entry. Allowlist checks use
 * {@code patternCount} to distinguish an absent allowlist from a configured list
 * with no valid rules. A configured allowlist with no valid rules rejects every
 * address, so malformed configuration cannot silently turn a restrictive list
 * into unrestricted access.
 *
 * <p>
 * {@code matchesPattern} and {@code validatePattern} parse inputs as supplied.
 * {@code normalizeIp} trims whitespace and removes a port only when
 * {@code parseHostPort} succeeds; callers choose that normalization explicitly
 * before matching.
 */
```

## Purpose overview: stored records and their index

Assume `LedgerView` delegates to a ledger and index implemented elsewhere. The
JSONL ledger is authoritative, readers use immutable index blocks, and an append
copies at most the unfinished final block. The component implementing publication
and consistency owns the design explanation; this facade has a purpose overview.

```java
/**
 * {@code LedgerView} exposes ledger records through a shared index for time-window
 * queries. The JSONL ledger holds the records, and its in-memory index can be
 * rebuilt from them. Readers decode blocks overlapping their requested time
 * range, while appending a record copies at most the unfinished final block.
 */
```

## Design overview: a value split across stream events

Assume `PlaceholderWriter` restores known placeholders, holds an unfinished tail,
and continues it only with later text from the same channel. It emits an
unmatched tail literally when the stream ends.

```java
/**
 * {@code PlaceholderWriter} restores known placeholders in streamed responses,
 * including placeholders split across text events. A placeholder can arrive in
 * pieces: one text event ends with <code>&#123;&#123;TOKEN_</code> and the next
 * begins with <code>name&#125;&#125;</code>. Replacing values one event at a time
 * would leave the placeholder visible to the caller, even though joining the text
 * first would have found it.
 *
 * <p>
 * The writer holds a trailing fragment that could begin a placeholder and
 * continues it with later text from the same channel. Complete text can be
 * emitted immediately. An unfinished fragment stays buffered until it can be
 * matched or ruled out; when the stream ends, any unmatched tail is emitted
 * literally so the caller receives all of the original text.
 */
```

## Design overview: elapsed work and operation counts

Assume `RequestTimings` merges completed intervals, clips them to the request
window, and counts each operation once. A lock protects recording and snapshot
creation; merging and formatting use the copied snapshot.

```java
/**
 * {@code RequestTimings} records request timings and builds timing headers from
 * completed calls.
 * Concurrent calls can overlap, so adding their durations overstates how long
 * the request spent waiting. Two calls that each take ten milliseconds and
 * overlap for five occupy fifteen milliseconds together, while still counting
 * as two operations. Elapsed work is the union of their intervals within the
 * request window; operation counts remain additive.
 *
 * <p>
 * Recording continues while the response header is prepared. The collector
 * copies counts and intervals under its lock, then releases the lock before
 * merging intervals and formatting the header. This keeps one header's inputs
 * consistent while allowing later observations to be recorded for subsequent
 * snapshots.
 */
```
