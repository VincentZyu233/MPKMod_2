# Build Workflow

This repository supports GitHub Actions builds driven by commit message keywords.

## Rules

- No `--version ...`: no build, no release.
- `build action --version fabric-1.21.3`: build only.
- `build release --version fabric-1.21.3`: build and create a GitHub Release.

The `--version` value must match an existing subproject directory such as:

- `fabric-1.19.4`
- `fabric-1.20.4`
- `fabric-1.20.6`
- `fabric-1.21`
- `fabric-1.21.3`
- `forge-1.8.9`

## Examples

```bash
git commit -m "ci: verify fabric 1.21.3 build action --version fabric-1.21.3"
git commit -m "release: mpkmod build release --version fabric-1.21.3"
git commit -m "docs: update README"
```

## Behavior

- The workflow parses the latest commit message on `push`.
- On `pull_request`, it parses the PR title with the same keyword rules.
- The selected target is built with:

```bash
./gradlew <target>:build <target>:remapJar
```

- The uploaded artifact name is:

```text
mpkmod-<mod-version>-<target>.jar
```

- The GitHub Release tag is:

```text
v<mod-version>-<target>
```

## Notes

- JDK 21 is used in Actions.
- Release notes are generated from `.github/release_template.md`.
- If you want a release, keep `build release` and `--version ...` in the same commit message.
