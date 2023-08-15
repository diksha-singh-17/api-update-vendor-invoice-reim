package com.sc.service;

import com.sc.TestData;
import com.sc.service.impl.InvoiceDownloadFtpServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;


import static org.apache.tomcat.util.http.fileupload.FileUtils.deleteDirectory;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
class InvoiceDownloadFtpServiceImplTest {
    InvoiceDownloadFtpServiceImpl invoiceDownloadFtpService;



    @Mock
    ZipFilesService zipFilesService;

    @BeforeEach
    public void setup() {
        invoiceDownloadFtpService = new InvoiceDownloadFtpServiceImpl(TestData.prepareVault(),  zipFilesService);
    }

    @Test
    void downloadXmlFileTest() throws Exception {
        try{
        assertNull(invoiceDownloadFtpService.downloadXmlFile());
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    @Test
    void deleteDirectoryTest() throws Exception{
        File file=new File("src/main/resources/testing");
        deleteDirectory(file);
        Assertions.assertNotNull(file);
    }




}

