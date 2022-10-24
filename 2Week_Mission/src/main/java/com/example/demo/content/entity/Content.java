package com.example.demo.content.entity;

import com.example.demo.articleAndContent.entity.ArticleAndContent;
import com.example.demo.base.BaseEntity;
import com.example.demo.member.entity.Member;
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
public class Content extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @Column
    private String subject;

    @Column
    private String Content;

    @Column
    private String ContentHtml;

    @OneToMany(mappedBy = "content")
    private List<ArticleAndContent> articleAndContentList = new ArrayList<>();




}
