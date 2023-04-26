package com.redcarpet.security.token

interface TokenService {

    fun generate(config: TokenConfig, vararg claims: TokenClaim): String

}