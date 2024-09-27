package han.aim.se.noyoumaynot.movie.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurePass {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }
}
