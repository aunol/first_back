package com.example.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class PetVO {	

    private int petNo;
    private int userNo;
    private String petName;
    private String petSpecies;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date petBirth;

    private String petGender;
}
