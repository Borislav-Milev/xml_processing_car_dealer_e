package xml.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xml.app.domain.entity.Supplier;
import xml.app.domain.model.binding.SupplierSeedDto;
import xml.app.domain.model.binding.rootDto.SupplierSeedRootDto;
import xml.app.domain.model.view.query3.LocalSupplierRootDto;
import xml.app.repository.SupplierRepository;
import xml.app.service.contract.SupplierService;
import xml.app.util.contract.FileIO;
import xml.app.util.contract.ValidatorUtil;
import xml.app.util.contract.XmlParser;

import java.util.Random;

import static xml.app.constant.FilePath.LOCAL_SUPPLIERS_VIEW;

@Service
class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final FileIO fileIO;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ValidatorUtil validatorUtil,
                               ModelMapper modelMapper, XmlParser xmlParser, FileIO fileIO) {
        this.supplierRepository = supplierRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileIO = fileIO;
    }

    @Override
    public void seedSuppliers(String xmlSuppliers) {
        if(supplierRepository.count() != 0){
            return;
        }
        SupplierSeedRootDto supplierSeedRootDto = this.xmlParser.parse(SupplierSeedRootDto.class, xmlSuppliers);

        System.out.println();
        for (SupplierSeedDto supplierSeedDto : supplierSeedRootDto.getSupplierSeedDtos()) {
            if(this.validatorUtil.ifNotValidPrintViolations(supplierSeedDto)){
                return;
            }
            Supplier supplier = this.modelMapper.map(supplierSeedDto, Supplier.class);
            this.supplierRepository.saveAndFlush(supplier);
        }
    }

    @Override
    public Supplier getRandomSupplier(){
        Random random = new Random();
        return this.findSupplierById(random.nextInt(this.getCount()) + 1);
    }

    @Override
    public Supplier findSupplierById(Integer id){
        return this.supplierRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public String getLocalSuppliers() {
        LocalSupplierRootDto localSupplierDtos = new LocalSupplierRootDto();
        localSupplierDtos.getLocalSupplierDtos().addAll(this.supplierRepository.localSuppliers());
        System.out.println();
        this.xmlParser.export(localSupplierDtos, LocalSupplierRootDto.class, LOCAL_SUPPLIERS_VIEW);
        return this.fileIO.readFile(LOCAL_SUPPLIERS_VIEW);
    }

    public int getCount(){
        return Math.toIntExact(this.supplierRepository.count());
    }
}
