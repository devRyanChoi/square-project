package com.square.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String Email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private boolean emailVerified;

    private String emailCheckToken;

    private LocalDateTime joinedAt;

    private String bio;

    private String url;

    private String occupation;

    private String location;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    private boolean officeCreateByEmail;
    private boolean officeCreateByWeb;

    private boolean officeEnrollmentResultByEmail;
    private boolean officeEnrollmentResultByWeb;

    private boolean officeUpdatedByEmail;
    private boolean officeUpdatedByWeb;


}
