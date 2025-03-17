package com.stultorum.mods.blunders.lang

open class Ref<T>(protected open var obj: T? = null) {
    open fun get(): T? = obj
    open fun set(new: T?) = obj
}
