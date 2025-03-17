package com.stultorum.mods.blunders.lang.dicts

/**
 * Note: There should never be a case where K1 has a value of which K2 does not have a corresponding key.
 * If this happens, it will cause unintended behavior.
 */
interface IDualMap<K1, K2, V> {
    val k1s: Set<K1>
    val k2s: Set<K2>
    val vs: Collection<V>
    
    fun put(k1: K1, k2: K2, v: V)
    fun remove(k1: K1, k2: K2): V?
    fun getK1(k1: K1): V?
    fun getK2(k2: K2): V?

    fun putIfAbsent(k1: K1, k2: K2, v: V): V? = getK1(k1) ?: run {
        put(k1, k2, v)
        return@run null
    }
    
    @JvmName("_getK1")
    @Suppress("INAPPLICABLE_JVM_NAME")
    operator fun get(k1: K1) = getK1(k1)
    @JvmName("_getK2")
    @Suppress("INAPPLICABLE_JVM_NAME")
    operator fun get(k2: K2) = getK2(k2)
    operator fun set(k1: K1, k2: K2, v: V) = put(k1, k2, v)
}
