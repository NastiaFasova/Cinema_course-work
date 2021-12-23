package kpi.models.mapper;

import kpi.models.Ticket;
import kpi.models.dto.response.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketResponseDto getTicketResponseDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setId(ticket.getId());
        ticketResponseDto.setMovieSessionId(ticket.getMovieSession().getId());
        return ticketResponseDto;
    }
}
