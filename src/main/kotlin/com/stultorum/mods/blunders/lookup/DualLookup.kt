package com.stultorum.mods.blunders.lookup

import com.stultorum.mods.blunders.lang.dicts.IDualMap
import com.stultorum.mods.blunders.lang.dicts.MetaDualMap
import com.stultorum.mods.blunders.lang.exceptions.AlreadyRegisteredException
import com.stultorum.mods.blunders.lang.exceptions.NotRegisteredException

class DualLookup<TKey1, TKey2, TObj>(private val map: IDualMap<TKey1, TKey2, TObj> = MetaDualMap()) where TObj: IRetrievable<TKey1>, TObj: IRetrievable2<TKey2> {
    fun register(obj: TObj) {
        val key1 = obj.retrieve<TKey1>()
        val key2 = obj.retrieve2<TKey2>()
        if (map.putIfAbsent(key1, key2, obj) != null) throw AlreadyRegisteredException("$key1;$key2")
    }

    fun getK1(key1: TKey1): TObj? = map[key1]
    fun getK2(key2: TKey2): TObj? = map[key2]

    fun remove(obj: TObj): Unit = remove(obj.retrieve<TKey1>(), obj.retrieve2<TKey2>())
    fun removeK1(key1: TKey1) {
        val key2 = getK1(key1)?.retrieve2<TKey2>() ?: throw NotRegisteredException("$key1")
        remove(key1, key2)
    }
    fun removeK2(key2: TKey2) {
        val key1 = getK2(key2)?.retrieve<TKey1>() ?: throw NotRegisteredException("$key2")
        remove(key1, key2)
    }
    fun remove(key1: TKey1, key2: TKey2) {
        if (map.remove(key1, key2) == null) throw NotRegisteredException("$key1, $key2")
    }

    @JvmName("_getK1")
    operator fun get(key1: TKey1) = getK1(key1)
    @JvmName("_getK2")
    operator fun get(key2: TKey2) = getK2(key2)
    operator fun plusAssign(obj: TObj) = register(obj)
    @JvmName("minusAssignObj")
    operator fun minusAssign(obj: TObj) = remove(obj)
    @JvmName("minusAssignK1")
    operator fun minusAssign(key1: TKey1) = removeK1(key1)
    @JvmName("minusAssignK2")
    operator fun minusAssign(key2: TKey2) = removeK2(key2)

}