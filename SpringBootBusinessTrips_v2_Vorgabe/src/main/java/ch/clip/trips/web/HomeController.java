package ch.clip.trips.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String status() {
        return "✅ Backend läuft!";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "✅ Alles bereit für das Frontend!";
    }
}
