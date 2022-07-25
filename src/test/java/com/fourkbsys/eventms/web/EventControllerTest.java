package com.fourkbsys.eventms.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourkbsys.eventms.domain.Event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private EventService eventService;

    /*
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


     */

}
