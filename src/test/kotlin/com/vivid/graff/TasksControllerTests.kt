package com.vivid.graff

import com.fasterxml.jackson.databind.ObjectMapper
import com.vivid.graff.shared.Task
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@AutoConfigureMockMvc
class TasksControllerTests : VividApplicationTests() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    @Throws(Exception::class)
    fun `should load all tasks in a proposal suing API`() {
        mockMvc.perform(get("/api/projects/1/proposals/1/tasks"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$[0].id").value(1))
    }

    @Test
    fun `should create new Task with API`() {

        val task = Task {
            proposalId = 1
            scope = "Test"
            name = "UnitTest Task"
            detail = "With more Details"
            cost = 123.0.toBigDecimal()
            qty = 10.0.toBigDecimal()
            unit = "unit"
            profit = 0.0.toBigDecimal()
            profitType = 0
            taskType = "Default"
            taskIdentifier = "1)"
        }

        mockMvc.perform(
            post("/api/projects/{projectId}/proposals/{proposalId}/tasks", 1, 1)
                .content(mapper.writeValueAsString(task))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andDo(print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").exists())
    }

}
