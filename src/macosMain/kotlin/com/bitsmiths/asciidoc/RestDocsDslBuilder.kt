package com.bitsmiths.restdocs

import kotlinx.cinterop.CPointer
import platform.posix.FILE
import platform.posix.fopen
import platform.posix.perror



@DslMarker
annotation class RestDocDsl

fun restDocTest(restDocTest: RestDocTestDSL.() -> Unit): RestDocTestDSL = RestDocTestDSL().apply(restDocTest)
@RestDocDsl
class RestDocTestDSL {
    var testType = TestTypeEnum.REST
    val with = this
    private val authorization = Authorization()

    private lateinit var url: String

    private lateinit var requestPayLoadPlusDocumentation: RequestPayLoadPlusDocumentation

    private lateinit var httpMethod: HttpMethod

    infix fun type(testType: String): RestDocTestDSL {
        this.testType = TestTypeEnum.valueOf(testType)
        return this
    }

    infix fun username (username: String): RestDocTestDSL {
        this.authorization.username = username
        return this
    }
    infix fun password(password: String): RestDocTestDSL{
        this.authorization.password = password
        return this
    }
    infix fun endpointUrl(url: String): RestDocTestDSL{
        this.url = url
        return this
    }
    infix fun httpMethod(httpMethod: String): RestDocTestDSL {
        this.httpMethod = HttpMethod.valueOf(httpMethod)
        return this
    }
    fun requestPayLoadPlusDocumentation(request: RequestPayLoadPlusDocumentation.() -> Unit) : RestDocTestDSL {
        this.requestPayLoadPlusDocumentation = RequestPayLoadPlusDocumentation()
        this.requestPayLoadPlusDocumentation.apply(request)
        return this
    }

    override fun toString(): String {
        return "RestDocTestDSL(testType=$testType, authorization=$authorization, url=$url, requestPayLoadPlusDocumentation=$requestPayLoadPlusDocumentation, httpMethod=$httpMethod)"
    }


}

enum class TestTypeEnum {
    REST, SOAP, RABBITMQ
}

 class Authorization{
    var username: String? = null
    var password: String? = null
}


enum class HttpMethod {
    POST, PUT, GET, DELETE
}

@RestDocDsl
class RequestPayLoadPlusDocumentation{
    private var payloadRequestFile: CPointer<FILE>? = null
    private  var payloadDocumentationCsvFile: CPointer<FILE>? = null
    public val with = this

    infix fun payloadRequestFile(file: String): RequestPayLoadPlusDocumentation {
        this.payloadRequestFile = fopen(file, "r")
        if (payloadRequestFile == null) {
            perror("cannot open input file $file")

        }
        return this
    }

    infix fun payloadDocumentationCsvFile(file: String): RequestPayLoadPlusDocumentation {
        this.payloadDocumentationCsvFile = fopen(file, "r")
        if (payloadDocumentationCsvFile == null) {
            perror("cannot open input file $file")

        }
        return this
    }

}

