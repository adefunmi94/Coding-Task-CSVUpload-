package com.uploadcsvfile.UploadCsvFile.client.dto;


import com.uploadcsvfile.UploadCsvFile.message.ResponseMessage;
import com.uploadcsvfile.UploadCsvFile.model.CSVFile;
import com.uploadcsvfile.UploadCsvFile.service.CSVFileService;
import com.uploadcsvfile.UploadCsvFile.utility.HelperClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/csv")
@Slf4j
public class CsvFileController {


    @Autowired
    CSVFileService csvFileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

        log.info("File goes through = {}", file);
        String message = " ";

        if (HelperClass.hasCSVFormat(file)) {
            try {
               log.info("It on DB or not == {}", csvFileService.save(file));

                log.info("File goes through = {}", file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/csv-files")
    public ResponseEntity<List<CSVFile>> getAllCsvFiles() {
        try {
            List<CSVFile> csvFiles= csvFileService.getAllCsvFiles();

            if (csvFiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(csvFiles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> saveData(@RequestBody CSVFile csvFile){
        log.info("I have records {}", csvFile);

        String message="";

        if (csvFile == null){
            message = "Could go through: !";
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseMessage(message));
        }

        message = "Data successfully save: ";

        csvFileService.saveData(csvFile);
        System.out.println(csvFile + "saved");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseMessage> findUserByPrimaryKey(@PathVariable Long id) {
       CSVFile csvFile= csvFileService.findById(id);
        String  message= "";

       if (csvFile == null){
//
           message = "Oops!  No data found " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
        }
       message = "" +csvFile;
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        String message = "";
        CSVFile csvFile = csvFileService.deleteById(id);

        if (csvFile == null) {
            message = "Oops!  No data to delete " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(message));
        }
        return ResponseEntity.ok().body(csvFile);

    }


}
