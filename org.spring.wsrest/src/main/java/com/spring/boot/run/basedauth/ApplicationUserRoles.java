package com.spring.boot.run.basedauth;

import com.google.common.collect.Sets;

import static com.spring.boot.run.basedauth.ApplicationUserPermission.*;

import java.util.Set;

public enum ApplicationUserRoles {

	STUDENT(Sets.newHashSet()), ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));

	private final Set<ApplicationUserPermission> permissions;

	ApplicationUserRoles(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}

}
