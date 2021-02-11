package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "smartphones")
public class SmartPhones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "smartphones_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Diagonal diagonal;
}
