package com.worktrack.worktrack.dto;

import com.worktrack.worktrack.enums.Role;
import com.worktrack.worktrack.enums.Status;

public class EmployeeRequest {

    private String name;

    private String email;

    private Role role;

    private Status status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
