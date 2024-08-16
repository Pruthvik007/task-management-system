package com.app.taskmanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @JsonIgnore
  @Column(nullable = false)
  private String password;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "user")
  private List<Task> tasks;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserStatus status;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  public User(String name, String email, String password, UserStatus status, UserRole userRole) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.status = status;
    this.userRole = userRole;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.toString()));
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return !UserStatus.INACTIVE.equals(status);
  }

  @Override
  public boolean isAccountNonLocked() {
    return !UserStatus.INACTIVE.equals(status);
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !UserStatus.INACTIVE.equals(status);
  }

  @Override
  public boolean isEnabled() {
    return !UserStatus.INACTIVE.equals(status);
  }

  @Getter
  public enum UserRole {
    USER,
    ADMIN
  }

  public enum UserStatus {
    ACTIVE,
    INACTIVE
  }
}
