package com.sample.cats.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual fun createKtorEngine(): HttpClientEngineFactory<*> = Darwin