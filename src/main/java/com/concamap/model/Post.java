package com.concamap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Timestamp;

import java.util.Set;

@Entity
@Table(name = "post", schema = "shark_squad")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "title", nullable = false)
    private String title;

    @Basic
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Basic
    @Column(name = "status", nullable = false)
    @JsonIgnore
    private int status = 1;

    @Basic
    @Column(name = "likes", nullable = false)
    private int likes;

    @Basic
    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Basic
    @Column(name = "updated_date", nullable = false)
    private Timestamp updatedDate;

    @Basic
    @Column(name = "anchor_name", nullable = false, unique = true)
    private String anchorName;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    private Set<Attachment> attachments;

    @ManyToOne
    @JoinColumn(name = "categories_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users users;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Comment> comments;

    @Transient
    private MultipartFile multipartFile;
}
