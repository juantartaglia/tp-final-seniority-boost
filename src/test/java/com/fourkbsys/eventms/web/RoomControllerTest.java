package com.fourkbsys.eventms.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fourkbsys.eventms.domain.Room.Room;
import com.fourkbsys.eventms.domain.Room.RoomService;
import com.fourkbsys.eventms.domain.Room.RoomStatus;
import com.fourkbsys.eventms.web.dto.RoomDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private RoomService roomService;

    @Test
    @DisplayName("getRoomById method should return a Room when roomId is valid")
    void getRoomById() throws Exception {

        Room room = new Room(1L, "Name Room1", "Description Room1", "Location Room1", 10, RoomStatus.ENABLED);

        Mockito.when(roomService.getById(1)).thenReturn(Optional.of(room));

        RequestBuilder req = MockMvcRequestBuilders
                .get("/rooms/1")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("roomId", Matchers.is(1)))
                .andExpect(jsonPath("name", Matchers.is("Name Room1")))
                .andExpect(jsonPath("description", Matchers.is("Description Room1")))
                .andExpect(jsonPath("location", Matchers.is("Location Room1")))
                .andExpect(jsonPath("maxCapacity", Matchers.is(10)))
                .andExpect(jsonPath("status", Matchers.is("enabled")))
                .andReturn();
    }

    @Test
    @DisplayName("getRoomById method should return status 404 when roomId is not found")
    void getRoomByIdNotFound() throws Exception {

        Room room = new Room(1L, "Name Room1", "Description Room1", "Location Room1", 10, RoomStatus.ENABLED);

        Mockito.when(roomService.getById(1)).thenReturn(Optional.empty());

        RequestBuilder req = MockMvcRequestBuilders
                .get("/rooms/1")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(req)
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @DisplayName("getRoomList method should return a Room List")
    void getRoomList() throws Exception {

        Room roomOne = new Room(1L, "Name Room1", "Description Room1", "Location Room1", 10, RoomStatus.ENABLED);
        Room roomTwo = new Room(2L, "Name Room2", "Description Room2", "Location Room2", 20, RoomStatus.ENABLED);

        Mockito.when(roomService.getAll()).thenReturn(List.of(roomOne, roomTwo));

        RequestBuilder req = MockMvcRequestBuilders
                .get("/rooms/")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(req)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.[0].roomId").value(equalTo(1)))
                .andExpect(jsonPath("$.[0].description").value(equalTo("Description Room1")))
                .andExpect(jsonPath("$.[0].status").value(equalTo("enabled")))
                .andExpect(jsonPath("$.[0].maxCapacity").value(equalTo(10)))
                .andReturn();
    }



    @Test
    @DisplayName("createRoom method should return status 201 when Room data is valid")
    void createRoom() throws Exception {

        Room toCreate = Room.builder()
                .name("Name Room1")
                .description("Description Room1")
                .location("Location Room1")
                .maxCapacity(100)
                .status(RoomStatus.ENABLED)
                .build();

        Room result = Room.builder()
                .roomId(1L)
                .name("Name Room1")
                .description("Description Room1")
                .location("Location Room1")
                .maxCapacity(100)
                .status(RoomStatus.ENABLED)
                .build();

        RoomDTO roomDTO = RoomDTO.builder()
                .name("Name Room1")
                .description("Description Room1")
                .location("Location Room1")
                .maxCapacity(100)
                .build();

        Mockito.when(roomService.createRoom(toCreate)).thenReturn(result);

        ObjectMapper bodyInputObj = new ObjectMapper();
        bodyInputObj.registerModule(new JavaTimeModule());

        ResultActions response = this.mockMvc.perform(post("/rooms/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyInputObj.writeValueAsString(roomDTO)));

        response.andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("createRoom method should return status 400 when maxCapacity is null")
    void createRoomWithMaxCapacityNull() throws Exception {

        RoomDTO roomDTO = RoomDTO.builder()
                .name("Name Room1")
                .description("Description Room1")
                .location("Location Room1")
                //.maxCapacity(100)
                .build();

        ObjectMapper bodyInputObj = new ObjectMapper();
        bodyInputObj.registerModule(new JavaTimeModule());

        ResultActions response = this.mockMvc.perform(post("/rooms/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyInputObj.writeValueAsString(roomDTO)));

        response.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("createRoom method should return status 400 when maxCapacity is zero")
    void createRoomWithMaxCapacityZero() throws Exception {

        RoomDTO roomDTO = RoomDTO.builder()
                .name("Name Room1")
                .description("Description Room1")
                .location("Location Room1")
                .maxCapacity(0)
                .build();

        ObjectMapper bodyInputObj = new ObjectMapper();
        bodyInputObj.registerModule(new JavaTimeModule());

        ResultActions response = this.mockMvc.perform(post("/rooms/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyInputObj.writeValueAsString(roomDTO)));

        response.andExpect(status().isBadRequest());
    }



    @Test
    @DisplayName("createRoom method should return status 400 when name is null")
    void createRoomWithNameNull() throws Exception {

        RoomDTO roomDTO = RoomDTO.builder()
                .description("Description Room1")
                .location("Location Room1")
                .maxCapacity(100)
                .build();

        ObjectMapper bodyInputObj = new ObjectMapper();
        bodyInputObj.registerModule(new JavaTimeModule());

        ResultActions response = this.mockMvc.perform(post("/rooms/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyInputObj.writeValueAsString(roomDTO)));

        response.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("createRoom method should return status 400 when location is null")
    void createRoomWithLocationNull() throws Exception {

        RoomDTO roomDTO = RoomDTO.builder()
                .name("Name Room1")
                .description("Description Room1")
                .maxCapacity(100)
                .build();

        ObjectMapper bodyInputObj = new ObjectMapper();
        bodyInputObj.registerModule(new JavaTimeModule());

        ResultActions response = this.mockMvc.perform(post("/rooms/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyInputObj.writeValueAsString(roomDTO)));

        response.andExpect(status().isBadRequest());
    }
}
