package com.example.core.handler

import com.example.core.data.Data
import com.example.core.data.UseCase

interface Handler {

    fun handle(data: Data, useCase: UseCase): Boolean

    fun setNext(h: Handler)

}