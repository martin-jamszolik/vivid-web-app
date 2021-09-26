package com.vivid.graff

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc


@AutoConfigureMockMvc
class TasksControllerTests : VividApplicationTests()  {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    @Throws(Exception::class)
    fun `should load all tasks in a proposal`() {
        mockMvc.perform(get("/api/projects/1/proposals/1/tasks"))
            .andDo(print())
            .andExpect(status().isOk)
    }

}
