package com.zidioProject.eems.Entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    
	@NotBlank(message = "Name cannot be blank!")
    private String full_name;
    
    @Email
    @NotBlank
    @Pattern(
    		regexp = "^[a-z0-9._%+-]+@gmail\\.com$",
    		message = "Email should be in proper format!")
    @Column(unique = true)
    private String email;
    
    @NotBlank(message = "Please enter password!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    @NotNull(message = "No role was selected!")
    @Enumerated(EnumType.STRING)
    private Roles role;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Expenses> expenses;

}
