package com.stultorum.mods.blunders.lang.dicts

open class MetaDualMap<K1, K2, V>: IDualMap<K1, K2, V> {
    private val mapK1 = HashMap<K1, Int>()
    private val mapK2 = HashMap<K2, Int>()
    private val mapV  = HashMap<Int,  V>()
    
    override val k1s: Set<K1> = mapK1.keys
    override val k2s: Set<K2> = mapK2.keys
    override val vs: Collection<V> = mapV.values

    override fun put(k1: K1, k2: K2, v: V) {
        val meta = getMeta(k1, k2)
        mapK1[k1]  = meta
        mapK2[k2]  = meta
        mapV[meta] = v
    }
    
    override fun remove(k1: K1, k2: K2): V? {
        val meta = mapK1[k1]
        mapK1.remove(k1)
        mapK2.remove(k2)
        return mapV.remove(meta)
    }
    override fun getK1(k1: K1): V? = mapV[mapK1[k1]]
    override fun getK2(k2: K2): V? = mapV[mapK2[k2]]
    
    protected open fun getMeta(k1: K1, k2: K2): Int = (k1.hashCode() * 31) + k2.hashCode()
}
