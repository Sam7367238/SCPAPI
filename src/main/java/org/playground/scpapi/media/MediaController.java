package org.playground.scpapi.media;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/media")
@AllArgsConstructor
public class MediaController {
    private final MediaService mediaService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MediaDto> createMedia(
            @RequestPart("file") MultipartFile file,
            UriComponentsBuilder uriBuilder
    ) throws IOException {
        mediaService.validateMedia(file);

        var dto = mediaService.uploadMedia(file);

        var uri = uriBuilder.path("/media/{uuid}").buildAndExpand(dto).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
