package com.vrms.controller;
import com.vrms.model.User;
import com.vrms.repo.UserRepository;
import com.vrms.security.JwtService;
import jakarta.validation.constraints.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final UserRepository users;private final PasswordEncoder encoder;private final JwtService jwt;
 public AuthController(UserRepository u,PasswordEncoder e,JwtService j){users=u;encoder=e;jwt=j;}
 public record LoginRequest(@Email @NotBlank String email,@NotBlank String password){}
 public record RegisterRequest(@NotBlank String fullName,@Email @NotBlank String email,@Size(min=8) String password){}
 @PostMapping("/register") public ResponseEntity<?> register(@RequestBody RegisterRequest r){
  if(r.fullName()==null||r.fullName().isBlank()||r.email()==null||r.password()==null||r.password().length()<8)return ResponseEntity.badRequest().body(Map.of("message","Name, email and password (at least 8 characters) are required."));
  if(users.findByEmail(r.email().toLowerCase()).isPresent())return ResponseEntity.status(409).body(Map.of("message","Email already registered."));
  User u=users.save(new User(r.fullName(),r.email().toLowerCase(),encoder.encode(r.password()),"CUSTOMER"));return ResponseEntity.ok(payload(u));
 }
 @PostMapping("/login") public ResponseEntity<?> login(@RequestBody LoginRequest r){
  var u=users.findByEmail(r.email().toLowerCase()).orElse(null);
  if(u==null||!encoder.matches(r.password(),u.password))return ResponseEntity.status(401).body(Map.of("message","Invalid email or password."));
  return ResponseEntity.ok(payload(u));
 }
 private Map<String,Object> payload(User u){return Map.of("token",jwt.generate(u.email,u.role),"id",u.id,"fullName",u.fullName,"email",u.email,"role",u.role);}
}
