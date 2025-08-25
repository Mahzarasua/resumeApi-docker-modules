package dev.mhzars.projects.commons.resumeapidockercompose.model;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public class CommonResume {
    private String firstName;
    private String lastName;
    private String title;
    private String city;
    private String state;
    private String country;
    private String email;
    private String phone;
    private String summary;
    private LocalDateTime creationDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommonResume that = (CommonResume) o;
        return Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(title, that.title)
                && Objects.equals(city, that.city)
                && Objects.equals(state, that.state)
                && Objects.equals(country, that.country)
                && Objects.equals(email, that.email)
                && Objects.equals(phone, that.phone)
                && Objects.equals(summary, that.summary)
                && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                firstName,
                lastName,
                title,
                city,
                state,
                country,
                email,
                phone,
                summary,
                creationDate);
    }
}
