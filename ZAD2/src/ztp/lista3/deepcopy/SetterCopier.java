package ztp.lista3.deepcopy;

import ztp.lista3.model.City;
import ztp.lista3.model.Country;
import ztp.lista3.model.Inhabitant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetterCopier implements CountryCopier {
    @Override
    public List<Country> copyCountryList(List<Country> countryList) {
        List<Country> newCountryList = new ArrayList<>();
        for (Country country : countryList) {
            Country newCountry = new Country();
            newCountryList.add(newCountry);
            newCountry.setName(country.getName());
            List<City> newCityList = new ArrayList<>();
            newCountry.setCities(newCityList);
            for (City city : country.getCities()) {
                City newCity = new City();
                newCityList.add(newCity);
                newCity.setName(city.getName());
                Set<Inhabitant> newInhabitantSet = new HashSet<>();
                newCity.setInhabitants(newInhabitantSet);
                for (Inhabitant inhabitant : city.getInhabitants()) {
                    Inhabitant newInhabitant = new Inhabitant();
                    newInhabitantSet.add(newInhabitant);
                    newInhabitant.setFirstName(inhabitant.getFirstName());
                    newInhabitant.setLastName(inhabitant.getLastName());
                }
            }
        }
        return newCountryList;
    }
}
