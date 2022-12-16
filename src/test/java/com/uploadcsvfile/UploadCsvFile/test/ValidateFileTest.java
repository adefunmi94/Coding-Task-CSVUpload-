package com.uploadcsvfile.UploadCsvFile.test;

import com.uploadcsvfile.UploadCsvFile.model.CSVFile;
import com.uploadcsvfile.UploadCsvFile.repo.CSVFileRepo;
import com.uploadcsvfile.UploadCsvFile.service.CSVFileService;
import com.uploadcsvfile.UploadCsvFile.utility.HelperClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class ValidateFileTest {

   private CSVFileRepo csvFileRepo;
   private CSVFileService csvFileService;


   @Autowired
    public ValidateFileTest(CSVFileRepo csvFileRepo,
                            CSVFileService csvFileService) {
        this.csvFileRepo = csvFileRepo;
        this.csvFileService = csvFileService;
    }

    @Test
    void confirmCSVFile(){

//        assertThat(csvFileRepo.count()).isEqualTo(3);

        assertThat(csvFileService.deleteById(Long.valueOf(5))).isNotNull();
    }
}
