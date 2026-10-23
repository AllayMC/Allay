---
name: code-comments
description: "Write and review English Javadoc and implementation comments for Allay's handwritten Java code, including initially uncommented code. Require coverage and minimum lengths by explanatory role, verify claims against code, and integrate production-file overviews into primary-type Javadoc."
---

# Code Comments

Follow repository documentation syntax and tooling. Explicit user instructions take precedence.

## Scope

- New files: cover every required declaration and operation. Existing files: cover added or changed declarations and operations, plus comments and caller explanations affected by the change. A changed method body includes its Javadoc and required local explanations; a changed type includes its Javadoc and added or changed fields.
- Every edited production file needs an overview in its primary type's Javadoc, classified using the whole file. Combine it with the type contract; do not add a separate file overview. A requested comment audit covers its selected files or symbols. Preserve unrelated declarations and concurrent work.
- Cover public and private declarations, including classes, interfaces, enums, records, annotation types and elements, constructors, accessors, overridden methods, and `main`. Document static and instance initializer blocks with ordinary comments using the method tiers. Write Javadoc and implementation comments in English.
- Exemptions are generated code identified by a generation marker or a verified generator/build mapping, verbatim third-party code, test-input source text, and separate declaration documentation for lambdas and anonymous classes. Required operations inside them remain covered. A directory name alone does not prove generation; handwritten generators and handwritten neighbors of generated files remain covered. Other exemptions require explicit user instruction.
- Do not expand Lombok-generated constructors/accessors or record-generated members into handwritten code to document them. Put their meaningful contracts on the owning type, fields, or record components. Document record components with `@param` descriptions using the field tiers; shared rules belong in the type's prose. Explicitly declared constructors and accessors still use method tiers.
- Preserve licenses, generation markers, compiler/lint directives, and tool-consumed documentation. Formats without comment syntax put the explanation at the owning handwritten loader, generator, or schema.

## Verify and explain

Read the implementation; follow callers, tests, dependencies, and design references as needed to support a claim. Check defaults, exceptions, failures, ownership, and ordering. For fields, inspect writers and readers for zero values, units, encoding, precedence, and mutable sharing; name the consumer when its rule is not universal.

Check exceptional paths behind promises such as preserved types, independent copies, atomic updates, thread safety, cancellation, and exactly-once behavior. Use a small executable probe if reflection, aliasing, or an interleaving remains uncertain. Tests support only the cases they exercise. Separate guarantees from caller obligations, qualify claims to their verified scope, and report unresolved behavior. Investigate code/comment disagreements; changing wording cannot establish intended behavior.

Organize a long explanation around a concrete question. Establish the situation, follow its consequence, and explain the handling. Give each paragraph one main question; put related conditions together. Prioritize choices a maintainer might accidentally undo, explaining the trigger, action, and failure prevented. Connect technical rules to actual caller or user outcomes. Use a small input or timeline for split data, encodings, overlapping quantities, or confused identities when prose alone makes the reader reconstruct the case. Verify the example's assumptions.

Describe current behavior in the present tense. Verified history, issues, tests, or dependency behavior can explain a current constraint, but the explanation must stand on its own. Never invent incidents, measurements, vendor behavior, or plausible design motives. Proposals and change narration belong in issues, PRs, or design proposals.

Match the opening to the documented element:

- Named types: start with a complete present-tense sentence whose subject is the type name, followed by what it represents or does. For example, "BiomeType identifies a registered biome and exposes its metadata." This applies to primary, nested, and additional top-level types. Describe the type's abstraction first; explain construction side effects separately with the constructor as the subject.
- Methods and constructors: the opening summary may omit the subject, as in "Returns..." or "Creates...", when the documented operation is unambiguous. There is no need to repeat the method name mechanically. Prefer "Returns whether..." for boolean results.
- Fields, constants, and record components: compact meanings may remain noun phrases, such as "numeric biome palette identifier". Ownership, precedence, lifecycle, and other independent rules need complete sentences naming the field, value, or consumer involved.

Use complete sentences with explicit subjects in explanatory prose. Name the caller, constructor, method, stored value, or other actor when its identity affects the explanation. Preserve concrete field and method names when they distinguish behavior, ownership, or coordination; generic descriptions should not make readers guess which symbol owns a rule. Pronouns are fine when their antecedents are unambiguous; do not repeat the symbol name in every sentence. Compact labels and method/constructor opening summaries follow the exceptions above.

Local operation comments may use imperative sentences, such as "Return the unused slot before waking the next waiter", when the actor and action are clear from the adjacent code. They still need the verified reason or consequence required by their role. Use concrete actors and verbs, and passive voice when the object's state is the point and the actor is clear. State conditions with `when`, `before`, `after`, `until`, or `unless`. Replace praise and vague labels with behavior and consequences. Keep precise exclusions that prevent misuse; omit unrelated assurances. Compact definitions and field phrases need no extra narrative.

## Mandatory coverage and minimum lengths

Every in-scope item below needs a comment. Meet both its minimum and required information; descriptive names and private visibility do not waive coverage.

| Item | Minimum | Information and placement |
| --- | --- | --- |
| Purpose overview in primary-type Javadoc | 30 words, 2 sentences | Type name as subject and its responsibility, then the domain rule and how the pieces address it |
| Design overview in primary-type Javadoc | 80 words, 2 prose paragraphs | Type name as subject and its responsibility, then the problem, consequential choice, and interacting rules |
| Definition method/constructor/initializer | 8 words, 1 sentence | Purpose and result/effect; consumer when it establishes the role |
| Calling-rule method/constructor/initializer | 25 words, 2 sentences | Purpose and applicable calling rules |
| Design method/constructor/initializer | 60 words, 3 sentences | Purpose, calling rules, and design conditions/consequences |
| Named type | 8 words, 1 sentence | Type name as subject and its representation or implementation role; shared rules at the owning type or field group |
| Compact field or record component | 3 words; unit/encoding label may use 1 | Meaning, source, unit, encoding, or literal special value; placement follows Java visibility rules below |
| Field with an independent conditional, ownership, precedence, or lifecycle rule | 12 words | Meaning, condition, effect; preceding block |
| Adjacent fields sharing a rule | 20 words for the group, plus each compact field explanation | Name all participating fields and explain their relationship once above the group |
| Each purpose group of static fields/constants | 12 words, 1 sentence | What the values govern and how they are used |
| Local fixed threshold, unit conversion, truncation, or approximation | 6 words, 1 sentence | Meaning and consequence; only when no other required mechanism below applies |
| Other required local operation | 15 words | Trigger and reason |
| Test/benchmark/fuzz entry point | 20 words, 2 sentences | Situation and behavior protected, or benchmark workload and measurement |

Calling rules include meaningful inputs/defaults, results, state changes, ownership/cleanup, cancellation, exceptions/recovery, and ordering/locking obligations. Explain special results that affect caller decisions or permitted use. Document meaningful exceptions and their triggering conditions without narrating routine propagation. Omit empty "not applicable" sections. JUnit test methods, JMH benchmarks, and fuzz entry points use the test tier; test helpers use method tiers, and named test types and fields use declaration rules. Explain individual enum constants using the field tiers.

Classify fields in order: shared rule, independent rule, compact meaning. A unit, encoding, enum label, or literal special value alone stays compact. Add a preceding block for an independent rule even when the field also belongs to a group. Count shared rules once and each field's identity separately. A label such as "state fields" explains no relationship. Public/protected fields, interface constants, and enum constants use preceding Javadoc. Private/package-private compact fields may use line-end comments; move them above the field when needed for line width or Lombok documentation. Put shared rules in the owning type's Javadoc when they govern API fields, or in a separate ordinary comment above an internal field group. Keep each member's documentation attached to that member.

One primary-type Javadoc satisfies both the overview and named-type requirements; apply the stronger minimum, not their sum. The overview prose itself must meet its purpose/design minimum; record-component tags do not replace that prose. Nested and additional top-level types retain their own declaration coverage without duplicating the primary overview.

### Select the explanatory role

A design explanation is required where the code itself implements:

- Coordination of independently progressing work, with custom state/ordering deciding which result, resource, or cancellation wins.
- Preservation of information or identity across split input, round trips, or incompatible representations, with special handling against loss or misassociation.
- Ordered persistent updates, recovery, or rollback that keeps multiple steps consistent.
- A calculation/selection combining observations or states whose interaction changes the result, such as overlapping durations or absent versus wholly invalid configuration.

Use the design method tier for those implementations. Put the method's decision there and shared background at the owning type. Calling a library operation, acquiring a pooled object, or delegating to the implementation does not itself qualify; line counts, branch counts, and imported mechanisms cannot promote a tier.

Otherwise use the calling-rule tier for any meaningful default, special input/result, ownership/cleanup obligation, cancellation, recovery, ordered side effect, or caller synchronization requirement. Remaining methods use the definition tier. Interface methods use the same criteria for their required behavior.

A production file owning a listed design relationship, including one distributed across declarations, needs a design overview in its primary type's Javadoc. Other production files, including delegating facades, need purpose overviews there. A pooled-buffer wrapper with a null/type fallback has calling rules and a purpose overview; a queue resolving admission against cancellation owns a design even when short.

Start method documentation with purpose/result, then develop its most consequential calling rule and related conditions. Separate independent questions into paragraphs in design methods. Use concrete special results when the distinction matters, and describe internal phases only to explain a calling rule or design choice.

### Count length

Count whitespace-separated English words after removing comment delimiters and Markdown/HTML decoration; identifiers, including those in `{@code ...}`, and hyphenated words count as one. Include prose and explanatory list items, including examples explained in sentences. Count explanatory text in `@param`, `@return`, and `@throws` once, excluding tag names and parameter/exception identifiers. Exclude headings, link-only references, code samples, licenses, directives, author/version metadata, and `{@inheritDoc}`. Wrapped lines and list labels are not complete sentences. Separate prose paragraphs with a blank comment line and `<p>` in Javadoc; wrap standalone prose near 80 columns.

These are hard lower bounds for the selected role. Stop when the explanation is complete; additional text must add supported information. Repetition, filler, and line-by-line narration fail even above the minimum. If reaching it requires padding, recheck the role and required facts; never invent a constraint to justify length.

## Place each explanation

| Reader's question | Primary home | What another location adds |
| --- | --- | --- |
| Defaults, inputs, results, caller obligations | Method/constructor Javadoc | Local mechanism or failure consequence |
| Shared lock, publication, or ownership rule | Type or field group | Field identity; local reason for ordering |
| Race, recovery choice, or sequencing at one operation | Immediately before that operation | Caller-visible result/obligation at the method |
| Entry-point relationships and shared phases | Primary-type Javadoc | Each declaration's calling rules |

Put type, constructor, and method Javadoc immediately before the declaration's annotations, such as `@Override`, `@Getter`, or `@Test`. Use ordinary `//` or `/* ... */` comments for local mechanisms and initializer blocks. Use `{@code ...}` for literals and `{@link ...}` for resolvable Java symbols; use `@param`, `@return`, and `@throws` when they add useful contract information. Preserve author tags, API-status annotations, and tool-consumed syntax. Do not add nullability annotations.

Handwritten overrides still require local Javadoc meeting their role's minimum. `{@inheritDoc}` may reuse a verified inherited contract, but cannot replace the required local explanation or its length. State the override's concrete role and any additional obligations without copying the entire parent contract. Check the parent and implementation before claiming they agree.

Explain a coordinated sequence once at its start, naming the operations covered; separate mechanisms and paths need separate explanations. Keep caller guarantees at declarations even when a design reference explains their background. Cross-component links use named symbols or stable sections; links cannot replace local explanations or count toward minima.

When two passages answer the same question with the same fact, keep the full explanation at its primary home and revise the other to cover its own required information. Repetition at an operation must add a condition, mechanism, or consequence. A field can name the constructor defining a default without repeating its value.

### Type overviews in Java

Before drafting, read or reuse [file-overviews.md](references/file-overviews.md). In a normal Java file, the primary top-level class, interface, enum, or record owns the overview. Start its Javadoc with the actual type name as the grammatical subject and explain what it represents, processes, provides, or produces, such as "RequestTimings records request durations and builds timing headers." Establish the type's responsibility before background or construction details. Adding a type name to a sentence about the wrong responsibility does not fix the explanation.

After stating the responsibility, develop the relevant domain rules, design choices, conditions, and consequences in the same Javadoc. "Provides utilities" is too vague. Mention symbols to explain relationships rather than inventory their jobs. Lists may clarify cases or sequences while retaining the design overview's prose paragraphs. Do not create a detached overview before or after imports, or a second Javadoc block beside the type contract.

For `package-info.java`, put the overview in package Javadoc before package annotations and the `package` declaration. If `module-info.java` is introduced, use module Javadoc before its declaration. Apply the same purpose/design minima to these overviews. Package documentation does not replace individual type contracts.

After formatting or moving declarations, check that each Javadoc still belongs to the intended declaration and that links and markup remain valid.

## Required local operations

Explain the trigger and reason for each in-scope occurrence of:

- Retry, backoff, or fallback selection; suppressing errors or converting failed operations to empty/default results.
- Commit, rollback, compensation, or persistence ordering.
- Thread/executor/future coordination, interruption or cancellation, resource closing, cache invalidation, or synchronized shared-state mutation.
- Protocol compatibility transformations/exceptions; thresholds, tolerances, truncation, unit conversions, or approximations.

Ordinary defaults, contract-level null guards, initialization, assignment, iteration, direct exception propagation, and routine library pool/lock/resource calls use their declaration contract. This includes routine try-with-resources cleanup. Their presence alone needs no local paraphrase. A branch implementing an independent mechanism above still requires its local explanation.

Use the 6-word tier only for a fixed threshold, unit conversion, truncation, or approximation with no other listed mechanism; otherwise use 15 words. Each explanation must add a verified fact beyond the adjacent expression: rule source, ordering reason, affected data/callers, what survives partial work, or failure prevented. A phase label or a numeric default's value supplies no reason. This extra-information test applies to local explanations; definitions and compact labels may directly identify their subject.

If evidence is missing, inspect callers, consumers, or the owning rule and report unresolved gaps. Deleting a required explanation or inventing a reason cannot resolve them.

## Review

Read or reuse [examples.md](references/examples.md) for tier choices and final review. Manually check:

1. Read comments with the whole file: can a newcomer follow the problem, flow, and decisions to preserve? Check usefulness, accuracy, and distribution of detail before counts.
2. Enumerate required items, including currently uncommented ones; assign roles and verify coverage, placement, words, sentences, and paragraphs. Count shared field rules once.
3. Verify facts and exceptions against implementation and consumers. Identify each local explanation's added fact and each repeated passage's distinct purpose. Recheck unclear guarantees with the evidence/probe rules above.
4. Check that paragraphs advance their question and compact definitions stay compact. Each type description must start with the type name as its subject and explain the type's role, not merely a constructor's side effects. Check explicit subjects and concrete symbols in longer explanations while allowing method/constructor opening summaries, compact field labels, and clear local imperatives. Each production overview belongs in the primary type's Javadoc; reject detached overviews, vague summaries, and declaration inventories. Similar lengths are a signal to inspect padding, not a reason to vary accurate wording; synonym cycling does not improve explanations.
5. Fix stale comments and inspect placement after formatting. Any missing, short, misplaced, unsupported, or inaccurate required explanation leaves validation incomplete. Fix within scope; report remaining items and reasons. Read-only reviews report without editing.

Lead reports with readability and material gaps, grouping minor presentation issues. Builds and tests do not establish comment quality. Keep comment-only work scoped to comments, validate Javadoc when affected, and follow repository-required checks. Use [evaluation.md](references/evaluation.md) only when comparing revisions of this skill.
