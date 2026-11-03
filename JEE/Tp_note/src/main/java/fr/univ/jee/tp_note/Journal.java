package fr.univ.jee.tp_note;
import java.time.LocalDateTime;

public record Journal (
		String login,
		String uri,
		LocalDateTime date_acces,
		boolean acces_authorise){
	
}
