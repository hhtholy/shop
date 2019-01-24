package com.mini.entity;

import javax.persistence.*;

/**
 * @author hht
 * @create 2019-01-15 13:32
 *
 * 属性表
 */
@Entity
@Table(name = "property")
public class Property {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "id")
        private Integer id;

        @Column(name = "name")
        private String name;

        @ManyToOne
        @JoinColumn(name = "cid")
        private Category category;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
