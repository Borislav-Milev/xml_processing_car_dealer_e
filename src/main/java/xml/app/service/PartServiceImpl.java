package xml.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xml.app.domain.entity.Part;
import xml.app.domain.model.binding.PartSeedDto;
import xml.app.domain.model.binding.rootDto.PartSeedRootDto;
import xml.app.repository.PartRepository;
import xml.app.service.contract.PartService;
import xml.app.service.contract.SupplierService;
import xml.app.util.contract.ValidatorUtil;
import xml.app.util.contract.XmlParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {

    private final SupplierService supplierService;
    private final PartRepository partRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public PartServiceImpl(SupplierService supplierService, PartRepository partRepository,
                           ValidatorUtil validatorUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.supplierService = supplierService;
        this.partRepository = partRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    @Transactional
    public void seedParts(String xmlParts) {
        if (this.partRepository.count() != 0) {
            return;
        }
        PartSeedRootDto partSeedRootDto = this.xmlParser.parse(PartSeedRootDto.class, xmlParts);

        System.out.println();
        for (PartSeedDto partSeedDto : partSeedRootDto.getPartSeedDtos()) {
            if (this.validatorUtil.ifNotValidPrintViolations(partSeedDto)) {
                return;
            }
            Part part = this.modelMapper.map(partSeedDto, Part.class);
            part.setSupplier(this.supplierService.getRandomSupplier());
            this.partRepository.saveAndFlush(part);
        }
    }
    @Override
    public int getCount() {
        return Math.toIntExact(this.partRepository.count());
    }

    @Override
    public Part findPartById(Integer id) {
        return this.partRepository.findById(id).orElse(null);
    }

    @Override
    public List<Part> getRandomParts() {
        System.out.println();
        List<Part> parts = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= random.nextInt(10) + 10; i++) {
            parts.add(this.getRandomPart());
        }
        return parts;
    }

    @Override
    public Part getRandomPart(){
        Random random = new Random();
        return this.findPartById(random.nextInt(this.getCount()) + 1);
    }

}
