package dk.group11.rolesystem.repositories

import dk.group11.rolesystem.model.Role
import org.springframework.data.repository.CrudRepository
import java.util.*

interface RoleRepository : CrudRepository<Role, UUID>