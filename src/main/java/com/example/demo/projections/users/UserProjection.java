package com.example.demo.projections.users;

import com.example.demo.serializers.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public interface UserProjection {
    String getId();
    String getUsername();
    String getEmail();
    @JsonSerialize(using = CustomDateSerializer.class)
    Date getCreatedAt();
    @JsonSerialize(using = CustomDateSerializer.class)
    Date getUpdatedAt();
}
