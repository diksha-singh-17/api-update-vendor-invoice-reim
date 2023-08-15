package com.sc.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.sc.exception.ServiceException;
import com.sc.model.VaultItem;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.springframework.stereotype.Component;
@Component
public class SFTPUtility {
    VaultItem vaultItem;

    public SFTPUtility(VaultItem vaultItem) {
        this.vaultItem = vaultItem;
    }
    public void upload(File file,File downloadedFile) throws ServiceException, IOException {
        System.setProperty(Constants.SYSTEM_PROPERTIES, Constants.SYSTEM_PROPERTIES_SLASH);
        if (!file.exists())
            throw new ServiceException("Error. Local file not found");
        StandardFileSystemManager manager = new StandardFileSystemManager();
        try {
            manager.init();
            FileObject localFile = manager.resolveFile(file.getAbsolutePath());
            FileObject remoteFile = manager.resolveFile(createConnectionString(vaultItem.getHostName(), vaultItem.getUserName(), vaultItem.getPassword(), vaultItem.getRemoteFilePath()), createDefaultOptions());
            remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);
//            Delete downloaded file
            deleteDirectory(downloadedFile.getAbsolutePath());
//            Delete zipped file
            deleteDirectory(file.getAbsolutePath());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        finally {
            manager.close();
        }
    }
    public void deleteDirectory(String path) throws IOException {
            Files.delete(Path.of(path));
    }


    public static String createConnectionString(String hostName, String username, String password, String remoteFilePath) {
        return "sftp://" + username + ":" + password + "@" + hostName + "/" + remoteFilePath;
    }
    public static FileSystemOptions createDefaultOptions() throws FileSystemException {
        FileSystemOptions opts = new FileSystemOptions();
        SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
        SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);

        return opts;
    }
}