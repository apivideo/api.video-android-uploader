package video.api.uploader.api.upload;

public interface UploadPartProgressListener {
    void onProgress(Long bytesWritten, Long totalBytes);
}
