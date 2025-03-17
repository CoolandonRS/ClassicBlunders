package com.stultorum.mods.blunders.lookup

import com.stultorum.mods.blunders.lang.exceptions.AlreadyRegisteredException
import com.stultorum.mods.blunders.lang.exceptions.NotRegisteredException

class Lookup<TKey, TObj: IRetrievable<TKey>>(private val map: HashMap<TKey, TObj> = HashMap()) {


    fun register(obj: TObj) {
        val key = obj.retrieve<TKey>()
        if (map.putIfAbsent(key, obj) != null) throw AlreadyRegisteredException(key.toString())
    }

    fun remove(obj: TObj) = remove(obj.retrieve<TKey>())
    fun remove(key: TKey) {
        if (map.remove(key) == null) throw NotRegisteredException(key.toString())
    }

    operator fun get(key: TKey): TObj? = map[key]
    operator fun plusAssign(obj: TObj) = register(obj)
    @JvmName("minusAssignObj")
    operator fun minusAssign(obj: TObj) = remove(obj)
    @JvmName("minusAssignKey")
    operator fun minusAssign(key: TKey) = remove(key)
}