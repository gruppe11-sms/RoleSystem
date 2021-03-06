package dk.group11.rolesystem.repositories

import dk.group11.rolesystem.models.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, String> {
    fun existsByTitle(title: String): Boolean
    fun findByTitle(s: String): Role?
}