package de.randombyte.kosp.extensions

import org.spongepowered.configurate.hocon.HoconConfigurationLoader
import java.nio.file.Path

fun Path.toConfigurationLoader(): HoconConfigurationLoader = HoconConfigurationLoader.builder().path(this).build()