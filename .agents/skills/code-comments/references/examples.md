# Java comment examples

Each excerpt assumes the behavior stated beside it. Verify those assumptions in
the code before adapting an example; these are not claims about existing Allay
implementations. Excerpts are not complete files. Apply SKILL.md's coverage and
minima to the whole task. Type-overview examples are in
[file-overviews.md](file-overviews.md).

## Definitions and calling rules

Definition tier: the private getter returns the configured display label.
Private visibility still requires documentation.

```java
/**
 * Returns the display label configured for this provider.
 */
private String getName() {
    return label;
}
```

Calling-rule tier: the reader returns shared cached bytes and throws
`NoSuchFileException` for a missing file. The copying obligation belongs at the API.

```java
/**
 * Returns the cached contents of the requested path. The returned array is shared
 * with the cache, so callers must copy it before modifying any bytes.
 *
 * @throws NoSuchFileException if the path has no cached entry
 */
```

## Design and paragraph order

Design tier: acquisition owns queue admission and cancellation, returns an
`AutoCloseable` slot whose implementation releases idempotently, and uses zero to
disable the limit. The paragraphs separate normal results, cancellation, and cleanup.

```java
/**
 * Acquires a slot after the request reaches the front of its account queue.
 * A zero limit lets the request proceed immediately without occupying a slot.
 *
 * <p>
 * Cancellation removes a waiting request from the queue. If cancellation races
 * with admission, this method returns the unused slot before waking another
 * waiter and completes the request with a cancellation exception.
 *
 * <p>
 * Callers close the returned handle after reading or closing the response.
 * Closing the handle repeatedly has no further effect on occupied slots.
 */
```

## Fields, records, and shared rules

Record components use field tiers in `@param` descriptions. This excerpt assumes
a nested record; a primary production type also needs its overview minimum.

```java
/**
 * Represents encoded image data sent in an upstream request.
 *
 * @param mediaType image MIME type
 * @param data base64
 */
private record Image(String mediaType, String data) {}
```

Independent field rule: an event sink reads the map asynchronously. Verify that
consumer's ownership rule rather than inferring it from the map type.

```java
/**
 * Holds event attributes. After enqueueing the event, callers must leave this map
 * and its mutable values unchanged until the sink finishes reading them.
 */
private Map<String, Object> fields;
```

Shared-rule tier: both durations start at request arrival and use zero for an
unobserved event. Explain the relationship once; each field names its endpoint.
For API fields, place the shared rule in the owning type's Javadoc and use Javadoc
for each member instead of line-end comments.

```java
// firstContentNanos and firstTextNanos measure elapsed nanoseconds from request
// arrival to their respective events. Zero means that event has not been
// observed for this request.
private long firstContentNanos; // first text, reasoning, or tool-call content
private long firstTextNanos; // first text content
```

Independent field rule: the log sink prefers a nonempty string attribute, then
`component`, then `"app"`. Verify conversions and whitespace handling at that sink.

```java
/**
 * Names the event source. The log sink prefers a nonempty string in
 * {@code fields.get("component")} and uses {@code "app"} when both sources are empty.
 */
private String component;
```

Lombok-generated members stay generated. If a generated getter exposes mutable
storage, document that obligation on its source field, before the annotation.
Explicitly handwritten accessors still require method documentation.

```java
/**
 * Holds the current packet payload. The generated getter exposes the same array,
 * so consumers must copy it before retaining or modifying its contents.
 */
@Getter
private byte[] payload;
```

## Constants and local decisions

Assume the sender counts dispatches and the receiver counts arrivals; connection
setup delays the first arrival. This explains the margin without promising that
it covers every possible delay. A single-constant group can use one Javadoc for
the group purpose and the constant's meaning, meeting the stronger minimum.

```java
/**
 * Allows for connection setup delaying the first arrival. Counting dispatch
 * times alone can admit another request inside the receiver's window.
 */
private static final Duration ARRIVAL_SLACK = Duration.ofSeconds(1);
```

Assume cancellation arrived after a waiter received its slot. The local
explanation identifies why returning the slot precedes waking another waiter.

```java
// Cancellation arrived after this waiter received a slot. Return its unused slot
// before waking the next request in the same account queue.
lane.busy--;
lane.grant();
```

Short-operation tier: the recorder already increased its count and now caps
interval storage alone. An additional coordination or recovery mechanism would
require the longer tier.

```java
// Keep counting after interval storage reaches its cap.
if (intervals.size() < MAX_INTERVALS) {
    intervals.add(next);
}
```

Assume the handler restores interruption before returning a failed result,
allowing its executor's caller to observe the cancellation request.

```java
// Preserve interruption for the executor's caller; converting this operation to
// a failed result must not consume the request to stop subsequent work.
Thread.currentThread().interrupt();
return false;
```

## Defaults, wrappers, and overrides

The constructor owns the numeric default; the field identifies its role.
Ordinary default handling needs no local paraphrase.

```java
/**
 * Creates an empty cache using the supplied lifetime for stored entries.
 * Nonpositive durations select thirty seconds. Expiration is checked during
 * reads rather than by a background cleanup task.
 *
 * @throws NullPointerException if {@code ttl} is null
 */
Cache(Duration ttl) {
    this.ttl = ttl.isNegative() || ttl.isZero() ? Duration.ofSeconds(30) : ttl;
}

private final Duration ttl; // stored entry lifetime
```

Calling-rule tier: an optional flusher changes the caller-visible effect. Branch
count does not determine length; the null check itself needs no narration.

```java
/**
 * Sends pending output through the optional flusher. Writers without a flusher
 * keep their existing buffering behavior, so callers can invoke this helper for
 * either kind of writer.
 */
private void flush() throws IOException {
    if (flusher != null) {
        flusher.flush();
    }
}
```

Calling-rule tier: borrowing delegates synchronization to a library pool and
replaces unusable entries. Calling the pool alone needs no synchronization tutorial.

```java
/**
 * Returns a reusable buffer whose contents may come from an earlier call.
 * Return it through {@code release} after its consumer finishes, then stop
 * accessing its storage. An unusable pool entry produces a fresh buffer.
 */
```

Handwritten override: this implementation delegates to a packet buffer whose
flushing behavior has been verified. Inherited text alone does not count toward
local coverage or length, and Javadoc precedes `@Override`.

```java
/**
 * Sends complete packets buffered by this writer to its transport. Incomplete
 * packet data remains buffered until later writes finish the packet, so flushing
 * cannot publish a partial packet.
 *
 * <p>
 * {@inheritDoc}
 */
@Override
public void flush() throws IOException {
    packetBuffer.flush();
}
```

## Tests, benchmarks, and unusual setup

Test tier: a delayed first arrival tests a receiver's rolling window. The entry
explains the scenario and observable behavior; the setup note explains the
injected delay. Place the Javadoc before `@Test` or `@ParameterizedTest`.

```java
/**
 * Models a slow first connection followed by reused connections. The receiver
 * must still receive no more than its rolling-window limit when the first
 * request arrives later than its dispatch time suggests.
 */
```

```java
// Delay the first arrival to model connection setup while later requests reuse
// an established connection and reach the receiver sooner.
receiver.firstDelay = Duration.ofMillis(150);
```

JMH entry points use the same minimum but explain the workload and measurement,
without inventing a performance assertion. Place the Javadoc before `@Benchmark`.

```java
/**
 * Measures lookups against a preloaded cache using keys prepared outside the
 * measured invocation. Each call consumes the retrieved value so the benchmark
 * measures lookup work without including cache construction.
 */
```

## Guarantees and exceptions

Calling-rule tier: writing delegates replacement/recovery. Assume this storage
layer atomically replaces ordinary files, but writes hard-linked files in place
after preserving the new bytes. This is an example-specific contract, not a
general guarantee of Java file operations.

```java
/**
 * Replaces the path through a temporary file and atomic move. Hard-linked files
 * are written in place to preserve their links. That path can leave a partial
 * write after a crash, with the complete new contents retained in a temporary file.
 */
```

A claim of an independent copy fails when a mutable timestamp remains shared.
Assume copying delegates collection copying and retains a mutable `Date` reference:

```java
/**
 * Copies the record's item list and timing map so callers can modify those
 * collections independently. The mutable completion timestamp remains shared;
 * callers must leave it unchanged through either record.
 */
```

## Review failures

Review the whole explanation before its counts. Required details should help
readers understand the problem and the decisions to preserve.

| Failure | Correction |
| --- | --- |
| "Timings use unions. The collector has a lock. Headers have a limit." | Explain the type's responsibility, then develop one design question through its consequences. |
| "Stop before the next dependency exceeds the header limit." | Explain what remains available after truncation; translating the comparison adds no fact. |
| The acquisition example flattened into one paragraph | Keep normal results, cancellation, and cleanup as separate reader questions. |
| A long component description merely calling it the event's source | Document the sink's override order, as in the field example. |
| "Returns an independent copy of all its data." | Verify mutable elements and retained references as well as collection containers. |
| Repeating the borrowing contract at the type, method, and pool call | Keep caller obligations at the method and local reasons at exceptional operations. |
| A detached overview beside a type's Javadoc | Merge the overview and type contract in one Javadoc, applying the stronger minimum once. |
| An uncommented private getter, or only `{@inheritDoc}` on an override | Restore local declaration coverage and the role's minimum explanation. |
| Expanding Lombok accessors just to comment on them | Document generated-member contracts on source fields or the owning type. |
| A "state fields" label | State the shared rule and preserve each field's meaning. |
| Padding, tags, line wrapping, or praise used to reach a minimum | Count substantive prose and preserve separate design paragraphs. |
| Claims of thread safety based only on a concurrent collection | Check operations spanning multiple calls and fields before claiming atomicity or ordering. |
| "Requires a positive timeout" when nonpositive values select a default | State the actual default and when it is applied. |

For truncation, assume fixed metrics and a dependency total were emitted before
sorted detail entries:

```java
// Fixed metrics and the dependency total remain; this dependency and later
// details are omitted when the next entry would exceed the header limit.
if (candidate.length() > MAX_HEADER_LENGTH) {
    break;
}
```
