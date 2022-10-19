package com.example.demo.article.entity;

import com.example.demo.articleAndContent.entity.ArticleAndContent;
import com.example.demo.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false , unique = true)
    private String name;

    @OneToMany(mappedBy = "article")
    private List<ArticleAndContent> articleAndContentList = new ArrayList<>();
}
