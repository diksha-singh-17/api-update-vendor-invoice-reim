package com.sc.service;

import com.sc.exception.ServiceException;

import java.io.IOException;

public interface ZipFilesService {
     String zipXmlFile(String filePath) throws IOException, ServiceException;
}
