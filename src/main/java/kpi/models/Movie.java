package kpi.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(unique = true)
    private String apiId;
    private String link;

    public Movie(String apiId, String link, String title) {
        this.title = title;
        this.apiId = apiId;
        this.link = link;
    }
}
