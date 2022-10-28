package com.visionrent.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageSavedResponse extends VRResponse{
    private String imageId;

    public ImageSavedResponse(String message, boolean success, String imageId) {
        super(message, success);
        this.imageId = imageId;
    }

}
