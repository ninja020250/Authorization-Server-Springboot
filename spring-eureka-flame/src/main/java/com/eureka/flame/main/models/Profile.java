package com.eureka.flame.main.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Nullable
    @Size(max = 50)
    @Column(nullable = true)
    private String firstName;

    @Nullable
    @Size(max = 50)
    @Column(nullable = true)
    private String lastName;

    @Column(nullable = true, length = 64)
    private String photos;

    @Nullable
    @Size(max = 11)
    @Column(nullable = true)
    private String mobilePhone;

    @Nullable
    @Column(nullable = true)
    private String city;

    @Nullable
    @Column(nullable = true)
    private String district;

    @Nullable
    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private Date birthDay;

    @Size(max = 255)
    @Nullable
    @Column(nullable = true)
    private String objective;

    public Profile(Long id, @Nullable @Size(max = 50) String firstName, @Nullable @Size(max = 50) String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Transient
    public String getFullname() {
        if (firstName == null || lastName == null) return null;
        return String.format("%s %s", lastName, firstName);
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;
        return "/user-photos/" + id + "/" + photos;
    }
}
