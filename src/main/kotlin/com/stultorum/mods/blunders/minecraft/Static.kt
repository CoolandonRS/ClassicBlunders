package com.stultorum.mods.blunders.minecraft

import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level

fun Level.throwIfClient() {
    if (this.isClientSide) throw IllegalStateException()
}

fun BlockPos.adjacent(): Array<BlockPos> = arrayOf(above(), below(), north(), east(), south(), west())