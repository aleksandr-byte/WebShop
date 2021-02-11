package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "diagonal")
public class Diagonal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diagonal_id", nullable = false)
    private Long id;

    @Column(name = "diagonal_value", nullable = false, length = 100)
    private String value;

}
