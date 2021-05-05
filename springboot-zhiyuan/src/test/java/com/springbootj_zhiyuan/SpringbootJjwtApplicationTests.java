package com.springbootj_zhiyuan;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class SpringbootJjwtApplicationTests {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    private String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMDE4MTAwMzc5MSJ9.gbw7Yu1_xvDAhZNkVbBm8t8N1qLXtK7squLDmqnIN0c";
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    //User
    @Test
    public void userLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/userLogin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"account\": \"20181003791\",\n" +
                        "  \"password\": \"123\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

    }
    @Test
    public void addUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/addUser")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"account\": \"20181003791\",\n" +
                        "  \"username\": \"sky\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void addMoreUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/addUsers")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n"+
                        "{\n" +
                        "  \"account\": \"20181003791\",\n" +
                        "  \"username\": \"sky\"\n" +
                        "},\n"+
                        "{\n" +
                        "  \"account\": \"wssb\",\n" +
                        "  \"username\": \"miao\"\n" +
                        "}\n"+
                        "]")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void updatePassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/changePw/1")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"passowrd\": \"123\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void change_u() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/change")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"account\": \"20181003791\",\n" +
                        "  \"role\": \"1\",\n" +
                        "  \"username\": \"sky\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void sendUserTable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user?pagesize=2&pagenum=1")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

    }
    @Test
    public void userDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/deleteUser/1")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

    }
    //组织
    @Test
    public void addGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/group/addGroup")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"groupname\": \"lzh\",\n" +
                        "  \"company\": \"lzh\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void updateGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/group/updateGroup")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"groupid\": \"2\",\n" +
                        "  \"groupname\": \"lzh\",\n" +
                        "  \"company\": \"lzh\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void groupDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/group/deleteGroup/1")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

    }
    @Test
    public void deleteGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/UrAndGp/delete/1&2")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

    }
    @Test
    public void addUrAndGp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/UrAndGp/add")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n"+
                        "{\n" +
                        "  \"account\": \"1\",\n" +
                        "  \"groupid\": \"5\"\n" +
                        "}\n"+
                        "]")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    //活动
    @Test
    public void findActive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/active/find?title=淼淼")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

    }
    @Test
    public void addActive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/active/add")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\n"+
                        "{\n" +
                        "  \"title\": \"1\",\n" +
                        "  \"desc\": \"1\",\n" +
                        "  \"content\": \"hello world\"\n" +
                        "}\n"+
                        "]")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void updateActive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/active/update?activityid=2")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"title\": \"hello world\"\n" +
                        "}\n")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void deleteActive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/active/delete/1")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

    }
    @Test
    public void sendacTable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/active?pagesize=2&pagenum=2")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

    }
    @Test
    public void findacUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/active/findusers?activityid=1")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

    }
    @Test
    public void findUrAcs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/findacs?account=20181003791")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

    }
    @Test
    public void addUrAcs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/addacs?activityid=3&account=20181003791")
                .header("token",token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());

    }
}
