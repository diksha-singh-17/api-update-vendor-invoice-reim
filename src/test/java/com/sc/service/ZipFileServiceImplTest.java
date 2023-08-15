package com.sc.service;

import com.sc.TestData;
import com.sc.service.impl.ZipFilesServiceImpl;
import com.sc.utility.SFTPUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sc.util.LoggerUtility;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
 class ZipFileServiceImplTest {
    @Mock
    ZipFilesServiceImpl zipFilesService;
    @Mock
    LoggerUtility log;

    @Mock
    SFTPUtility sftpUtility;

    @BeforeEach
    void setup() {
        zipFilesService = new ZipFilesServiceImpl(TestData.prepareVault(),sftpUtility);
    }

    @Test
    void zipXmlFileTest() throws Exception  {
        try {
            assertNull(zipFilesService.zipXmlFile("src/test/resources/testing"));
        }
        catch(Exception e){
          log.error(TestData.APPNAME +"{zipXmlFileTest()}",e,TestData.CORRELATION_ID);
        }
    }


}
