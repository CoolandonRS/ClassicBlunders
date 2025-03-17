package com.stultorum.mods.blunders.lookup

/**
 * This literally only exists because of JVM type erasure.
 * Is identical to {@link IRetrievable}.
 * Use that instead unless necessary.
 */
interface IRetrievable2<TClass> {
    fun <TFunc: TClass> retrieve2(): TFunc
}