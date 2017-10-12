package dk.group11.rolesystem.controllers

import dk.group11.rolesystem.models.ApplicationGroup
import dk.group11.rolesystem.services.GroupService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/groups")
class GroupController(val groupService: GroupService) {

    @GetMapping
    fun getGroups(): List<ApplicationGroup> {
        return groupService.getGroups()
    }

    @GetMapping("/{id}")
    fun getGroup(@PathVariable id: UUID): ApplicationGroup {
        return groupService.getGroup(id)
    }

    @PostMapping
    fun createGroups(@RequestBody group: ApplicationGroup) {
        groupService.createGroups(group)
    }

    @PutMapping
    fun updateGroup(@RequestBody group: ApplicationGroup) {
        groupService.updateGroup(group)
    }

    @DeleteMapping("/{id}")
    fun deleteGroup(@PathVariable id: UUID) {
        groupService.deleteGroup(id)
    }

}


