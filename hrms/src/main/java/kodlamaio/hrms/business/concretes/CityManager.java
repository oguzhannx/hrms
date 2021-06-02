package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.CityDao;
import kodlamaio.hrms.entities.concretes.City;

@Service
public class CityManager implements CityService {
	
	private CityDao cityDao;

	
	@Autowired
	public CityManager(CityDao cityDao) {
		super();
		this.cityDao = cityDao;
	}

	@Override
	public Result add(City city) {
		if (!cityDao.findByName(city.getName()).isEmpty()) {
			return new ErrorResult("Bu Şehir Zaten Eklenmiş.");
		}
		this.cityDao.save(city);
		return new SuccessResult("Şehir eklendi");
	}

	@Override
	public DataResult<List<City>> getAll() {
		
		return new SuccessDataResult<List<City>>(
				this.cityDao.findAll(),
				"Data Getirildi");
	}

}