package enums;

/**
 * Created by fauno on 05/03/17.
 */
public class ItemWhile {
    private String labelIf;
    private String labelElse;
    private Boolean observeElse;

    public ItemWhile(String labelIf, String labelElse, Boolean observeElse) {
        this.labelIf = labelIf;
        this.labelElse = labelElse;
        this.observeElse = observeElse;
    }

    public ItemWhile(String labelIf, String labelElse) {
        this.labelIf = labelIf;
        this.labelElse = labelElse;
    }

    public String getLabelIf() {
        return labelIf;
    }

    public void setLabelIf(String labelIf) {
        this.labelIf = labelIf;
    }

    public String getLabelElse() {
        return labelElse;
    }

    public void setLabelElse(String labelElse) {
        this.labelElse = labelElse;
    }

    public Boolean getObserveElse() {
        return observeElse;
    }

    public void setObserveElse(Boolean observeElse) {
        this.observeElse = observeElse;
    }
}
