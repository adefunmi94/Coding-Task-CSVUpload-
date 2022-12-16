package com.uploadcsvfile.UploadCsvFile.model;

import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class CSVFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String updatedTimeStamp;

    private LocalDateTime timestamp;

    public CSVFile(Long id, String name, String description, String updatedTimeStamp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.updatedTimeStamp = String.valueOf(getTimestamp());
    }

    public CSVFile() {

    }

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }


}
