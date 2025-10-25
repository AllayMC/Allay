import os
import mkdocs_gen_files

BASE_DIR = "api/src/main/java/org/allaymc/api/eventbus/event"
GITHUB_BASE_URL = "https://github.com/AllayMC/Allay/blob/master/"

entries = {}

for category in sorted(os.listdir(BASE_DIR)):
    category_path = os.path.join(BASE_DIR, category)
    if not os.path.isdir(category_path):
        continue

    files = [
        f for f in os.listdir(category_path)
        if f.endswith(".java")
    ]
    if not files:
        continue

    entries[category] = sorted(files)

print(entries)

with mkdocs_gen_files.open("advanced/event-list.md", "w") as f:
    def fp(str):
        print(str, file=f)

    def el():
        print("", file=f)
    
    fp("---")
    fp("comments: true")
    fp("hide:")
    fp("  - navigation")
    fp("  - toc")
    fp("---")
    el()

    fp("# Event List")
    el()
    fp("This page contains all the available events in Allay.")
    el()
    fp(f"Current event count: {sum(len(files) for files in entries.values())}")
    el()

    for category, files in entries.items():
        fp(f"## {category.capitalize()}")
        el()

        for filename in files:
            event_name = filename[:-5]
            github_path = f"{GITHUB_BASE_URL}{BASE_DIR}/{category}/{filename}"
            fp(f"- [{event_name}]({github_path})")

        el()
