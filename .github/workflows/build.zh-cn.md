# 构建工作流

这个仓库现在支持用 commit 信息驱动 GitHub Actions 构建。

## 规则

- 不带 `--version ...`：不构建，不发布。
- `build action --version fabric-1.21.3`：只构建。
- `build release --version fabric-1.21.3`：构建并创建 GitHub Release。

`--version` 的值必须和仓库里现有的子项目目录一致，例如：

- `fabric-1.19.4`
- `fabric-1.20.4`
- `fabric-1.20.6`
- `fabric-1.21`
- `fabric-1.21.3`
- `forge-1.8.9`

## 示例

```bash
git commit -m "ci: verify fabric 1.21.3 build action --version fabric-1.21.3"
git commit -m "release: mpkmod build release --version fabric-1.21.3"
git commit -m "docs: update README"
```

## 行为

- `push` 时，工作流解析最新 commit message。
- `pull_request` 时，工作流解析 PR 标题，规则相同。
- 选中的目标会用下面的命令构建：

```bash
./gradlew <target>:build <target>:remapJar
```

- 上传的构建产物文件名：

```text
mpkmod-<模组版本>-<目标>.jar
```

- GitHub Release 的 tag：

```text
v<模组版本>-<目标>
```

## 说明

- GitHub Actions 使用 JDK 21。
- Release Notes 来自 `.github/release_template.md`。
- 如果要发 Release，`build release` 和 `--version ...` 必须同时出现在同一个提交信息里。
