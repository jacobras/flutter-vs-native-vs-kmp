package com.sample.cats.network

import io.ktor.client.engine.HttpClientEngineFactory

expect fun createKtorEngine(): HttpClientEngineFactory<*>