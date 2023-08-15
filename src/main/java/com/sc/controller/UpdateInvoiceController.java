package com.sc.controller;

import com.sc.model.VaultItem;
import com.sc.service.InvoiceDownloadFtpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import sc.util.LoggerUtility;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UpdateInvoiceController {

    final LoggerUtility logger;


    final InvoiceDownloadFtpService invoiceDownloadFtpService;

    final VaultItem vaultItem;


    public UpdateInvoiceController(InvoiceDownloadFtpService invoiceDownloadFtpService,  VaultItem vaultItem, LoggerUtility logger) {
        this.invoiceDownloadFtpService = invoiceDownloadFtpService;
        this.vaultItem = vaultItem;
        this.logger = logger;
    }


    @GetMapping("/")
    public ResponseEntity<String> downloadXmlFile(@RequestHeader(value = "X-Correlation-ID", required = false) String correlationId) {

        try {
            invoiceDownloadFtpService.downloadXmlFile();
            logger.info("Success", correlationId);
            return ResponseEntity.status(HttpStatus.OK).body("File Uploaded successfully");
        } catch (Exception e) {
            logger.error("Internal server error", e, correlationId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to  uploaded file: " + e.getMessage());
        }
    }

}

