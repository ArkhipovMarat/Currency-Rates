package com.example1.dto.giphydata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiphyDataDto {
    private Data data;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Data {
        private String image_original_url;
    }
}
