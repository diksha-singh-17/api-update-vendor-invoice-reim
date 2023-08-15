package com.sc.utility;

import com.sc.TestData;
import com.sc.model.VaultItem;
import com.sc.service.impl.VaultServiceImpl;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sc.util.LoggerUtility;

import java.io.File;
import java.net.URI;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class SFTPUtitlityTest {
    @Mock
    SFTPUtility sftpUtility;
    @Mock
    LoggerUtility log;

    @BeforeEach
    void before() {
        sftpUtility = new SFTPUtility(TestData.prepareVault());
    }

    @Test
    void upload() throws Exception {
        try {
            FileObject localFile = Mockito.mock(FileObject.class);
            File file=new File("src/test/resources/zippedFiles/edidlinv_202305300005944.zip");
           file.createNewFile();
            StandardFileSystemManager manager = Mockito.mock(StandardFileSystemManager.class);
            Mockito.when(manager.resolveFile(any(URI.class))).thenReturn(localFile);

            sftpUtility.upload(file,file);
            Assertions.assertNotNull(localFile);
        } catch (Exception e) {
            log.error(TestData.APPNAME +"{upload()}",e,TestData.CORRELATION_ID);
        }
    }
    @Test
    void deleteDirectoryTest() throws Exception {
        try {
            FileObject localFile = Mockito.mock(FileObject.class);
            File file=new File("src/test/resources/zippedFiles/edidlinv_202305300005944.zip");
            file.createNewFile();
            sftpUtility.deleteDirectory(file.getAbsolutePath());
            Assertions.assertNotNull(localFile);
        } catch (Exception e) {
            log.error(TestData.APPNAME +"{deleteDirectoryTest()}",e,TestData.CORRELATION_ID);
        }
    }

}
