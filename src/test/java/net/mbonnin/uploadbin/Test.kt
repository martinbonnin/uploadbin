package net.mbonnin.uploadbin

import org.junit.Test

class Test {
    @Test
    fun put() {
        ReleaseHistory.createRelease(7, Track.ALPHA, Service.ANDROID_BUILDS, 100.0, "com.dailymotion.dailymotoin")
    }

    @Test
    fun get() {
        ReleaseHistory.createRelease(7, Track.ALPHA, Service.ANDROID_BUILDS, 100.0, "com.dailymotion.dailymotoin")
    }
}