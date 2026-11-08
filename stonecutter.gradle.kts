stonecutter active "26.3-fabric"

// See https://stonecutter.kikugie.dev/wiki/config/params
stonecutter parameters {
    val (version, loader) = current.project.split('-', limit = 2)

    // Makes version- and loader-specific properties apply from `stoncutter.properties.toml`
    properties {
        tags(version, loader)
    }

    // Adds constants to Stonecutter comments (i.e. for `//? if fabric {...`)
    constants {
        match(loader, "fabric", "neoforge")
    }

    swaps["mod_version"] = "\"${properties["mod.version"]}\";"
    swaps["minecraft"] = "\"${current.version}\";"
    constants["release"] = properties["mod.id"] != "template"
    targets["fapi"] = properties.getOrNull("deps.fabric_api") ?: "0"

    replacements {
        string(current.parsed >= "1.21.11") {
            replace("ResourceLocation", "Identifier")
        }

        string(current.parsed >= "26.1") {
            replace("classTweaker v2 named", "classTweaker v2 official")
        }
    }
}
