package com.stultorum.mods.blunders.events

import com.stultorum.mods.blunders.lang.exceptions.AlreadyRegisteredException
import com.stultorum.mods.blunders.lang.exceptions.NotRegisteredException

open class DataEvent<Arg> {
    protected val callbacks: MutableSet<(Arg) -> Unit> = HashSet()
    
    open fun addCallback(callback: (Arg) -> Unit): Boolean = callbacks.add(callback)
    open fun removeCallback(callback: (Arg) -> Unit): Boolean = callbacks.remove(callback)
    open fun call(obj: Arg) { for (callback in callbacks) callback(obj) }
    
    open operator fun plusAssign(callback: (Arg) -> Unit) {
        if (!addCallback(callback)) throw AlreadyRegisteredException()
    }
    open operator fun minusAssign(callback: (Arg) -> Unit) {
        if (!removeCallback(callback)) throw NotRegisteredException()
    }
    open operator fun invoke(obj: Arg) = call(obj)
}
