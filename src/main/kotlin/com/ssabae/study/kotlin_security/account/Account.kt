package com.ssabae.study.kotlin_security.account

import org.hibernate.annotations.CreationTimestamp
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.time.LocalDateTime
import java.util.stream.Collectors
import javax.persistence.*

@Entity
class Account(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var email: String,
    var password: String,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableSet<AccountRole>,

    @CreationTimestamp
    var createDt: LocalDateTime = LocalDateTime.now()
) {
//    constructor() : this() {
//
//    }

    fun getAuthorities(): User {
        return User(
            this.email, this.password,
            this.roles.stream().map { role -> SimpleGrantedAuthority("ROLE_$role") }.collect(Collectors.toSet())
        )
    }
    //테스트 성곤
    //테스트 성곤
}

