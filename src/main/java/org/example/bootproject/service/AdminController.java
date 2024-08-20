package org.example.bootproject.service;

import org.example.bootproject.entity.Category;
import org.example.bootproject.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-category")
    public Category addCategory(@RequestBody Category category) {
        return adminService.addCategory(category);
    }

    @DeleteMapping("/delete-category/{id}")
    public void deleteCategory(@PathVariable Long id) {
        adminService.removeCategory(id);
    }

    @GetMapping("/all-categories")
    public List<Category> getAllCategories() {
        return adminService.getAllCategories();
    }

    @PostMapping("/add-event")
    public Event addEvent(@RequestBody Event event) {
        return adminService.createEvent(event);
    }

    @PutMapping("/update-event/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return adminService.updateEvent(id, event);
    }

    @DeleteMapping("/delete-event/{id}")
    public void deleteEvent(@PathVariable Long id) {
        adminService.deleteEvent(id);
    }

    @GetMapping("/all-teams/{id}")
    public Set<String> getAllTeams(@PathVariable Long id) {
        return adminService.getAllTeamsByCategory(id);
    }
}
