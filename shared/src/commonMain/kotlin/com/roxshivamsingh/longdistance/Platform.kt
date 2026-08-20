package com.roxshivamsingh.longdistance

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform