package com.stultorum.mods.blunders.lang.exceptions

// There has to be an easier way to do this

open class RegistrationException: IllegalArgumentException {
    constructor(): super(); constructor(msg: String): super(msg)
}
class AlreadyRegisteredException: RegistrationException {
    constructor(): super(); constructor(msg: String): super(msg)
}
class NotRegisteredException : RegistrationException {
    constructor(): super(); constructor(msg: String): super(msg)
}

class ObjectDisposedException : IllegalStateException()