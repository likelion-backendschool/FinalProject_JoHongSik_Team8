package com.example.demo.content.entity;

import com.example.demo.base.BaseEntity;
import com.example.demo.member.entity.Member;
import lombok.*;

import javax.persistence.*;

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




}
