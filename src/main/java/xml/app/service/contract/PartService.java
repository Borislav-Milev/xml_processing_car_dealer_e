package xml.app.service.contract;


import xml.app.domain.entity.Part;

import java.util.List;

public interface PartService {

    void seedParts(String jsonParts);

    int getCount();

    Part findPartById(Integer id);

    List<Part> getRandomParts();

    Part getRandomPart();
}
