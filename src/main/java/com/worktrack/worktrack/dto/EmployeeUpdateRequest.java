package com.worktrack.worktrack.dto;

import com.worktrack.worktrack.enums.Role;
import com.worktrack.worktrack.enums.Status;
import jakarta.validation.constraints.NotBlank;

public class EmployeeUpdateRequest {

    @NotBlank(message = "Name cannot be null")
    private String name;

    private Role role;

    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

