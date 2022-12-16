package com.uploadcsvfile.UploadCsvFile.repo;

import com.uploadcsvfile.UploadCsvFile.model.CSVFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CSVFileRepo extends JpaRepository<CSVFile, Long> {


}
