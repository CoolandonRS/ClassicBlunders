package com.stultorum.mods.blunders.events

import com.stultorum.mods.blunders.lang.exceptions.AlreadyRegisteredException
import com.stultorum.mods.blunders.lang.exceptions.NotRegisteredException

open class PersistentEvent<Persist> {
    protected val callbacks: MutableSet<Pair<Persist, (Persist) -> Unit>> = HashSet()

    open fun addCallback(persist: Persist, callback: (Persist) -> Unit): Boolean = callbacks.add(Pair(persist, callback))
    open fun removeCallback(persist: Persist, callback: (Persist) -> Unit): Boolean = callbacks.remove(Pair(persist, callback))
    open fun call() { for (callback in callbacks) callback.second(callback.first) }

    open operator fun plusAssign(callback: Pair<Persist, (Persist) -> Unit>) {
        if (callbacks.add(callback)) throw AlreadyRegisteredException()
    }
    open operator fun minusAssign(callback: Pair<Persist, (Persist) -> Unit>) {
        if (callbacks.remove(callback)) throw NotRegisteredException()
    }
    open operator fun invoke() = call()
}
