package com.galvanize.prodman.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class IdDTO {

    @NotNull
    @Size(max=50)
    private String id;

}
