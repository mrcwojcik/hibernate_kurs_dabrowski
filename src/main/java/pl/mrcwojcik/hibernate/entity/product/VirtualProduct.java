package pl.mrcwojcik.hibernate.entity.product;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VIRTUAL")
public class VirtualProduct extends BaseProduct {

    private boolean downloable;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "file_name")
    private String filaname;

    public boolean isDownloable() {
        return downloable;
    }

    public void setDownloable(boolean downloable) {
        this.downloable = downloable;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilaname() {
        return filaname;
    }

    public void setFilaname(String filaname) {
        this.filaname = filaname;
    }

    @Override
    public String toString() {
        return "VirtualProduct{" +
                "downloable=" + downloable +
                ", filePath='" + filePath + '\'' +
                ", filaname='" + filaname + '\'' +
                '}';
    }
}
