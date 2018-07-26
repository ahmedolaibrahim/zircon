package org.codetome.zircon.internal.factory

import org.codetome.zircon.api.data.Size
import org.codetome.zircon.api.util.Cache
import org.codetome.zircon.internal.data.DefaultSize

internal object SizeFactory {

    private val cache: Cache<Size> = Cache.create()

    fun create(xLength: Int, yLength: Int): Size {
        return cache.retrieveIfPresent(Size.generateCacheKey(xLength, yLength)).orElseGet {
            cache.store(DefaultSize(xLength, yLength))
        }
    }
}
