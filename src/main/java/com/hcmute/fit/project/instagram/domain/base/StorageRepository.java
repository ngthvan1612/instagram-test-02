package com.hcmute.fit.project.instagram.domain.base;

import java.io.InputStream;

public interface StorageRepository {
    String saveUploadedStream(String uploadFileName, InputStream inputStream, long length);
}