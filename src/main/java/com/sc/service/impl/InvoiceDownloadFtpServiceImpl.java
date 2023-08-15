package com.sc.service.impl;

import com.sc.model.VaultItem;
import com.sc.service.InvoiceDownloadFtpService;
import com.sc.service.ZipFilesService;
import com.sc.utility.Constants;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Pattern;

@Service
public class InvoiceDownloadFtpServiceImpl implements InvoiceDownloadFtpService {

    String regex = "^INV_PO.+\\.txt.+\\.txt.+([0-9]{2}-[0-9]{2}-[0-9]{4}).+";

    VaultItem vaultItem;

    ZipFilesService zipFilesService;

    public InvoiceDownloadFtpServiceImpl(VaultItem vaultItem, ZipFilesService zipFilesService) {
        this.vaultItem = vaultItem;
        this.zipFilesService = zipFilesService;
    }

    @Override
    public String downloadXmlFile() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(vaultItem.getFtpHost(), Integer.parseInt(vaultItem.getFtpPort()));
        ftpClient.login(vaultItem.getFtpUser(), vaultItem.getFtpPass());
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        Pattern pattern = Pattern.compile(regex);

        File folder = new File("src/main/resources/downloadedFiles");
        folder.mkdirs();
        String[] fileNames = ftpClient.listNames(vaultItem.getFtpRemoteDirectory());
        for (String fileName : fileNames) {
            if (pattern.matcher(fileName).matches()) {
                try (FileOutputStream fosFile = new FileOutputStream(Constants.DOWNLOADED_FILE_PATH + "/" + fileName);
                     FileInputStream fis = new FileInputStream(Constants.DOWNLOADED_FILE_PATH + "/" + fileName);) {
                    ftpClient.retrieveFile(vaultItem.getFtpRemoteDirectory() + "/" + fileName, fosFile);
                }
            }
        }
        zipFilesService.zipXmlFile(Constants.DOWNLOADED_FILE_PATH);
        ftpClient.logout();

        return null;
    }



}



