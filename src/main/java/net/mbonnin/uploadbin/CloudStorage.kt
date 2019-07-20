package net.mbonnin.uploadbin

import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions

object CloudStorage {
    val service by lazy {
        StorageOptions.getDefaultInstance().service!!
    }
    val bucket by lazy {
        service.get("uploadbin.appspot.com")!!
    }
}