package com.redcarpet.security.hashing

import org.apache.commons.codec.digest.DigestUtils

class SHA256HashingService: HashingService {

    override fun generateHash(value: String): String {
        return DigestUtils.sha256Hex(value)
    }

    override fun verify(value: String, hash: String): Boolean {
        return DigestUtils.sha256Hex(value) == hash
    }
}