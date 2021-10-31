package com.galvanize.prodman.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class IdDTO {

    @NotNull
    @Size(max=50)
    private String id;

}
