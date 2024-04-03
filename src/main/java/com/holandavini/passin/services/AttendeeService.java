package com.holandavini.passin.services;

import com.holandavini.passin.controllers.AttendeeController;
import com.holandavini.passin.domain.attendee.Attendee;
import com.holandavini.passin.domain.checkin.CheckIn;
import com.holandavini.passin.dto.attendee.AttendeeDetails;
import com.holandavini.passin.dto.attendee.AttendeeListResponseDTO;
import com.holandavini.passin.repositories.AttendeeRepository;
import com.holandavini.passin.repositories.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckInRepository checkInRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeeListResponseDTO getEventAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
           Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeeListResponseDTO(attendeeDetailsList);
    }
}
