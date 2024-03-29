package com.sfacl.magnus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "systemUser")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "last_password_change")
    private LocalDate lastPasswordChange;


    @ElementCollection
    @CollectionTable(name = "user_password_history", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "password")
    private List<String> passwordHistory = new ArrayList<>();

    // Method to add a password to the password history
    public void addPasswordToHistory(String password) {
        passwordHistory.add(password);
        // Ensure that only the last two passwords are stored in history
        if (passwordHistory.size() > 2) {
            passwordHistory.remove(0);
        }
    }
    public String getSecondLastPassword() {
        if (passwordHistory.size() >= 2) {
            return passwordHistory.get(passwordHistory.size() - 2);
        }
        return null;
    }



    @OneToMany(mappedBy = "user")
    private List<Token> tokens;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
