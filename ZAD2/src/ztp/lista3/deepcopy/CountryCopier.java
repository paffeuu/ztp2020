package ztp.lista3.deepcopy;

import ztp.lista3.model.Country;

import java.util.List;

public interface CountryCopier {
    List<Country> copyCountryList(List<Country> countryList);
}
