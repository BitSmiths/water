package com.bitsmiths.restdocs

fun hello(): String = "Hello, Kotlin/Native!"
fun printIt() {
    val t = restDocTest {
        with type "REST"
        with username "test"
        with password "pass"
        with endpointUrl "http://localhost:8080/val"
        with httpMethod "POST"
        requestPayLoadPlusDocumentation {
            /*with payloadRequestFile ""
            with payloadDocumentationCsvFile ""*/
        }
    }
    println(t)
}


fun main() {
    println(hello())
    printIt()
}