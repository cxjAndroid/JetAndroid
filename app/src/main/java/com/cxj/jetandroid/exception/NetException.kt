/**
 *  Created by jon chen
 */
package com.cxj.jetandroid.exception

open class NetException : Exception {
    var errorCode: Int
    var errorMsg: String

    constructor(error: ERROR, e:Throwable?=null):super(e){
        this.errorCode = error.code
        this.errorMsg = error.errMsg
    }

    constructor(errorCode: Int, errorMsg: String, e: Throwable? = null) : super(e) {
        this.errorCode = errorCode
        this.errorMsg = errorMsg
    }

}