import java.util.Date;

public class Report{
    private String SortMethod;
    private Double TotalTime;
    private int Registers;
    private String CreateAt;
    private long time;

    Report(){
        long milisec = System.currentTimeMillis();
        Date dt = new Date(milisec);
        this.CreateAt = dt.toString();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(String lastModified) {
        CreateAt = lastModified;
    }

    public Double getTotalTime() {
        return TotalTime;
    }

    public void setTotalTime(Double totalTime) {
        TotalTime = totalTime;
    }

    public String getSortMethod() {
        return SortMethod;
    }

    public void setSortMethod(String sortMethod) {
        SortMethod = sortMethod;
    }

    public int getRegisters() {
        return Registers;
    }

    public void setRegisters(int registers) {
        Registers = registers;
    }

}
