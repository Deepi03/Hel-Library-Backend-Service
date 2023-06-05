package com.rest_api.fs14backend.genre;

import java.io.IOException;

import com.rest_api.fs14backend.Fs14BackendApplication;
import com.rest_api.fs14backend.Fs14BackendApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Fs14BackendApplication.class)
@WebAppConfiguration
public  class GenreControllerTest extends Fs14BackendApplicationTests {
    @Test
    public void getGenresList() throws Exception {
        String uri = "/api/v1/genres/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Genre[] genreList = mapFromJson(content, Genre[].class);
        assertTrue(genreList.length > 0);
    }

    @Test
    public void createGenre() throws Exception {
        String uri = "/api/v1/admin/genres";
        Genre genre = new Genre();
        genre.setId(UUID.randomUUID());
        genre.setName("Gi");
        genre.setDescription("daefeasf");
        genre.setCoverImage("frefrdsfv");

        String inputJson = super.mapToJson(genre);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
    }
}

