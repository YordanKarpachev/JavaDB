package softuni.exam.models.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastWrapper {

    @XmlElement(name = "forecast")
   private List<ForecastImportDTO> forecastImportDTOList;

    public ForecastWrapper() {
    }

    public List<ForecastImportDTO> getForecastImportDTOList() {
        return forecastImportDTOList;
    }

    public void setForecastImportDTOList(List<ForecastImportDTO> forecastImportDTOList) {
        this.forecastImportDTOList = forecastImportDTOList;
    }
}
