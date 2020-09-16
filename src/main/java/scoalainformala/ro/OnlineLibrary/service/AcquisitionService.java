package scoalainformala.ro.OnlineLibrary.service;

import scoalainformala.ro.OnlineLibrary.domain.Acquisition;
import scoalainformala.ro.OnlineLibrary.dto.AcquisitionDto;
import scoalainformala.ro.OnlineLibrary.exceptions.NotEnoughProductsInStockException;

public interface AcquisitionService {
    Acquisition add(AcquisitionDto acquisitionDto) throws NotEnoughProductsInStockException;
}
