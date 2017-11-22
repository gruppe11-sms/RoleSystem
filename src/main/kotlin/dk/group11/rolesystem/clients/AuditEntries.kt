package dk.group11.rolesystem.clients

import dk.group11.rolesystem.models.ApplicationGroup
import dk.group11.rolesystem.models.ApplicationUser
import dk.group11.rolesystem.models.Role

data class UserAuditEntry(val username: String, val name: String, val userId: Long)
data class RoleAuditEntry(val key: String, val title: String, val description: String)
data class GroupAuditEntry(val id: Long, val title: String, val description: String)
data class UserWithRole(val user: UserAuditEntry, val role: RoleAuditEntry)
data class UserWithRoles(val user: UserAuditEntry, val role: List<RoleAuditEntry>)
data class UserWithGroup(val user: UserAuditEntry, val group: GroupAuditEntry)
data class UserWithGroups(val user: UserAuditEntry, val group: List<GroupAuditEntry>)
class LoginAuditEntryData(val username: String, val userId: Long)


fun ApplicationUser.toAuditEntry(): UserAuditEntry = UserAuditEntry(username = this.username, name = this.name, userId = this.id)
fun Role.toAuditEntry(): RoleAuditEntry = RoleAuditEntry(key = this.id, description = this.description, title = this.title)
fun ApplicationGroup.toAuditEntry(): GroupAuditEntry = GroupAuditEntry(id = this.id, description = this.description, title = this.title)