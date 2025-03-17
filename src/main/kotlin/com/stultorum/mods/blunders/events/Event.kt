package com.stultorum.mods.blunders.events

import com.stultorum.mods.blunders.lang.exceptions.AlreadyRegisteredException
import com.stultorum.mods.blunders.lang.exceptions.NotRegisteredException

open class Event {
    protected val callbacks: MutableSet<() -> Unit> = HashSet()
    
    open fun addCallback(callback: () -> Unit): Boolean = callbacks.add(callback)
    open fun removeCallback(callback: () -> Unit): Boolean = callbacks.remove(callback)
    open fun call() { for (callback in callbacks) callback() }
    
    open operator fun plusAssign(callback: () -> Unit) {
        if (!addCallback(callback)) throw AlreadyRegisteredException()
    }
    open operator fun minusAssign(callback: () -> Unit) {
        if (!removeCallback(callback)) throw NotRegisteredException()
    }
    open operator fun invoke() = call()
}
