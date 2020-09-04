package scoalainformala.ro.OnlineLibrary.repository;

import org.springframework.data.repository.CrudRepository;
import scoalainformala.ro.OnlineLibrary.domain.Acquisition;

import java.util.UUID;

public interface AcquisitionRepository extends CrudRepository<Acquisition, UUID> {
}
