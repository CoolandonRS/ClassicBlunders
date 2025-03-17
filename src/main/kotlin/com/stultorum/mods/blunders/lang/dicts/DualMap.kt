package com.stultorum.mods.blunders.lang.dicts

open class DualMap<K1, K2, V>: IDualMap<K1, K2, V> {
    private val mapK1 = HashMap<K1, V>()
    private val mapK2 = HashMap<K2, V>()
    
    override val k1s: Set<K1> = mapK1.keys
    override val k2s: Set<K2> = mapK2.keys
    override val vs: Collection<V> = mapK1.values

    override fun put(k1: K1, k2: K2, v: V) {
        mapK1[k1] = v
        mapK2[k2] = v
    }

    override fun remove(k1: K1, k2: K2): V? {
        mapK2.remove(k2)
        return mapK1.remove(k1)
    }

    override fun getK1(k1: K1): V? = mapK1[k1]
    override fun getK2(k2: K2): V? = mapK2[k2]
}
