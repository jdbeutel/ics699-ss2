package com.getsu.wcy.auth

class User {

	transient springSecurityService

	String username     // primary email address
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

    static hasMany = [oAuthIDs: OAuthID]

	static transients = ['springSecurityService', 'email']

	static constraints = {
		username blank: false, unique: true, email: true
		password blank: false, nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

    String getEmail() {
        username
    }

    void setEmail(String email) {
        username = email
    }

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
        if (password != null) {
            password = springSecurityService.encodePassword(password)
        }
	}

    String getPassword() {
        password == null ? 'matches no encryption' : password
    }
}
