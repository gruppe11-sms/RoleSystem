package dk.group11.rolesystem.controllers

import dk.group11.rolesystem.models.ApplicationUser
import dk.group11.rolesystem.services.UserService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController(val userService: UserService) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: UUID): ApplicationUser {
        return userService.getUser(id)
    }

    @GetMapping
    fun getUsers(): List<ApplicationUser> {
        return userService.getAllUsers()
    }

    @PostMapping
    fun createUser(@RequestBody user: ApplicationUser) {
        userService.createUser(user)
    }

    @PutMapping
    fun updateUser(@RequestBody user: ApplicationUser) {
        userService.updateUser(user)
    }

    @DeleteMapping("/[id}")
    fun deleteUser(@PathVariable id: UUID) {
        userService.deleteUser(id)
    }
}