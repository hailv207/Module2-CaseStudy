package application.material;

public class MenuMaterial {
    private String code;
    private String name;
    private String unit;
    private double remaining;

    public MenuMaterial(String code, String name, String unit, double remaining) {
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.remaining = remaining;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getRemaining() {
        return remaining;
    }

    public void setRemaining(double remaining) {
        this.remaining = remaining;
    }
}
