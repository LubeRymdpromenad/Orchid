package com.example.orchid.common

import io.reactivex.schedulers.Schedulers

class MockSchedulerProvider : SchedulerProvider {
    override fun ui() = Schedulers.trampoline()

    override fun computation() = Schedulers.trampoline()

    override fun io() = Schedulers.trampoline()
}