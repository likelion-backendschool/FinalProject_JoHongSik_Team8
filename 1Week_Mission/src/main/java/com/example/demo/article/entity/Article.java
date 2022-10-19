package com.example.demo.article.entity;

import com.example.demo.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Article extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false , unique = true)
    private String name;
}
