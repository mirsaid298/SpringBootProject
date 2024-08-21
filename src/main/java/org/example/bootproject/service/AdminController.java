package org.example.bootproject.service;

import org.example.bootproject.entity.Category;
import org.example.bootproject.entity.Event;
import org.example.bootproject.entity.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> addEvent(@RequestBody Event event) {
        try {
            Event newEvent = adminService.createEvent(
                    event,
                    event.getFirst_team(),
                    event.getSecond_team(),
                    event.getCategory().getId()
            );
            return ResponseEntity.ok(newEvent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update-event/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        try {
            Event updatedEvent = adminService.updateEvent(id, event);
            return ResponseEntity.ok(updatedEvent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-event/{id}")
    public void deleteEvent(@PathVariable Long id) {
        adminService.deleteEvent(id);
    }

    @GetMapping("/teams/{categoryId}")
    public List<Teams> findTeamsByCategoryId(@PathVariable Long categoryId) {
        return adminService.findTeamsByCategoryId(categoryId);
    }
}
