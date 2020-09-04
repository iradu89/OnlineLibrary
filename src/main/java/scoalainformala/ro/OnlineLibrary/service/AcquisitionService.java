package scoalainformala.ro.OnlineLibrary.service;

import scoalainformala.ro.OnlineLibrary.domain.Acquisition;
import scoalainformala.ro.OnlineLibrary.dto.AcquisitionDto;

import java.util.UUID;

public interface AcquisitionService {
    Acquisition add(AcquisitionDto acquisitionDto);
}
