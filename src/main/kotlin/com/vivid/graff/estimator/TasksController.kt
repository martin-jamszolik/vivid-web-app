package com.vivid.graff.estimator

import com.vivid.graff.shared.Proposal
import com.vivid.graff.shared.ProposalRepository
import com.vivid.graff.shared.Task
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.toList
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/projects/{projectId}/proposals/{proposalId}/tasks")
class TasksController(private val pr: ProposalRepository) {


    @GetMapping
    fun getAll(@PathVariable("proposalId") proposalId: Int) : List<Task> {
        return pr.tasks.filter { it.proposalId eq proposalId }.toList()
    }

    @PostMapping()
    fun insert(@PathVariable("proposalId") proposalId: Int, @RequestBody task: Task): Task {
        return pr.insertTask(task)
    }

    @PutMapping("/{taskId}")
    fun update(@PathVariable("taskId") taskId: Int, @RequestBody task: Task): Boolean {
        return pr.updateTask(task)
    }


}