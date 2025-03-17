package com.stultorum.mods.blunders.lookup

interface IRetrievable<TClass> {
    fun <TFunc: TClass> retrieve(): TFunc
}