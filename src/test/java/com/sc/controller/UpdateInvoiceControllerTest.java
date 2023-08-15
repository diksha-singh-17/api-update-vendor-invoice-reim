package com.sc.controller;

import com.sc.TestData;
import com.sc.service.InvoiceDownloadFtpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sc.util.LoggerUtility;


@ExtendWith(SpringExtension.class)
 class UpdateInvoiceControllerTest {
    UpdateInvoiceController updateInvoiceController;
    @Mock
    InvoiceDownloadFtpService invoiceDownloadFtpService;


    @Mock
    LoggerUtility logger;

    @BeforeEach
    public void setup(){
        updateInvoiceController= new UpdateInvoiceController(invoiceDownloadFtpService,
                TestData.prepareVault(),logger);
    }

    @Test
     void testDownloadXmlFile() throws Exception {
        Mockito.when(invoiceDownloadFtpService.downloadXmlFile()).thenReturn("");
        updateInvoiceController.downloadXmlFile(TestData.CORRELATION_ID);

    }
    @Test
    void testDownloadXmlFileWithException() throws Exception {
        Mockito.when(invoiceDownloadFtpService.downloadXmlFile()).thenThrow(Exception.class);
        updateInvoiceController.downloadXmlFile(TestData.CORRELATION_ID);

    }
}
