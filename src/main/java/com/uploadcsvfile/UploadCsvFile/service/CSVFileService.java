package com.uploadcsvfile.UploadCsvFile.service;

import com.uploadcsvfile.UploadCsvFile.model.CSVFile;
import com.uploadcsvfile.UploadCsvFile.repo.CSVFileRepo;
import com.uploadcsvfile.UploadCsvFile.utility.HelperClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CSVFileService {


    @Autowired
    CSVFileRepo csvFileRepo;

    public List<CSVFile> save(MultipartFile file) {

        List<CSVFile> csvFiles = new ArrayList<>();

        try {
             csvFiles= HelperClass.csvToModel(file.getInputStream());

            log.info(" Here it saved = {}", csvFiles);
            csvFileRepo.saveAll(csvFiles);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
        return csvFiles;
    }

    public List<CSVFile> getAllCsvFiles() {
        return csvFileRepo.findAll();
    }

    public void saveData(CSVFile csvFile){

        csvFile.setTimestamp(LocalDateTime.now());
        csvFile.setUpdatedTimeStamp(String.valueOf(csvFile.getTimestamp()));

        csvFileRepo.save(csvFile);

    }

    public CSVFile findById(Long primary_key){

        Optional<CSVFile> csvFiles=csvFileRepo.findById(primary_key);

        CSVFile csvFile = null;

        if (csvFiles.isPresent()){
            csvFile= csvFiles.get();
        }
        return csvFile;

    }


    public CSVFile deleteById(Long primary_key){
        CSVFile csvFile = findById(primary_key);

        if (csvFile != null){
            csvFileRepo.deleteById(primary_key);
        }
        return csvFile;
    }
}
