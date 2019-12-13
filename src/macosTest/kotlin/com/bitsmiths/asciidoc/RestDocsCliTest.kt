package com.bitsmits.asciidoc

import com.bitsmiths.restdocs.hello
import kotlin.test.Test
import kotlin.test.assertTrue

class RestDocsCliTest {
    @Test
    fun testHello() {
        assertTrue("Kotlin/Native" in hello())
    }
}