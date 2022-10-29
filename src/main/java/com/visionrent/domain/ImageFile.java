package com.visionrent.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "t_imagefile")
@Entity
public class ImageFile extends BaseEntity{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;
    private String name;
    private String type;
    private String length;
    @OneToOne(cascade = CascadeType.ALL)
    private ImageData imageData;

    public ImageFile(String name, String type, ImageData imageData) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }
}
