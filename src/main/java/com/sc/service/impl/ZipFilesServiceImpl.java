package com.sc.service.impl;

import com.sc.exception.ServiceException;
import com.sc.model.VaultItem;
import com.sc.service.ZipFilesService;
import com.sc.utility.SFTPUtility;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Service
public class ZipFilesServiceImpl implements ZipFilesService {
    VaultItem vaultItem;
    SFTPUtility sftpUtility;

    public ZipFilesServiceImpl(VaultItem vaultItem, SFTPUtility sftpUtility) {
        this.vaultItem = vaultItem;
        this.sftpUtility=sftpUtility;
    }

    @Override
    public String zipXmlFile(String filePath) throws IOException, ServiceException {

        File folder = new File(filePath);
        File[] files = folder.listFiles();
        File zipFolder = new File("src/main/resources/zippedFiles");
        zipFolder.mkdirs();

        byte[] buffer = new byte[1024];
        assert files != null;
        for (File file : files) {
            File zipFile = new File(file.toURI());
            if (zipFile.isFile()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("_yyyyMMddHHmmss");
                String currentDateTime = dateFormat.format(new Date());
               String zipFileName = "src/main/resources/zippedFiles/edidlinv" + currentDateTime + ".zip";
                try ( FileOutputStream fos = new FileOutputStream(zipFileName);
                        ZipOutputStream zos = new ZipOutputStream(fos);
                     FileInputStream fis = new FileInputStream(zipFile);)
                {
                    ZipEntry zipEntry = new ZipEntry("edidlinv"+currentDateTime+"");
                    zos.putNextEntry(zipEntry);
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                } catch (Exception e) {
                    throw new ServiceException(e.getMessage());
                }
                File zippedFile = new File(zipFileName);
                sftpUtility.upload(zippedFile,file);
            }
        }

        return null;
    }
}