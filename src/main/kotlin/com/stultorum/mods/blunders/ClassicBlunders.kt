package com.stultorum.mods.blunders

import com.mojang.logging.LogUtils
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent

@Mod(ClassicBlunders.MODID)
class ClassicBlunders {
    companion object {
        const val MODID = "blunders"
        private val LOGGER = LogUtils.getLogger()
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {
        LOGGER.info("Classic blunders loaded")
    }

    constructor(modEventBus: IEventBus, modContainer: ModContainer) {
        modEventBus.addListener(::commonSetup)
    }
}