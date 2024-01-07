package com.example.demo.models;

import com.example.demo.serializers.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;
import java.util.UUID;

@Data
@Document("users")
@JsonIgnoreProperties({"password", "_id", "emailActivation"})
public class User {

    public static class UserEmailActivation {
        @Field(targetType = FieldType.STRING, write = Field.Write.ALWAYS)
        private String code;
        @Field(targetType = FieldType.BOOLEAN)
        private boolean wasActivated;
        @Field(targetType = FieldType.DATE_TIME, write = Field.Write.ALWAYS)
        @JsonSerialize(using = CustomDateSerializer.class)
        private Date expiresAt;

        public UserEmailActivation() {
            this.code = null;
            this.wasActivated = false;
            this.expiresAt = null;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isWasActivated() {
            return wasActivated;
        }

        public void setWasActivated(boolean wasActivated) {
            this.wasActivated = wasActivated;
        }

        public Date getExpiresAt() {
            return expiresAt;
        }

        public void setExpiresAt(Date expiresAt) {
            this.expiresAt = expiresAt;
        }
    }

    @Id
    private String _id;
    @Field(targetType = FieldType.STRING)
    private UUID id;
    @Field(targetType = FieldType.STRING)
    private String username;
    @Field(targetType = FieldType.STRING)
    private String email;
    @Field(targetType = FieldType.STRING)
    private String password;
    @CreatedDate
    @Field(targetType = FieldType.DATE_TIME)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createdAt;
    @LastModifiedDate
    @Field(targetType = FieldType.DATE_TIME)
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updatedAt;

    private UserEmailActivation emailActivation;

    public User() {
        this.id = UUID.randomUUID();
        this.createdAt = new Date();
        this.updatedAt = new Date();

        this.emailActivation = new UserEmailActivation();
    }

    public String get_id() {
        return _id;
    }

    public UserEmailActivation getEmailActivation() {
        return emailActivation;
    }

    public void setEmailActivation(UserEmailActivation emailActivation) {
        this.emailActivation = emailActivation;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public User(String username, String email, String password, Date createdAt, Date updatedAt) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}


