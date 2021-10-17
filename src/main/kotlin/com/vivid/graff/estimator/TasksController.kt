package com.vivid.graff.estimator

import com.vivid.graff.shared.ProposalRepository
import com.vivid.graff.shared.Task
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.toList
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/projects/{projectId}/proposals/{proposalId}/tasks")
class TasksController(private val pr: ProposalRepository) {


    @GetMapping
    fun getAll(@PathVariable("proposalId") proposalId: Int) : List<Task> {
        return pr.tasks.filter { it.proposalId eq proposalId }.toList()
    }

    @PostMapping()
    fun insert(@PathVariable("proposalId") proposalId: Int, @RequestBody task: Task): ResponseEntity<Task> {
        return ResponseEntity(pr.insertTask(task),HttpStatus.CREATED)
    }

    @PutMapping("/{taskId}")
    fun update(@PathVariable("taskId") taskId: Int, @RequestBody task: Task): ResponseEntity<Boolean> {
        return ResponseEntity(pr.updateTask(task), HttpStatus.OK)
    }


}