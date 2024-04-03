package com.holandavini.passin.services;

import com.holandavini.passin.domain.attendee.Attendee;
import com.holandavini.passin.dto.attendee.AttendeeListResponseDTO;
import com.holandavini.passin.repositories.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private AttendeeRepository attendeeRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeeListResponseDTO getEventAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

    }
}
