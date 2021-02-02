package xml.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xml.app.domain.entity.Car;
import xml.app.domain.model.binding.CarSeedDto;
import xml.app.domain.model.binding.rootDto.CarSeedRootDto;
import xml.app.domain.model.view.query2.ToyotaCarDto;
import xml.app.domain.model.view.query2.ToyotaCarRootDto;
import xml.app.domain.model.view.query4.CarAndPartsDto;
import xml.app.domain.model.view.query4.CarsAndPartsRootDto;
import xml.app.repository.CarRepository;
import xml.app.service.contract.CarService;
import xml.app.service.contract.PartService;
import xml.app.util.contract.FileIO;
import xml.app.util.contract.ValidatorUtil;
import xml.app.util.contract.XmlParser;

import java.util.ArrayList;
import java.util.List;

import static xml.app.constant.FilePath.CARS_FROM_MAKE_TOYOTA_VIEW;
import static xml.app.constant.FilePath.CARS_WITH_PARTS_VIEW;

@Service
public class CarServiceImpl implements CarService {

    private final PartService partService;
    private final CarRepository carRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final FileIO fileIO;

    public CarServiceImpl(PartService partService, CarRepository carRepository,
                          ValidatorUtil validatorUtil, ModelMapper modelMapper, XmlParser xmlParser, FileIO fileIO) {
        this.partService = partService;
        this.carRepository = carRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileIO = fileIO;
    }

    @Override
    public void seedCars(String xmlCars) {
        if (this.carRepository.count() != 0) {
            return;
        }
        CarSeedRootDto carSeedRootDto = this.xmlParser.parse(CarSeedRootDto.class, xmlCars);

        for (CarSeedDto carSeedDto : carSeedRootDto.getCarSeedDtos()) {
            if (this.validatorUtil.ifNotValidPrintViolations(carSeedDto)) {
                return;
            }
            Car car = this.modelMapper.map(carSeedDto, Car.class);
            System.out.println();
            car.getParts().addAll(this.partService.getRandomParts());
            this.carRepository.saveAndFlush(car);
        }
    }

    @Override
    public List<Car> getAllCars(){
        return this.carRepository.findAll();
    }

    @Override
    public String getToyotaCars() {
        ToyotaCarRootDto toyotaCarRootDto = new ToyotaCarRootDto();
        List<Car> cars = this.carRepository.toyotaCars();

        for (Car car : cars) {
            toyotaCarRootDto.getToyotaCarDtos().add(this.modelMapper.map(car, ToyotaCarDto.class));
        }
        this.xmlParser.export(toyotaCarRootDto, ToyotaCarRootDto.class, CARS_FROM_MAKE_TOYOTA_VIEW);
        return this.fileIO.readFile(CARS_FROM_MAKE_TOYOTA_VIEW);
    }

    @Override
    @Transactional
    public String getCarsAndParts() {
        List<Car> cars = new ArrayList<>(this.carRepository.carsAndParts());
        CarsAndPartsRootDto carsAndPartsRootDto = new CarsAndPartsRootDto();
        for (Car car : cars) {
            carsAndPartsRootDto.getCarAndPartsDtos().add(this.modelMapper.map(car, CarAndPartsDto.class));
        }
        this.xmlParser.export(carsAndPartsRootDto, CarsAndPartsRootDto.class, CARS_WITH_PARTS_VIEW);
        return this.fileIO.readFile(CARS_WITH_PARTS_VIEW);
    }
}
