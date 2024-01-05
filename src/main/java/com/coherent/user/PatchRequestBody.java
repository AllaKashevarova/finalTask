package com.coherent.user;

import lombok.Data;

@Data
public class PatchRequestBody {
    private User userNewValues;
    private User userToChange;

    public PatchRequestBody(User userNewValues, User userToChange) {
        this.userNewValues = userNewValues;
        this.userToChange = userToChange;
    }
}
