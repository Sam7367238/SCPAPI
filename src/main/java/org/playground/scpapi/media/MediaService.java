package org.playground.scpapi.media;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaConfig config;
    private final S3Client s3Client;
    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    @Value("${spring.cloud.s3.bucket}")
    private String s3Bucket;

    public MediaDto uploadMedia(MultipartFile file) throws IOException {
        String ext = getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
        String storedName = UUID.randomUUID() + (ext.isEmpty() ? "" : "." + ext);

        s3Client.putObject(
                request -> request
                                .bucket(s3Bucket)
                                .key("images/" + storedName)
                                .ifNoneMatch("*"),
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        var media = new Media(
                null,
                file.getOriginalFilename(),
                storedName,
                file.getContentType(),
                file.getSize() / 1000,
                LocalDateTime.now()
        );

        mediaRepository.save(media);

        return mediaMapper.toDto(media);
    }

    public void validateMedia(MultipartFile file) {
        if (file.isEmpty()) {
            throw new EmptyFileException();
        }

        String mimeType = file.getContentType();

        if (mimeType == null || !config.getAllowedTypes().contains(mimeType)) {
            throw new InvalidMimeTypeException();
        }
    }

    private String getFileExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        return lastDot == -1 ? "" : fileName.substring(lastDot + 1);
    }
}
