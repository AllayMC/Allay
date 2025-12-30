import toml
import mkdocs_gen_files

from urllib.parse import urlparse


def parse_github_url(url):
    parsed_url = urlparse(url)
    
    if parsed_url.netloc != "github.com":
        raise ValueError("The provided URL is not a valid GitHub URL")

    path_parts = parsed_url.path.strip("/").split("/")
    
    if len(path_parts) < 2:
        raise ValueError("Invalid URL format. Unable to extract organization and repository names")
    
    organization = path_parts[0]
    repository = path_parts[1]
    
    return organization, repository

with mkdocs_gen_files.open("plugin_list.md", "w") as f:
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

    fp("# Plugin List")
    el()
    fp("This page holds some available plugins that can be installed into your Allay server. You are free to add your plugins to the site, just open a new issue.")
    el()

    fp("## Available Plugins")
    el()
    fp("<div class=\"grid cards\" markdown>")
    el()

    config = toml.load("docs/plugin_list.toml")
    for name, info in config.items():
        description = info["description"]
        authors = info["authors"]
        github = info["github"]
        user, repo = parse_github_url(github)

        fp(f"-   **{name}** <small>by {", ".join(authors)}</small>")
        el()
        fp(f"    ![Stars](https://img.shields.io/github/stars/{user}/{repo})")
        fp(f"    ![Release](https://img.shields.io/github/release/{user}/{repo})")
        fp(f"    ![License](https://img.shields.io/github/license/{user}/{repo})")
        fp(f"    ![Last Commit](https://img.shields.io/github/last-commit/{user}/{repo})")
        fp(f"    ![Top Language](https://img.shields.io/github/languages/top/{user}/{repo})")
        el()
        fp("    ---")
        el()
        fp(f"    {description}")
        el()
        fp(f"    [**:octicons-arrow-right-24: GitHub**]({github})")
        el()

    el()
    fp("</div>")
