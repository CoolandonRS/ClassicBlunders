package com.stultorum.mods.blunders.events

import com.stultorum.mods.blunders.lang.exceptions.AlreadyRegisteredException
import com.stultorum.mods.blunders.lang.exceptions.NotRegisteredException

open class PersistentDataEvent<Persist, Arg> {
    protected val callbacks: MutableSet<Pair<Persist, (Persist, Arg) -> Unit>> = HashSet()

    open fun addCallback(persist: Persist, callback: (Persist, Arg) -> Unit): Boolean = callbacks.add(Pair(persist, callback))
    open fun removeCallback(persist: Persist, callback: (Persist, Arg) -> Unit): Boolean = callbacks.remove(Pair(persist, callback))
    open fun call(arg: Arg) { for (callback in callbacks) callback.second(callback.first, arg) }

    open operator fun plusAssign(callback: Pair<Persist, (Persist, Arg) -> Unit>) {
        if (callbacks.add(callback)) throw AlreadyRegisteredException()
    }
    open operator fun minusAssign(callback: Pair<Persist, (Persist, Arg) -> Unit>) {
        if (callbacks.remove(callback)) throw NotRegisteredException()
    }
    open operator fun invoke(arg: Arg) = call(arg)
}
