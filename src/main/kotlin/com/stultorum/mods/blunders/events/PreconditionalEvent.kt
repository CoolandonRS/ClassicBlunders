package com.stultorum.mods.blunders.events

import com.stultorum.mods.blunders.lang.exceptions.AlreadyRegisteredException
import com.stultorum.mods.blunders.lang.exceptions.NotRegisteredException

open class PreconditionalEvent<PreconditionArg> {
    protected val callbacks: HashMap<(PreconditionArg) -> Unit, (PreconditionArg) -> Boolean> = HashMap()

    open fun addCallback(precondition: PreconditionArg, callback: () -> Unit) = addCallback(precondition) { _ -> callback() }
    open fun addCallback(precondition: (PreconditionArg) -> Boolean, callback: () -> Unit) = addCallback(precondition) { _ -> callback() }
    open fun addCallback(precondition: PreconditionArg, callback: (PreconditionArg) -> Unit) = addCallback({it == precondition}, callback)
    open fun addCallback(precondition: (PreconditionArg) -> Boolean, callback: (PreconditionArg) -> Unit): Boolean = callbacks.putIfAbsent(callback, precondition) == null
    open fun removeCallback(callback: () -> Unit): Boolean = callbacks.remove({ _ -> callback() }) != null
    open fun removeCallback(callback: (PreconditionArg) -> Unit): Boolean = callbacks.remove(callback) != null
    open fun call(arg: PreconditionArg) = callbacks.filter { it.value(arg) }.forEach { it.key(arg) }

    @JvmName("plusAssignSimple")
    @Suppress("INAPPLICABLE_JVM_NAME")
    open operator fun plusAssign(obj: Pair<PreconditionArg, () -> Unit>) {
        if (!addCallback(obj.first, obj.second)) throw AlreadyRegisteredException()
    }
    @JvmName("plusAssignComplexPrecondition")
    @Suppress("INAPPLICABLE_JVM_NAME")
    open operator fun plusAssign(obj: Pair<(PreconditionArg) -> Boolean, () -> Unit>) {
        if (!addCallback(obj.first, obj.second)) throw AlreadyRegisteredException()
    }
    @JvmName("plusAssignComplexCallback")
    @Suppress("INAPPLICABLE_JVM_NAME")
    open operator fun plusAssign(obj: Pair<PreconditionArg, (PreconditionArg) -> Unit>) {
        if (!addCallback(obj.first, obj.second)) throw AlreadyRegisteredException()
    }
    @JvmName("plusAssignComplex")
    @Suppress("INAPPLICABLE_JVM_NAME")
    open operator fun plusAssign(obj: Pair<(PreconditionArg) -> Boolean, (PreconditionArg) -> Unit>) {
        if (!addCallback(obj.first, obj.second)) throw AlreadyRegisteredException()
    }
    open operator fun minusAssign(callback: () -> Unit) {
        if (!removeCallback(callback)) throw NotRegisteredException()
    }
    open operator fun minusAssign(callback: (PreconditionArg) -> Unit) {
        if (!removeCallback(callback)) throw NotRegisteredException()
    }
    open operator fun invoke(arg: PreconditionArg) = call(arg)
}