package ua.nure.webshop.domain;

import org.springframework.data.domain.PageRequest;

import java.util.List;

public class Parameters {

    private List<String> diagonalParams;
    private List<String> resolutionsParams;
    private List<String> memorySizesParams;
    private List<String> flashMemorySizesParams;
    private List<String> batteryCapacitiesParams;
    private List<String> capacitiesParams;
    private List<String> colorsParams;
    private List<String> cpusParams;
    private List<String> displayTypesParams;
    private List<String> manufacturers;

    public Parameters(List<String> diagonalParams, List<String> resolutionsParams, List<String> memorySizesParams, List<String> flashMemorySizesParams, List<String> batteryCapacitiesParams, List<String> capacitiesParams, List<String> colorsParams, List<String> cpusParams, List<String> displayTypesParams, List<String> manufacturers) {
        this.diagonalParams = diagonalParams;
        this.resolutionsParams = resolutionsParams;
        this.memorySizesParams = memorySizesParams;
        this.flashMemorySizesParams = flashMemorySizesParams;
        this.batteryCapacitiesParams = batteryCapacitiesParams;
        this.capacitiesParams = capacitiesParams;
        this.colorsParams = colorsParams;
        this.cpusParams = cpusParams;
        this.displayTypesParams = displayTypesParams;
        this.manufacturers = manufacturers;
    }

    public List<String> getDiagonalParams() {
        return diagonalParams;
    }

    public void setDiagonalParams(List<String> diagonalParams) {
        this.diagonalParams = diagonalParams;
    }

    public List<String> getResolutionsParams() {
        return resolutionsParams;
    }

    public void setResolutionsParams(List<String> resolutionsParams) {
        this.resolutionsParams = resolutionsParams;
    }

    public List<String> getMemorySizesParams() {
        return memorySizesParams;
    }

    public void setMemorySizesParams(List<String> memorySizesParams) {
        this.memorySizesParams = memorySizesParams;
    }

    public List<String> getFlashMemorySizesParams() {
        return flashMemorySizesParams;
    }

    public void setFlashMemorySizesParams(List<String> flashMemorySizesParams) {
        this.flashMemorySizesParams = flashMemorySizesParams;
    }

    public List<String> getBatteryCapacitiesParams() {
        return batteryCapacitiesParams;
    }

    public void setBatteryCapacitiesParams(List<String> batteryCapacitiesParams) {
        this.batteryCapacitiesParams = batteryCapacitiesParams;
    }

    public List<String> getCapacitiesParams() {
        return capacitiesParams;
    }

    public void setCapacitiesParams(List<String> capacitiesParams) {
        this.capacitiesParams = capacitiesParams;
    }

    public List<String> getColorsParams() {
        return colorsParams;
    }

    public void setColorsParams(List<String> colorsParams) {
        this.colorsParams = colorsParams;
    }

    public List<String> getCpusParams() {
        return cpusParams;
    }

    public void setCpusParams(List<String> cpusParams) {
        this.cpusParams = cpusParams;
    }

    public List<String> getDisplayTypesParams() {
        return displayTypesParams;
    }

    public void setDisplayTypesParams(List<String> displayTypesParams) {
        this.displayTypesParams = displayTypesParams;
    }

    public List<String> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<String> manufacturers) {
        this.manufacturers = manufacturers;
    }
}
