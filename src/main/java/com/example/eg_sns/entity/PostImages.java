package com.example.eg_sns.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

//投稿画像Entityクラス。
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "post_images")
public class PostImages extends EntityBase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** ユーザーID */
    @Column(name = "users_id", nullable = false)
    private Long usersId;

    /** 投稿ID */
    @Column(name = "posts_id", nullable = false)
    private Long postsId;

    /** 投稿画像 */
    @Column(name = "image_uri")
    private String imageUri;

    /** ユーザー情報とのJOIN */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Users users;

    /** 投稿情報とのJOIN */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Posts posts;
}
