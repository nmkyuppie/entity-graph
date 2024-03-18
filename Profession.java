package com.natwest.mmm.user.application.domain.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "profession")
@NamedEntityGraph(name = "Profession.professions",
        attributeNodes = @NamedAttributeNode("professions")
)
class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profession_generator")
    @SequenceGenerator(name = "profession_generator", sequenceName = "profession_id_seq", allocationSize = 20)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "createdAt", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updatedAt", nullable = false, updatable = false)
    @LastModifiedDate
    private Instant updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profession", orphanRemoval = true)
    private List<User> professions = new ArrayList<>();

    @Override
    public int hashCode() {
        return 13;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Profession other = (Profession) obj;
        return id != null && id.equals(other.getId());
    }
}
