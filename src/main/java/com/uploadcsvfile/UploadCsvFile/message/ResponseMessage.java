package com.uploadcsvfile.UploadCsvFile.message;

import lombok.Data;

@Data
public class ResponseMessage {

    private String response;

    public ResponseMessage(String response) {
        this.response = response;
    }
}
