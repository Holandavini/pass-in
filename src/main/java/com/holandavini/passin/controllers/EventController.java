package com.holandavini.passin.controllers;

import com.holandavini.passin.dto.attendee.AttendeeListResponseDTO;
import com.holandavini.passin.dto.event.EventIdDTO;
import com.holandavini.passin.dto.event.EventRequestDTO;
import com.holandavini.passin.dto.event.EventResponseDTO;
import com.holandavini.passin.services.AttendeeService;
import com.holandavini.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String eventId){
        EventResponseDTO event = this.eventService.getEventDetails(eventId);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeeListResponseDTO> getEventAttendees(@PathVariable String id){
        AttendeeListResponseDTO attendessListResponse = this.attendeeService.getEventAttendee(id);
        return ResponseEntity.ok(attendessListResponse);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }
}
